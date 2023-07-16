package oasis.contact.chat.controller;
import oasis.contact.chat.dto.VoteDto;
import org.springframework.beans.factory.annotation.Autowired;


import oasis.contact.chat.model.Question;
import oasis.contact.chat.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Question>> getAllQuestions() {
        List<Question> questions = questionService.getAllQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        Optional<Question> optionalQuestion = questionService.getQuestionById(id);
        if(optionalQuestion.isPresent()){
            return new ResponseEntity<>(optionalQuestion.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/ask")
    public Question askQuestion(@RequestBody String question) {
        return questionService.askQuestion(question);
    }

    @PutMapping("/answer/{id}")
    public Question answerQuestion(@PathVariable Long id, @RequestBody String answer) {
        return questionService.answerQuestion(id, answer);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
    }

    @PostMapping("/questions/{id}/vote")
    public ResponseEntity<?> voteOnQuestion(@PathVariable Long id, @RequestBody VoteDto voteDto, HttpServletRequest request) {
        String ipAddress = request.getRemoteAddr();
        questionService.voteOnQuestion(id, voteDto.getVoteValue(), ipAddress);
        return ResponseEntity.ok().build();
    }



}

