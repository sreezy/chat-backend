package project.contact.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import project.contact.chat.model.Message;
import project.contact.chat.service.MessageValidationService;

@Controller
public class ChatController {

    @Autowired
    private MessageValidationService validationService;

    @MessageMapping("/chat.sendMessage")
    @SendTo("/topic/public")
    public Message sendMessage(@Payload Message chatMessage) {
        validationService.validateMessage(chatMessage);
        chatMessage.setType(Message.MessageType.CHAT);
        return chatMessage;
    }

    @MessageMapping("/chat.join")
    @SendTo("/topic/public")
    public Message join(@Payload Message chatMessage) {
        validationService.validateMessage(chatMessage);
        chatMessage.setType(Message.MessageType.JOIN);
        return chatMessage;
    }

    @MessageMapping("/chat.leave")
    @SendTo("/topic/public")
    public Message leave(@Payload Message chatMessage) {
        validationService.validateMessage(chatMessage);
        chatMessage.setType(Message.MessageType.LEAVE);
        return chatMessage;
    }

    @MessageMapping("/chat.error")
    @SendTo("/topic/public")
    public Message error(@Payload Message chatMessage) {
        validationService.validateMessage(chatMessage);
        chatMessage.setType(Message.MessageType.ERROR);
        return chatMessage;
    }

}

