package com.example.cameldemoa.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RouteHandler extends RouteBuilder {
    @Autowired
    private GetCurrentTime getCurrentTime;

    @Override
    public void configure() {
        from("timer:timer")
                .bean(getCurrentTime, "getCurrentTime")
                .log("${body}")
                .process(new LoggingProcessor());
               //.to("log:timer?level=WARN");
    }
}

@Component
class GetCurrentTime{
    public String getCurrentTime(){
        return "The time is: "+ LocalDateTime.now();
    }
}

class LoggingProcessor implements Processor {
    private final Logger logger= LoggerFactory.getLogger(LoggingProcessor.class);
    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("Simple logging component {}", exchange.getMessage().getBody(String.class));
    }
}