package oasis.contact.chat.service;

import oasis.contact.chat.model.Vote;
import oasis.contact.chat.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oasis.contact.chat.model.Question;
import oasis.contact.chat.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {


    private final QuestionRepository questionRepository;

    private final VoteRepository voteRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, VoteRepository voteRepository) {
        this.questionRepository = questionRepository;
        this.voteRepository = voteRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Optional<Question> getQuestionById(Long id) {
        return questionRepository.findById(id);
    }

    public Question askQuestion(String question) {
        Question newQuestion = new Question();
        newQuestion.setQuestion(question);
        return questionRepository.save(newQuestion);
    }

    public Question answerQuestion(Long id, String answer) {
        Question question = questionRepository.findById(id).orElse(null);
        if (question != null) {
            question.setAnswer(answer);
            questionRepository.save(question);
        }
        return question;
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public void voteOnQuestion(Long questionId, int voteValue, String ipAddress) {
        Optional<Vote> existingVote = voteRepository.findByIpAddressAndQuestionId(ipAddress, questionId);
        if (existingVote.isPresent()) {
            // If a vote from this IP address for this question already exists, don't allow another one
            throw new RuntimeException("This IP address has already voted on this question");
        }

        Question question = questionRepository.findById(questionId).orElseThrow(() -> new RuntimeException("Question not found"));

        Vote vote = new Vote();
        vote.setIpAddress(ipAddress);
        vote.setVoteValue(voteValue);
        vote.setQuestion(question);
        voteRepository.save(vote);

        question.getVotes().add(vote);
        questionRepository.save(question);
    }


}

