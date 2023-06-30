package com.example.adminserver.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
    private static final Logger log = LoggerFactory.getLogger(MyController.class);

    @GetMapping("/test")
    public String test() {
        log.debug("This is a debug log message");
        log.info("This is an info log message");
        log.warn("This is a warning log message");
        log.error("This is an error log message");

        return "Test completed";
    }
}
