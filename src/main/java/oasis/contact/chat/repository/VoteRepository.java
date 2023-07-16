package oasis.contact.chat.repository;

import oasis.contact.chat.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findByIpAddressAndQuestionId(String ipAddress, Long questionId);
}
