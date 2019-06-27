package de.tub.ise.anwsys.controllers;

import de.tub.ise.anwsys.model.Channel;
import de.tub.ise.anwsys.repositories.ChannelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    private final Logger LOGGER = LoggerFactory.getLogger(ChannelController.class);

    @Autowired
    ChannelRepository channelRepository;

    @GetMapping(produces = "application/json")
    public ResponseEntity<?> getChannels(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                         @RequestParam(value = "size", required = false) Optional<Integer> size) {
        if (page.isPresent() && size.isPresent()) {
            return ResponseEntity.ok("Got channels on page: "+ page.get() +" and size: "+ size.get());
        } else if (page.isPresent() && !size.isPresent()) {
            return ResponseEntity.ok("Got channels on page: "+ page.get());
        } else if (!page.isPresent() && size.isPresent()) {
            return ResponseEntity.ok("Got channels with size: "+ size.get());
        } else {
            return ResponseEntity.ok(channelRepository.findAll());
        }
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<?> getChannelInformation(@PathVariable("id") long id) {

        return ResponseEntity.ok(channelRepository.findById(id));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createChannel(@RequestBody Channel channel) {

        channelRepository.save(channel);

        LOGGER.info("Persisting: "+ channel.toString());

        return ResponseEntity.ok(channel);
    }
}
