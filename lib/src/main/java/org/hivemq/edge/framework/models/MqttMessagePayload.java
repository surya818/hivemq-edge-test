package org.hivemq.edge.framework.models;

public class MqttMessagePayload {

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private long timestamp;
    private String value;
}
