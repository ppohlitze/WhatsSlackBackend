package de.tub.ise.anwsys.controllers;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/channels/{id}/messages")
public class MessageController {

    @GetMapping(produces = "text/html")
    public ResponseEntity<?> getMessageList(@PathVariable("id") long id,
                                            @RequestParam(value = "lastSeenTimestamp", required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> timestamp) {

        if (timestamp.isPresent()) {
            return ResponseEntity.ok("Got message list for channelId: "+ id +" and timestamp: "+ timestamp.get().toString());
        } else {
            return ResponseEntity.ok("Got message list for channelId: "+ id);
        }
    }

    @PostMapping(produces = "text/html")
    public ResponseEntity<?> createMessage(@PathVariable("id") long id,
                                           @RequestParam(value = "lastSeenTimestamp", required = false)
                                           @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> timestamp) {

        if (timestamp.isPresent()) {
            return ResponseEntity.ok("Created message list for channelId: "+ id +" and timestamp: "+ timestamp.get().toString());
        } else {
            return ResponseEntity.ok("Created message list for channelId: "+ id);
        }
    }
}
