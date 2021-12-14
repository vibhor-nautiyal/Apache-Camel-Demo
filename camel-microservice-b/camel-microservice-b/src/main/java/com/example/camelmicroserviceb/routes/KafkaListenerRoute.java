package com.example.camelmicroserviceb.routes;

import com.example.camelmicroserviceb.model.CurrencyExchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;

//@Component
public class KafkaListenerRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        from("kafka:myKafkaTopic")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .to("log:received-message-from-myKafkaTopic");
    }
}
