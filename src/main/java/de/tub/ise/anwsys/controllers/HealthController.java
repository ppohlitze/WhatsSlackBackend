package de.tub.ise.anwsys.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HealthController {

    @Value("${token}")
    private String token;

    @GetMapping(value = "/health", produces = "application/json")
    public ResponseEntity<?> getHelloWorld(@RequestHeader("X-Group-Token") String header) {

        if (token.equals(header)) {
            return ResponseEntity.ok(String.format("Feeling pretty good!"));
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
