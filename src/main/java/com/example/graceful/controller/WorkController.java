package com.example.graceful.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkController {

    @GetMapping("/work")
    public String work() throws InterruptedException {
        // 阻塞式
        System.out.println("request in");
        Thread.sleep(10 * 1000L);
        return "success";
    }

    @GetMapping("/hpa")
    public String hpa() {
        // 阻塞式
        System.out.println("request in");
        return "success";
    }
}