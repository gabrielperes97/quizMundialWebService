package com.getbrains.qmrestservice.repository

import com.getbrains.qmrestservice.model.Answer
import org.springframework.data.repository.CrudRepository

interface AnswerRepository : CrudRepository<Answer, Int>{
    fun findByQuestionID(questionid_fk: Int): List<Answer>

}