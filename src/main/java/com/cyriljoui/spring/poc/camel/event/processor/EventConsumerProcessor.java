package com.cyriljoui.spring.poc.camel.event.processor;

import com.cyriljoui.spring.poc.camel.event.model.EventMessage;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

/**
 * Created by popom on 01/07/16.
 */
@Component
public class EventConsumerProcessor implements Processor {
    @Override
    public void process(Exchange exchange) throws Exception {
        EventMessage eventMessage = exchange.getIn().getBody(EventMessage.class);
        System.out.println("Event message: " + eventMessage + Thread.currentThread());
        Thread.sleep(5000);
    }
}
