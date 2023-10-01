package project.contact.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.contact.chat.model.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
