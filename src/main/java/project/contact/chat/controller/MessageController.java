package project.contact.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.contact.chat.model.Message;
import project.contact.chat.service.MessageService;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message savedMessage = messageService.saveMessage(message);
        return ResponseEntity.ok(savedMessage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Message> updateMessage(@PathVariable Long id, @RequestBody Message message) {
        Message updatedMessage = messageService.updateMessage(id, message);
        if (updatedMessage == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedMessage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable Long id) {
        if (messageService.deleteMessage(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}

