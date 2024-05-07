package org.hivemq.edge.framework.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtils {
	
	public static Gson jsonObject() {
		GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting(); 
        Gson gson = builder.create(); 
        return gson;
	}

}
