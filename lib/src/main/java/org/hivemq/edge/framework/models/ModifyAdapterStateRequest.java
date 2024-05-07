package org.hivemq.edge.framework.models;

public class ModifyAdapterStateRequest {

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    private String command;
}
