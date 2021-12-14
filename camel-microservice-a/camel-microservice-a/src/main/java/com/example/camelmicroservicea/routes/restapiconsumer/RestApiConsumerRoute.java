package com.example.camelmicroservicea.routes.restapiconsumer;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

//@Component
public class RestApiConsumerRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        restConfiguration().host("localhost").port(8000);

        from("timer:my-timed-api-call?period=5000")
                .setHeader("from",()->"EUR")
                .setHeader("to",()->"INR")
                .log("${body}")
                .to("rest:get:/get/from/{from}/to/{to}")
                .log("${body}");
    }
}
