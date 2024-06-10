package com.example.cameldemoa.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveEmitterRoute extends RouteBuilder {
    @Override
    public void configure() {
        // emit message to activeMq queue every 10seconds
        from("timer:mq-timer?period=10000")
                .transform().constant("Timer to queue message")
                .to("activemq:active-queue");
    }
}
