package org.hivemq.edge.framework.services;

import java.net.http.HttpResponse;
import java.util.List;

import org.hivemq.edge.framework.models.*;
import org.hivemq.edge.framework.utils.JSONUtils;

public class AdapterService extends BaseService {
	private String bearerToken;
	public AdapterService() {
		super();
	}
	
	public void setBearerToken(String bearerToken) {
		this.bearerToken = bearerToken;
	}
	
	public void CreateHttpAdapter(Adapter request) {
		String path = "/api/v1/management/protocol-adapters/adapters/http";
		String url = baseUrl + path;
		String payload = jsonObject.toJson(request, Adapter.class);
		HttpResponse<String> response = client.post(url, payload, this.bearerToken);
		assert (response != null) : "Response is null";
		assert (response.statusCode() == 200 ): String.format("Error Response Status code is %d \n Request Body: %s \n Response Body: %s \n",response.statusCode(),payload, response.body());

	}

	public HttpResponse<String> PostHttpAdapterResponse(Adapter request) {
		String path = "/api/v1/management/protocol-adapters/adapters/http";
		String url = baseUrl + path;
		String payload = jsonObject.toJson(request, Adapter.class);
        return client.post(url, payload, this.bearerToken);
	}
	
	public Adapters GetActiveAdapters() {
		String path = "/api/v1/management/protocol-adapters/adapters";
		String url = baseUrl + path;
		HttpResponse<String> response = client.get(url,this.bearerToken);
		assert (response != null) : "Response is null";
		assert (response.statusCode() == 200 ): "Response Status code is not 200 OK";
        return jsonObject.fromJson(response.body(),Adapters.class);
	}


	public Adapter GetActiveAdapter(String adapterId) {
		String path = "/api/v1/management/protocol-adapters/adapters/" + adapterId;
		String url = baseUrl + path;
		HttpResponse<String> response = client.get(url,this.bearerToken);
		assert (response != null) : "Response is null";
		assert (response.statusCode() == 200 ): "Response Status code is not 200 OK";
		return jsonObject.fromJson(response.body(),Adapter.class);
	}

	public void DeleteActiveAdapter(String adapterId) {
		String path = "/api/v1/management/protocol-adapters/adapters/" + adapterId;
		String url = baseUrl + path;
		HttpResponse<String> response = client.delete(url,this.bearerToken);
		assert (response.statusCode() == 200 ): "Response Status code is not 200 OK";

	}

	public void ModifyAdapterState(String adapterId, ModifyAdapterStateRequest request) {
		String path = "/api/v1/management/protocol-adapters/adapters/" + adapterId +"/status";
		String url = baseUrl + path;
		String payload = jsonObject.toJson(request, ModifyAdapterStateRequest.class);
		HttpResponse<String> response = client.put(url, payload, this.bearerToken);
		assert (response != null) : "Response is null";
		assert (response.statusCode() == 200 ): String.format("Error Response Status code is %d \n Request Body: %s \n Response Body: %s \n",response.statusCode(),payload, response.body());

	}



}
