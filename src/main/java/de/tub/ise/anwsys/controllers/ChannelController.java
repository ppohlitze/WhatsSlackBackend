package de.tub.ise.anwsys.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    @GetMapping(produces = "text/html")
    public ResponseEntity<?> getChannels(@RequestParam(value = "page", required = false) int page,
                                         @RequestParam(value = "size", required = false) int size) {

        return null;
    }

    @GetMapping(value = "{id}", produces = "text/html")
    public ResponseEntity<?> getChannelInformation(@PathVariable("id") long id) {

        return null;
    }

    @PostMapping(produces = "text/html")
    public ResponseEntity<?> createNewChannel() {

        return null;
    }
}
