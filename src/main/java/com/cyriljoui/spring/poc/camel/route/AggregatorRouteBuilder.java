package com.cyriljoui.spring.poc.camel.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class AggregatorRouteBuilder extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        errorHandler(defaultErrorHandler().redeliveryDelay(3000).maximumRedeliveries(1));

        from("timer://trigger?period=1000").
                process(exchange -> {
                    exchange.getOut().setBody(Arrays.asList(1, 2, 3, 4, 2, 5, 6, 2, 10, 11));
                }).
                split(body(), new SimpleAggregator())
                .parallelProcessing()
                .parallelAggregate()
                .process(exchange1 -> {
                    System.out.println(Thread.currentThread() + " - STARTING" + exchange1.getIn().getBody());
                    if (exchange1.getIn().getBody(Integer.class) == 2) {
                        //System.out.println(Thread.currentThread() + " - Headers: " + exchange1.getIn().getHeaders());
                        if (exchange1.getIn().getHeader("CamelRedelivered", Boolean.class) == Boolean.TRUE) {
                            return;
                        }
                        throw new RuntimeException("Error body 2");
                    }
                    Thread.sleep((long) (Math.random() * 5000));
                    System.out.println(Thread.currentThread() + " - ENDING" + exchange1.getIn().getBody());
                })
                .end()
                .to("log:out");
    }
}

class SimpleAggregator implements AggregationStrategy {

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        System.out.println(Thread.currentThread() + " - aggregate !");
        return newExchange;
    }
}
