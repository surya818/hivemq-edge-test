package org.hivemq.edge.framework.models;


import com.google.gson.annotations.SerializedName;

public class Adapter {
	private String id;
    public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public AdapterConfig getConfig() {
		return config;
	}
	public void setConfig(AdapterConfig config) {
		this.config = config;
	}
	public String type;
	@SerializedName("config")
    private AdapterConfig config;
	@SerializedName("status")
	private AdapterStatus status;
	public AdapterStatus getStatus() {
		return status;
	}
	public void setStatus(AdapterStatus status) {
		this.status = status;
	}
}


