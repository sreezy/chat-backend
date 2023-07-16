package oasis.contact.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import oasis.contact.chat.model.Question;
import oasis.contact.chat.repository.QuestionRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
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

    @Transactional
    public void clearQuestions() {
        questionRepository.truncateTable();
    }

}

