package com.latency.stats.service.resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeResource {

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }
}
