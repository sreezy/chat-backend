package project.contact.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import project.contact.chat.model.Message;
import org.springframework.stereotype.Service;
import project.contact.chat.repository.MessageRepository;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    private final PolicyFactory sanitizerPolicy = Sanitizers.FORMATTING.and(Sanitizers.LINKS);

    public Message saveMessage(Message message) {
        message.setContent(sanitizeContent(message.getContent()));
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message updateMessage(Long id, Message message) {
        if (messageRepository.existsById(id)) {
            message.setId(id);
            message.setContent(sanitizeContent(message.getContent()));
            return messageRepository.save(message);
        }
        return null;
    }

    public boolean deleteMessage(Long id) {
        if (messageRepository.existsById(id)) {
            messageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private String sanitizeContent(String content) {
        return sanitizerPolicy.sanitize(content);
    }
}
