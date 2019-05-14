package com.unosquare.utils;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unosquare.config.Configuration;

public class ConfigReader {

	public static Configuration globalConfig;
	
	public static void loadConfiguration() {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			globalConfig = mapper.readValue(new File("src/test/resources/config.json"), Configuration.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
