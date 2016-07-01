package com.cyriljoui.spring.poc.camel.event;

import org.apache.camel.CamelContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext =
                SpringApplication.run(Application.class, args);
        CamelContext camelContext = applicationContext.getBean(CamelContext.class);
        //DefaultProducerTemplate.newInstance(camelContext, "");
    }
}
