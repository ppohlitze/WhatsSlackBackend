package de.tub.ise.anwsys.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    @GetMapping(produces = "text/html")
    public ResponseEntity<?> getChannels(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                         @RequestParam(value = "size", required = false) Optional<Integer> size) {
        if (page.isPresent() && size.isPresent()) {
            return ResponseEntity.ok("Got channels on page: "+ page.get() +" and size: "+ size.get());
        } else if (page.isPresent() && !size.isPresent()) {
            return ResponseEntity.ok("Got channels on page: "+ page.get());
        } else if (!page.isPresent() && size.isPresent()) {
            return ResponseEntity.ok("Got channels with size: "+ size.get());
        } else {
            return ResponseEntity.ok("Got channels with no information");
        }
    }

    @GetMapping(value = "{id}", produces = "text/html")
    public ResponseEntity<?> getChannelInformation(@PathVariable("id") long id) {

        return ResponseEntity.ok("Got Information for channel with id: "+ id);
    }

    @PostMapping(produces = "text/html")
    public ResponseEntity<?> createChannel() {

        return ResponseEntity.ok("Channel was created");
    }
}
