package project.contact.chat.service;

import org.springframework.stereotype.Service;
import project.contact.chat.model.Message;

import static org.aspectj.weaver.model.AsmRelationshipUtils.MAX_MESSAGE_LENGTH;

@Service
public class MessageValidationService {

    public void validateMessage(Message message) {
        if (message.getContent().length() > MAX_MESSAGE_LENGTH) {
            throw new IllegalArgumentException("Message is too long");
        }
        // TODO: Add other validation checks as needed
    }
}

