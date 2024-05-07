package org.hivemq.edge.framework.http;

import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;

public class RestClientInitializer {

	public static HttpClient initialize() {
		return HttpClient.newBuilder()
			      .version(Version.HTTP_2)
			      .followRedirects(Redirect.NORMAL)
			      .build();
		
	}
}
