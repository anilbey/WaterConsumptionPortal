package it.polimi.awt.springmvc.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utilities {

	public static String convertToJSONString(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		// Object to JSON in String
		String jsonInString = new String();
		try {
			jsonInString = mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return jsonInString;

	}
}
