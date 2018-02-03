package get.brains.quizmundial.webservice.controller

import get.brains.quizmundial.webservice.model.Answer
import get.brains.quizmundial.webservice.model.AnsweredQuestion
import get.brains.quizmundial.webservice.repository.AnswerRepository
import get.brains.quizmundial.webservice.repository.AnsweredQuestionRepository
import get.brains.quizmundial.webservice.repository.PlayerRepository
import get.brains.quizmundial.webservice.repository.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("answered_question")
class AnsweredQuestionController {

    @Autowired
    lateinit var answeredQuestionRepository : AnsweredQuestionRepository

    @Autowired
    lateinit var questionRepository : QuestionRepository

    @Autowired
    lateinit var playerRepository: PlayerRepository

    @GetMapping
    fun getByQuestion(@RequestParam(value = "question", required = false) question:Int?, @RequestParam(value="player", required = false) player:Int?):List<AnsweredQuestion>?{
        if (question != null) {
            return answeredQuestionRepository.findByQuestion(questionRepository.findOne(question))
        }
        else
            if (player != null)
            {
                return answeredQuestionRepository.findByPlayer(playerRepository.findOne(player))
            }
        return null
    }

    @PostMapping()
    fun save(@RequestBody answeredQuestion: AnsweredQuestion): AnsweredQuestion = answeredQuestionRepository.save(answeredQuestion)

}