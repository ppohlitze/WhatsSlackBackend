package de.tub.ise.anwsys.controllers;

import de.tub.ise.anwsys.model.Channel;
import de.tub.ise.anwsys.repositories.ChannelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
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
            return ResponseEntity.ok(channelRepository.findAll(PageRequest.of(page.get(), size.get())));
        } else if (page.isPresent() && !size.isPresent()) {
            return ResponseEntity.ok(channelRepository.findAll(PageRequest.of(page.get(), 20)));
        } else if (!page.isPresent() && size.isPresent()) {
            return ResponseEntity.ok(channelRepository.findAll(PageRequest.of(0, size.get())));
        } else {
            return ResponseEntity.ok(channelRepository.findAll(PageRequest.of(0, 20)));
        }
    }

    @GetMapping(value = "{id}", produces = "application/json")
    public ResponseEntity<?> getChannelInformation(@PathVariable("id") long id) {

        if (channelRepository.findById(id).isPresent()) {
            return ResponseEntity.ok(channelRepository.findById(id).get());
        } else {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> createChannel(@RequestBody Channel channel)     {

        if (channelRepository.findByName(channel.getName()) != null) {

            return ResponseEntity.status(409).build();

        } else {

            channelRepository.save(channel);
            LOGGER.info("Persisting: "+ channel.toString());
            URI location = null;

            try {
                location = new URI("localhost:8080/channels/"+ channel.getId());
            } catch (URISyntaxException e) {
                LOGGER.error("URI Syntax wasn't valid", e);
            }

            return ResponseEntity.created(location).body(channel);
        }
    }
}
