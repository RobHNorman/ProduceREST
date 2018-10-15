package uk.co.produce.converters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;

import uk.co.produce.entities.Produce;

public class ProduceConverter {
	public static Produce convertToProduce(LinkedHashMap produce){
    	return new Produce(
    			produce.get("name").toString()
    			,BigDecimal.valueOf(Double.valueOf(produce.get("price").toString()))
    			,Integer.valueOf(produce.get("stock").toString())
    			,LocalDate.parse(produce.get("updated").toString(), DateTimeFormatter.ISO_LOCAL_DATE));
    	
		//return new ObjectMapper().convertValue(produce, Produce.class);
    }
}
