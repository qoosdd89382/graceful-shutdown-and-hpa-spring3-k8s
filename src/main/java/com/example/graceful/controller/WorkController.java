package com.example.graceful.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/work")
    public String work() throws InterruptedException {
        // 阻塞式
        logger.info("--------request in--------");
        Thread.sleep(10 * 1000L);
        return "success";
    }

    @GetMapping("/hpa")
    public String hpa() {
        // 阻塞式
        logger.info("--------request in--------");
        return "success";
    }
}