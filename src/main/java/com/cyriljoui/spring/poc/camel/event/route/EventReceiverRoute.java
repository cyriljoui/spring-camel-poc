package com.cyriljoui.spring.poc.camel.event.route;

import com.cyriljoui.spring.poc.camel.event.processor.EventConsumerProcessor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by popom on 01/07/16.
 */
@Component
public class EventReceiverRoute extends RouteBuilder {

    @Autowired
    private EventConsumerProcessor eventConsumerProcessor;

    @Override
    public void configure() throws Exception {
        from("seda:eventReceiverRoute")
                .routeId("eventReceiverRoute")
                .threads(3)
                .process(eventConsumerProcessor);
    }
}
