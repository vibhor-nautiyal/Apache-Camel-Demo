package com.example.camelmicroservicea.routes.files;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//commenting @Component to disable route
@Component
public class MyFileRouter extends RouteBuilder {

    @Autowired
    private JSONDeciderBean jsonDeciderBean;

    @Override
    public void configure() throws Exception {

//        from("file:files/input/camel")
//                .log("${body}")
//                .to("file:files/output");


        from("file:files/input/camel")
                .routeId("my-file-route")
                .transform().body(String.class)
                .choice()
                    .when(simple("${file:ext} ends with 'xml'"))
                        .log("XML file")
                    .when(method(jsonDeciderBean))
                        .log("File contains USD")
                    .otherwise()
                        .log("Neither JSON nor XML")
                .end()
                .log("${body}")
                .to("file:files/output");
    }
}

@Component
class JSONDeciderBean{
    public boolean decide(String body){

        return (body.contains("USD"));

    }
}