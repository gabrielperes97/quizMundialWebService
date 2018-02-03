package get.brains.quizmundial.webservice.repository

import get.brains.quizmundial.webservice.model.Answer
import get.brains.quizmundial.webservice.model.AnsweredQuestion
import get.brains.quizmundial.webservice.model.Player
import get.brains.quizmundial.webservice.model.Question
import org.springframework.data.repository.CrudRepository

interface AnsweredQuestionRepository : CrudRepository<AnsweredQuestion, Int>{
    fun findByQuestion(question: Question): List<AnsweredQuestion>

    fun findByPlayer(player: Player): List<AnsweredQuestion>

}