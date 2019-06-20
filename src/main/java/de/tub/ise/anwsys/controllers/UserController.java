package de.tub.ise.anwsys.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class UserController {

    @GetMapping(value = "/channels/{id}/users", produces = "text/html")
    public ResponseEntity<?> getChannelUserList(@PathVariable("id") long id) {


        return null;
    }
}
