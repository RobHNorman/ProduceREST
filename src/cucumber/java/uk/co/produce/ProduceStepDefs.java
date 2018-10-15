package uk.co.produce;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.produce.converters.ProduceConverter;
import uk.co.produce.entities.Produce;
import io.restassured.http.Header;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.deps.com.thoughtworks.xstream.annotations.XStreamConverter;
import cucumber.deps.com.thoughtworks.xstream.annotations.XStreamConverters;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.assertj.core.api.Assertions.*;


public class ProduceStepDefs {
	String response = "";
	
	@Given("^I post '(.*?)' to \"(.*?)\"$")
	public void i_post_the(String path, String address) throws IOException {
		String postResponse = given().body(getFile(path)).header(new Header("Accept","application/json")).when().post(address).andReturn().asString();
		assertThat(postResponse).contains("OK");
	}

	private String getFile(String path) throws IOException {
		String produce;
		try (BufferedReader file = Files.newBufferedReader(Paths.get(path), StandardCharsets.UTF_8)) {
			produce = file.lines().collect(Collectors.joining());
		}
		return produce;
	}

	@When("^I call \"(.*?)\"$")
	public void i_call_the(String address) {
	    response = get(address).asString();
	}

	@Then("^I get the response$")
	public void i_get_the_response(List<Produce> lines) throws JsonParseException, JsonMappingException, IOException {
		List<LinkedHashMap> p = new ObjectMapper().readValue(response, List.class);
    	
		List<Produce> responseProduce = p.stream().map(ProduceConverter::convertToProduce).collect(Collectors.toList());
    	
		
	    assertThat(lines).containsAll(responseProduce);
	}

	public boolean notLines(String line){
		return !line.equals("lines");
	}
}