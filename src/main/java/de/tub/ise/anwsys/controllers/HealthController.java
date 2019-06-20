package de.tub.ise.anwsys.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class HealthController {

    @GetMapping(value = "/health", produces = "text/html")
    public ResponseEntity<?> getHelloWorld() {
        return ResponseEntity.ok(String.format("Feeling pretty good!"));
    }
}
