package com.cyriljoui.spring.poc.camel.event.model;

/**
 * Created by popom on 01/07/16.
 */
public class EventMessage {

    private String type;

    public EventMessage(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "type='" + type + '\'' +
                '}';
    }
}
