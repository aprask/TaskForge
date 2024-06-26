package com.api.taskforge.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/home")
    public String home() {
        return "Welcome to Task Forge";
    }

    @RequestMapping("/restricted")
    public String restricted() {
        return "RESTRICTED";
    }
}
