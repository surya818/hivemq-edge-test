package org.hivemq.edge.framework.models;

public enum HttpAdapterAction {
    START("START"),
    STOP("STOP");
    private final String value;

    // Constructor to initialize the enum value
    HttpAdapterAction(String value) {
        this.value = value;
    }

    // Method to get the enum value
    public String getValue() {
        return value;
    }
}
