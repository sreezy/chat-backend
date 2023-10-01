package project.contact.chat.repository;

import project.contact.chat.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query(value = "DELETE FROM question", nativeQuery = true)
    void deleteAllQuestions();


}

