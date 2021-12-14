package com.example.camelmicroserviceb.routes;

import com.example.camelmicroserviceb.model.CurrencyExchange;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class ActiveMQListenerRoute extends RouteBuilder {

    @Autowired
    private SimpleMessageProcessor simpleMessageProcessor;
    @Override
    public void configure() throws Exception {

        from("activemq:my-active-mq")
                .unmarshal().json(JsonLibrary.Jackson, CurrencyExchange.class)
                .bean(simpleMessageProcessor)
                .to("log:received message from activemq");

        from("activemq:my-active-mq-xml")
                .unmarshal().jacksonxml(CurrencyExchange.class)
                .bean(simpleMessageProcessor)
                .to("log:received message from activemq-xml");

    }
}


//@Component
@Slf4j
class SimpleMessageProcessor{
    public void process(CurrencyExchange currencyExchange){
        log.info("Processing-> {} -> {} to {}",currencyExchange.getConversionMultiple(),currencyExchange.getFrom(),currencyExchange.getTo());
    }
}
/*
{
  "id": 1000,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 70
}
*/