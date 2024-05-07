package org.hivemq.edge.framework.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Adapters {
	
	@SerializedName("items")
	private List<Adapter> items;

	public List<Adapter> getItems() {
		return items;
	}

	public void setItems(List<Adapter> items) {
		this.items = items;
	}

}
