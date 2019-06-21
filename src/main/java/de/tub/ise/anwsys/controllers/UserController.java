package de.tub.ise.anwsys.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(value = "/channels/{id}/users", produces = "text/html")
    public ResponseEntity<?> getUserList(@PathVariable("id") long id) {

        return ResponseEntity.ok("Got user list for channel with id: "+ id);
    }
}