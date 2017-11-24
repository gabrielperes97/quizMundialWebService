package com.getbrains.qmrestservice.repository

import com.getbrains.qmrestservice.model.Answer
import com.getbrains.qmrestservice.model.Question
import org.springframework.data.repository.CrudRepository

interface AnswerRepository : CrudRepository<Answer, Int>{
    fun findByQuestion(question: Question): List<Answer>

}