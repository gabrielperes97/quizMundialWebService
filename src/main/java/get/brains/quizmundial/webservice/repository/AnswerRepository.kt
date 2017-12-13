package get.brains.quizmundial.webservice.repository

import get.brains.quizmundial.webservice.model.Answer
import get.brains.quizmundial.webservice.model.Question
import org.springframework.data.repository.CrudRepository

interface AnswerRepository : CrudRepository<Answer, Int>{
    fun findByQuestion(question: Question): List<Answer>

}