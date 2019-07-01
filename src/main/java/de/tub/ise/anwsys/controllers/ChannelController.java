package de.tub.ise.anwsys.controllers;

import de.tub.ise.anwsys.model.Channel;
import de.tub.ise.anwsys.repositories.ChannelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@RestController
@RequestMapping("/channels")
public class ChannelController {

    private final Logger LOGGER = LoggerFactory.getLogger(ChannelController.class);

    @Value("${token}")
    private String token;

    @Autowired
    ChannelRepository channelRepository;

    @GetMapping
    public ResponseEntity<?> channels(@RequestParam(value = "page", required = false) Optional<Integer> page,
                                      @RequestParam(value = "size", required = false) Optional<Integer> size,
                                      @RequestHeader("X-Group-Token") String header,
                                      PagedResourcesAssembler pagedResourcesAssembler,
                                      Pageable pageable) {

        if (token.equals(header)) {
            return ResponseEntity.ok(pagedResourcesAssembler.toResource(channelRepository.findAll(pageable)));
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> channel(@PathVariable("id") long id,
                                     @RequestHeader("X-Group-Token") String header) {

        if (token.equals(header)) {
            if (channelRepository.findById(id).isPresent()) {
                return ResponseEntity.ok(channelRepository.findById(id).get());
            } else {
                return ResponseEntity.status(404).build();
            }
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<?> channel(@RequestBody Channel channel,
                                     @RequestHeader("X-Group-Token") String header)     {

        if (token.equals(header)) {
            if (channelRepository.findByName(channel.getName()) != null) {
                return ResponseEntity.status(409).build();
            } else {
                channelRepository.save(channel);
                LOGGER.info("Persisting: " + channel.toString());
                URI location = null;

                try {
                    location = new URI("localhost:8080/channels/" + channel.getId());
                } catch (URISyntaxException e) {
                    LOGGER.error("URI Syntax wasn't valid", e);
                }

                return ResponseEntity.created(location).body(channel);
            }
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
