package com.cyriljoui.spring.poc.camel.event;

import com.cyriljoui.spring.poc.camel.event.model.EventMessage;
import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by popom on 01/07/16.
 */
@Component
public class CamelProducerExample {

    @EndpointInject(uri = "seda:eventReceiverRoute")
    private ProducerTemplate producerTemplate;

    @Autowired
    private ProducerTemplate producerTemplateGeneric;

    @Scheduled(fixedDelay = 1000)
    public void schedule() {
        producerTemplate.sendBody(new EventMessage("Scheduler"));
        producerTemplateGeneric.sendBody("seda:eventReceiverRoute", new EventMessage("SchedulerGeneric"));
    }
}
