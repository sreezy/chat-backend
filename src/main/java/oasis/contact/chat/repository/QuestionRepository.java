package oasis.contact.chat.repository;

import oasis.contact.chat.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Modifying
    @Query(value = "DELETE FROM questions", nativeQuery = true)
    void deleteAllQuestions();


}

