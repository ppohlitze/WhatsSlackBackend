package de.tub.ise.anwsys.controllers;

import de.tub.ise.anwsys.model.Message;
import de.tub.ise.anwsys.repositories.ChannelRepository;
import de.tub.ise.anwsys.repositories.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/channels/{id}")
public class MessageController {

    @Value("${token}")
    private String token;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    ChannelRepository channelRepository;

    @GetMapping(value = "/messages", produces = "application/json")
    public ResponseEntity<?> messages(@PathVariable("id") long id,
                                      @RequestParam(value = "lastSeenTimestamp", required = false)
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> timestamp,
                                      @RequestHeader("X-Group-Token") String header,
                                      PagedResourcesAssembler pagedResourcesAssembler) {
        if (token.equals(header)) {
            if (timestamp.isPresent()) {
                return ResponseEntity.ok(pagedResourcesAssembler.toResource(messageRepository.findMessagesNewerThanTimestamp(timestamp.get(), id, PageRequest.of(0, 50))));
            } else {
                return ResponseEntity.ok(pagedResourcesAssembler.toResource(messageRepository.findMessagesByChannelId(id, PageRequest.of(0, 10))));
            }
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @PostMapping(value = "/messages", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> message(@PathVariable("id") long id,
                                     @RequestParam(value = "lastSeenTimestamp", required = false)
                                     @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> timestamp,
                                     @RequestBody Message message,
                                     @RequestHeader("X-Group-Token") String header,
                                     PagedResourcesAssembler pagedResourcesAssembler) {
        if (token.equals(header)) {
            message.setChannel(channelRepository.findById(id).get());
            messageRepository.save(message);

            if (timestamp.isPresent()) {
                return ResponseEntity.ok(pagedResourcesAssembler.toResource(messageRepository.findMessagesNewerThanTimestamp(timestamp.get(), id, PageRequest.of(0, 50))));
            } else {
                return ResponseEntity.ok(pagedResourcesAssembler.toResource(messageRepository.findMessagesByChannelId(id, PageRequest.of(0, 10))));
            }
        } else {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<?> users(@PathVariable("id") long id,
                                   @RequestHeader("X-Group-Token") String header) {
        if (token.equals(header)) {
            return ResponseEntity.ok(messageRepository.findUniqueCreatorsByChannelId(id));
        } else {
            return ResponseEntity.status(401).build();
        }
    }
}
