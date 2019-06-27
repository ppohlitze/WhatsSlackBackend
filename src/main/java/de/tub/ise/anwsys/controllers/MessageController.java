package de.tub.ise.anwsys.controllers;

import de.tub.ise.anwsys.model.Message;
import de.tub.ise.anwsys.repositories.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/channels/{id}")
public class MessageController {

    private final Logger LOGGER = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    MessageRepository messageRepository;

    @GetMapping(value = "/messages", produces = "application/json")
    public ResponseEntity<?> getMessageList(@PathVariable("id") long id,
                                            @RequestParam(value = "lastSeenTimestamp", required = false)
                                            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> timestamp) {

        if (timestamp.isPresent()) {
            return ResponseEntity.ok(messageRepository.findMessagesNewerThanTimestamp(timestamp.get(), id));
        } else {
            return ResponseEntity.ok(messageRepository.findMessagesByChannelId(id, PageRequest.of(0, 10)));
        }
    }

    @PostMapping(value = "/messages", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createMessage(@PathVariable("id") long id,
                                           @RequestParam(value = "lastSeenTimestamp", required = false)
                                           @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> timestamp,
                                           @RequestBody Message message) {

        message.setChannelId(id);
        messageRepository.save(message);
        LOGGER.info("Persisting: "+ message.toString());

        if (timestamp.isPresent()) {
            return ResponseEntity.ok(messageRepository.findMessagesNewerThanTimestamp(timestamp.get(), id));
        } else {
            return ResponseEntity.ok(message);
        }
    }

    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<?> getUserList(@PathVariable("id") long id) {

        return ResponseEntity.ok(messageRepository.findUniqueCreatorsByChannelId(id));
    }
}
