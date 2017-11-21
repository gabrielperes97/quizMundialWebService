package com.getbrains.qmrestservice.controller

import com.getbrains.qmrestservice.model.Answer
import com.getbrains.qmrestservice.repository.AnswerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("answer")
class AnswerController {

    @Autowired
    lateinit var answerRepository : AnswerRepository

    @GetMapping
    fun getByQuestion(@RequestParam(value = "question", required = false) question:Int?):List<Answer>?{
        if (question != null) {
            return answerRepository.findByQuestionID(question)
        }
        return null
    }

    @PostMapping()
    fun save(@RequestBody answer: Answer): Answer = answerRepository.save(answer)

    @RequestMapping("list")
    fun list(): List<Answer> = answerRepository.findAll().toList()
}