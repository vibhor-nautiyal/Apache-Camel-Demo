package com.example.camelmicroservicea.routes.kafka;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class KafkaSenderRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("file:files/input/kafka")
                .to("kafka:myKafkaTopic");
    }
}
