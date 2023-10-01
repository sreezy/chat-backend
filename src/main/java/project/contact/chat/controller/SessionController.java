package project.contact.chat.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/session")
public class SessionController {

    @GetMapping("/generate")
    public ResponseEntity<String> generateSessionId() {
        UUID sessionId = UUID.randomUUID();
        return ResponseEntity.ok(sessionId.toString());
    }
}
