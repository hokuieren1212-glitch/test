package com.example.demo;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GreetingService {

    private final GreetingRepository repository;

    public GreetingService(GreetingRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Greeting saveGreeting(String name) {
        String msg = (name == null || name.isBlank()) ? "Hello from Spring Boot" : String.format("Hello, %s", name);
        Greeting g = new Greeting(name, msg);
        return repository.save(g);
    }
}
