package com.getbrains.qmrestservice.repository

import com.getbrains.qmrestservice.model.Question
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface QuestionRepository : CrudRepository<Question, Int>{
    fun findByRegion(region: String): List<Question>

    fun findByContinent(continent: String): List<Question>
}
