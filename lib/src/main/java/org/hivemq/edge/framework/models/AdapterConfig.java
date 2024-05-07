package org.hivemq.edge.framework.models;

import java.util.ArrayList;

public class AdapterConfig {

	 public int getQos() {
		return qos;
	}
	public void setQos(int qos) {
		this.qos = qos;
	}
	public String getHttpRequestMethod() {
		return httpRequestMethod;
	}
	public void setHttpRequestMethod(String httpRequestMethod) {
		this.httpRequestMethod = httpRequestMethod;
	}
	public int getHttpConnectTimeout() {
		return httpConnectTimeout;
	}
	public void setHttpConnectTimeout(int httpConnectTimeout) {
		this.httpConnectTimeout = httpConnectTimeout;
	}
	public String getHttpRequestBodyContentType() {
		return httpRequestBodyContentType;
	}
	public void setHttpRequestBodyContentType(String httpRequestBodyContentType) {
		this.httpRequestBodyContentType = httpRequestBodyContentType;
	}
	public boolean isAssertResponseIsJson() {
		return assertResponseIsJson;
	}
	public void setAssertResponseIsJson(boolean assertResponseIsJson) {
		this.assertResponseIsJson = assertResponseIsJson;
	}
	public boolean isHttpPublishSuccessStatusCodeOnly() {
		return httpPublishSuccessStatusCodeOnly;
	}
	public void setHttpPublishSuccessStatusCodeOnly(boolean httpPublishSuccessStatusCodeOnly) {
		this.httpPublishSuccessStatusCodeOnly = httpPublishSuccessStatusCodeOnly;
	}
	public ArrayList<Object> getHttpHeaders() {
		return httpHeaders;
	}
	public void setHttpHeaders(ArrayList<Object> httpHeaders) {
		this.httpHeaders = httpHeaders;
	}
	public boolean isAllowUntrustedCertificates() {
		return allowUntrustedCertificates;
	}
	public void setAllowUntrustedCertificates(boolean allowUntrustedCertificates) {
		this.allowUntrustedCertificates = allowUntrustedCertificates;
	}
	public int getMaxPollingErrorsBeforeRemoval() {
		return maxPollingErrorsBeforeRemoval;
	}
	public void setMaxPollingErrorsBeforeRemoval(int maxPollingErrorsBeforeRemoval) {
		this.maxPollingErrorsBeforeRemoval = maxPollingErrorsBeforeRemoval;
	}
	public int getPollingIntervalMillis() {
		return pollingIntervalMillis;
	}
	public void setPollingIntervalMillis(int pollingIntervalMillis) {
		this.pollingIntervalMillis = pollingIntervalMillis;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	private int qos;
	 private String httpRequestMethod;
	 private int httpConnectTimeout;
	 private String httpRequestBodyContentType;
	    private boolean assertResponseIsJson;
	    private boolean httpPublishSuccessStatusCodeOnly;
	    private ArrayList<Object> httpHeaders;
	    private boolean allowUntrustedCertificates;
	    private int maxPollingErrorsBeforeRemoval;
	    private int pollingIntervalMillis;
	    private String destination;
	    private String url;
	    private String id;
}
