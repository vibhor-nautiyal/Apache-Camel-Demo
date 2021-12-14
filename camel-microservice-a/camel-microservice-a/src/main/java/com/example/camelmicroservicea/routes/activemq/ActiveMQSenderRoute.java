package com.example.camelmicroservicea.routes.activemq;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//@Component
public class ActiveMQSenderRoute extends RouteBuilder {

    @Autowired
    private MessageGenerator messageGenerator;

    @Override
    public void configure() throws Exception {

        // this class generates a message every 10 seconds and sends to the active mq instance on docker
//        from("timer:my-activemq-timer?period=10000") //generating a null message every 10 second
//                .bean(messageGenerator) //transforming the null message into current time
//                .log("${body}")
//                .to("activemq:my-active-mq");   //sending the message to activemq

        from("file:files/input/camel") //sending file contents from camel folder
                .to("activemq:my-active-mq");   //sending the message to activemq

        from("file:files/input/xml") //sending file contents from camel folder
                .to("activemq:my-active-mq-xml");   //sending the message to activemq


    }
}

@Component
class MessageGenerator{

    public String generate(){
        return "The time is "+ LocalDateTime.now().toLocalTime();
    }
}