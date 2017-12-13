package com.getbrains.quizmundial.webservice.repository

import com.getbrains.quizmundial.webservice.model.Answer
import com.getbrains.quizmundial.webservice.model.Question
import org.springframework.data.repository.CrudRepository

interface AnswerRepository : CrudRepository<Answer, Int>{
    fun findByQuestion(question: Question): List<Answer>

}