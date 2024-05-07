package org.hivemq.edge.framework.services;

import java.net.http.HttpClient;

import org.hivemq.edge.framework.http.HttpServiceClient;
import org.hivemq.edge.framework.utils.JSONUtils;
import org.hivemq.edge.framework.utils.TestDataInitializer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class BaseService {
	
	protected HttpServiceClient client;
	protected Gson jsonObject;
	protected JsonObject testData;
	protected String baseUrl;
	protected String bearerToken;
	protected String brokerUrl;

	BaseService(){
		
		this.client = new HttpServiceClient();
		this.jsonObject = JSONUtils.jsonObject();
		testData = TestDataInitializer.loadTestData();
		baseUrl = testData.get("baseUrl").getAsString();
	}

}
