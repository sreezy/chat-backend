package oasis.contact.chat.controller;
import org.springframework.beans.factory.annotation.Autowired;


import oasis.contact.chat.model.Question;
import oasis.contact.chat.service.QuestionService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/questions")
public class QuestionController {

    private QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
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

