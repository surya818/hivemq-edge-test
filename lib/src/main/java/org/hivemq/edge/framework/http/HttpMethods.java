package org.hivemq.edge.framework.http;

import java.net.http.HttpResponse;
import java.util.Map;

import com.google.common.reflect.TypeToken;

public interface HttpMethods {

	public HttpResponse<String> get(String serverAddress, String header, String value);
	public HttpResponse<String> post(String serverAddress, String payload, String header, String value);
	public HttpResponse<String> get(String serverAddress, String bearerToken);
	public HttpResponse<String> post(String serverAddress, String payload, String bearerToken);
	public HttpResponse<String> post(String serverAddress, String payload);
	public HttpResponse<String> put(String serverAddress, String payload, String bearerToken);
	public HttpResponse<String> delete(String serverAddress, String bearerToken);
}
