package oasis.contact.chat.repository;

import oasis.contact.chat.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, Long> {
}

