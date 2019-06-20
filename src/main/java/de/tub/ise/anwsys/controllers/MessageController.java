package de.tub.ise.anwsys.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/channels/{id}/messages[?lastSeenTimestamp]")
public class MessageController {

    @GetMapping(produces = "text/html")
    public ResponseEntity<?> getChannelMessageList(@PathVariable("id") long id,
                                                   @RequestParam(value = "lastSeenTimestamp", required = false)
                                                   @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp) {


        return null;
    }

    @PostMapping(produces = "text/html")
    public ResponseEntity<?> createMessage(@PathVariable("id") long id,
                                           @RequestParam(value = "lastSeenTimestamp", required = false)
                                           @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp) {


        return null;
    }
}
