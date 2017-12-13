package com.getbrains.quizmundial.webservice.controller

import com.getbrains.quizmundial.webservice.model.Answer
import com.getbrains.quizmundial.webservice.repository.AnswerRepository
import com.getbrains.quizmundial.webservice.repository.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("answer")
class AnswerController {

    @Autowired
    lateinit var answerRepository : AnswerRepository

    @Autowired
    lateinit var questionRepository : QuestionRepository

    @GetMapping
    fun getByQuestion(@RequestParam(value = "question", required = false) question:Int?):List<Answer>?{
        if (question != null) {
            return answerRepository.findByQuestion(questionRepository.findOne(question))
        }
        return null
    }

    @PostMapping()
    fun save(@RequestBody answer: Answer): Answer = answerRepository.save(answer)

}