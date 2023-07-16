package oasis.contact.chat.controller;
import org.springframework.beans.factory.annotation.Autowired;


import oasis.contact.chat.model.Question;
import oasis.contact.chat.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}

