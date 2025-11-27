package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class HelloController {

    private final GreetingService greetingService;

    public HelloController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GetMapping("/hello")
    public Map<String, String> hello(@RequestParam(name = "name", required = false) String name) {
        Greeting saved = greetingService.saveGreeting(name);
        String msg = saved.getMessage();
        String id = saved.getId() != null ? String.valueOf(saved.getId()) : "";
        return Map.of("message", msg, "id", id);
    }
}
