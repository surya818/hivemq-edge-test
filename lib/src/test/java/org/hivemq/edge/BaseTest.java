package org.hivemq.edge;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.hivemq.edge.framework.http.HttpServiceClient;
import org.hivemq.edge.framework.models.Adapter;
import org.hivemq.edge.framework.models.AdapterConfig;
import org.hivemq.edge.framework.models.AuthenticateRequest;
import org.hivemq.edge.framework.models.AuthenticateResponse;
import org.hivemq.edge.framework.services.AuthenticationService;
import org.hivemq.edge.framework.utils.JSONUtils;
import org.hivemq.edge.framework.utils.TestDataInitializer;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class BaseTest {
	
	protected Gson jsonObject;
	protected JsonObject testData;
	protected String username;
	protected String password;
	protected String bearerToken;
	protected String brokerUrl;
	protected String topic;

	BaseTest(){
		
		this.jsonObject = JSONUtils.jsonObject();
		testData = TestDataInitializer.loadTestData();
		username = testData.get("username").getAsString();
		password = testData.get("password").getAsString();
		brokerUrl = testData.get("brokerUrl").getAsString();
		topic = testData.get("topic").getAsString();
		this.bearerToken = "Bearer "+getBearerToken();

	}

	protected String getBearerToken() {
		AuthenticateRequest request = new AuthenticateRequest();
		request.setUsername(username); request.setPassword(password);
    	AuthenticationService authService = new AuthenticationService();
    	AuthenticateResponse loginResponse = authService.login(request);
    	String token = loginResponse.getToken();
		return token;
	}
	
	protected AuthenticateRequest createAuthRequest() {
		AuthenticateRequest request = new AuthenticateRequest();
		request.setUsername(username); request.setPassword(password);
		return request;
	}

	protected AuthenticateRequest createAuthRequest(String userName, String passWord) {
		AuthenticateRequest request = new AuthenticateRequest();
		request.setUsername(userName); request.setPassword(passWord);
		return request;
	}


	protected Adapter createHttpAdapterRequest (String adapterId) {
		Adapter request = new Adapter();
		AdapterConfig config = new AdapterConfig();
		config.setHttpRequestMethod("GET");
		config.setAllowUntrustedCertificates(true);
		config.setHttpConnectTimeout(5);
		config.setHttpRequestBodyContentType("JSON");
		config.setAssertResponseIsJson(false);
		config.setHttpPublishSuccessStatusCodeOnly(true);
		config.setHttpHeaders(new ArrayList<Object>());
		config.setMaxPollingErrorsBeforeRemoval(5);
		config.setPollingIntervalMillis(1000);
		config.setUrl("https://jsonplaceholder.typicode.com/todos/1");
		config.setDestination("test");
		config.setQos(0);
		config.setId(adapterId);
		request.setConfig(config);
		request.setId(adapterId);
		request.setType("http");

		return request;
	}

	protected String generateAdapterId() {
		StringBuilder builder = new StringBuilder("http-adapter-");
		int randomNumber = (int) (Math.random() * 10000) + 1;
		builder.append(randomNumber);
		return builder.toString();
	}

}
