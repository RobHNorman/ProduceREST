package uk.co.produce.controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.co.produce.converters.ProduceConverter;
import uk.co.produce.entities.Produce;
import uk.co.produce.repositories.ProduceRepository;

@RestController
public class ProduceController {

	@Resource
	ProduceRepository produceRepository;

    @RequestMapping(path="/produce", method=GET)
    public List<Produce> getProduce() {
        return produceRepository.findAll().stream().sorted(this::sortByUpdated).collect(Collectors.toList());
    }
    
    public int sortByUpdated(Produce prod1, Produce prod2) {
		return prod1.getUpdated().compareTo(prod2.getUpdated());
	}
    
    @RequestMapping(path="/produce", method=POST)
    public String setProduce(@RequestBody String produce) throws JsonParseException, JsonMappingException, IOException {
    	produceRepository.deleteAll();
    	List<LinkedHashMap> p = new ObjectMapper().readValue(produce, List.class);
    	
    	produceRepository.saveAll(p.stream().map(ProduceConverter::convertToProduce).collect(Collectors.toList()));
    	
        return "OK";
    }
    
    
}
