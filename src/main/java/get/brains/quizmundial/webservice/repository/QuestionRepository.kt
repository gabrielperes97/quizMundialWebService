package get.brains.quizmundial.webservice.repository

import get.brains.quizmundial.webservice.model.Question
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional

@Transactional
interface QuestionRepository : CrudRepository<Question, Int>{
    fun findByRegion(region: String): List<Question>

    fun findByContinent(continent: String): List<Question>

    @Query(nativeQuery = true, value = "SELECT * FROM quizmundial.question where continent=:cont order by random() limit 10")
    fun find10RandonByContinent(@Param("cont") continent: String): List<Question>

    @Query(nativeQuery = true, value = "SELECT * FROM quizmundial.question WHERE stage=:stg ORDER BY random() LIMIT 10")
    fun find10RandonByStage(@Param("stg") stage: Int): List<Question>
}
