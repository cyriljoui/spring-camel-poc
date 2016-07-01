package com.cyriljoui.spring.poc.camel.event.route;

import com.cyriljoui.spring.poc.camel.event.model.EventMessage;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

/**
 * Created by popom on 01/07/16.
 */
//@Component
public class TimerTestRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        from("timer://trigger?repeatCount=1000&period=60")
                .setBody(constant(new EventMessage("Timer")))
//                .to("log:out?showAll=true")
                .to("seda:eventReceiverRoute");
    }
}
