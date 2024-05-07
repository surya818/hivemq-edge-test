package org.hivemq.edge.framework.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TestDataInitializer {

	public static JsonObject loadTestData() {

		JsonObject testdata = null;
		InputStream inputStream = TestDataInitializer.class.getClassLoader().getResourceAsStream("testdata.json");
		
        if (inputStream != null) {
            Gson json = JSONUtils.jsonObject();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                testdata = json.fromJson(reader, JsonObject.class);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        } else {
        	
            System.err.println("Test data file not found in resources");
        }
        return testdata;
	}
	
	
}
