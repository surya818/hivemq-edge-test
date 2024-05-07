package org.hivemq.edge.framework.services;

import java.net.http.HttpResponse;

import org.hivemq.edge.framework.models.ApiException;
import org.hivemq.edge.framework.models.AuthenticateRequest;
import org.hivemq.edge.framework.models.AuthenticateResponse;
import org.hivemq.edge.framework.utils.JSONUtils;

public class AuthenticationService extends BaseService {
	
	public AuthenticationService() {
		super();
	}
	
	public AuthenticateResponse login(AuthenticateRequest request) {
		String path = "/api/v1/auth/authenticate";
		String url = baseUrl + path;
		String payload = jsonObject.toJson(request, AuthenticateRequest.class);
		HttpResponse<String> response = loginHttpResponse(request);
		assert (response != null) : "Response is null";
		assert (response.statusCode() == 200 ): "Response Status code is not 200 OK";
        return jsonObject.fromJson(response.body(), AuthenticateResponse.class);
	}

	public HttpResponse<String> loginHttpResponse(AuthenticateRequest request) {
		String path = "/api/v1/auth/authenticate";
		String url = baseUrl + path;
		String payload = jsonObject.toJson(request, AuthenticateRequest.class);
        return client.post(url, payload);
	}

}
