package com.example.camelmicroservicea.routes.timer;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//Commenting this @Component to deactivate this route
//@Component
public class MyFirstTimerRoute extends RouteBuilder {

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessor simpleLoggingProcessor;
    @Override
    public void configure() throws Exception {
        // 1.listen to a message queue / timer
        // 2.transform/morph the message
        // 3.print.log.save to DB

        from("timer:my-timed-message") //timer is a keyword here; sends a null message to every second
//                .transform().constant("My constant message")      //sends this constant string as a message body
//                .transform().constant("The time is "+ LocalDateTime.now())    //sends the same time every second as it is a constant
                .bean(getCurrentTimeBean,"getTime")     //Uses the bean(GetCurrentTimeBean) to get current time and initializes the null message with it
                //method name isn't required is there is only one method
                .log("${body}")
                .bean(simpleLoggingProcessor)   //processing using bean
                .process(new AnotherSimpleLoggingProcesssor())  //processing using Processor interface
                .to("log:my-timed-message"); //log is a keyword here; catches the message and logs it
    }
}


@Component
class GetCurrentTimeBean{
    // for transformation of data/message

    public String getTime(){

        return "The time is "+ LocalDateTime.now();
    }
}

@Component
@Slf4j
class SimpleLoggingProcessor{
    // class that process (not transforms) a message

    public void process(String message){
        String s=message.substring(message.lastIndexOf(' ')+1);
        log.info("After processing-> {}",s);
    }
}

@Slf4j
class AnotherSimpleLoggingProcesssor implements Processor {

    @Override
    public void process(Exchange exchange) throws Exception {
        log.info("After using processor interface-> {}",exchange.getMessage().getBody());
    }
}