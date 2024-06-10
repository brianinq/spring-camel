package com.example.cameldemoa.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class FilesRouteHandler extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        // listens on the folder input at root and whenever a file is added the file is moved to output
        from("file:files/input")
                .to("file:files/output");
    }
}
