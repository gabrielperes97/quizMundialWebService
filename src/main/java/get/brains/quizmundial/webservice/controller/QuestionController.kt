package get.brains.quizmundial.webservice.controller

import get.brains.quizmundial.webservice.model.Question
import get.brains.quizmundial.webservice.repository.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("question")
class QuestionController {

    @Autowired
    lateinit var questionRepository : QuestionRepository

    @GetMapping
    fun getByRegion(@RequestParam(value = "region", required = false) region:String?,
                    @RequestParam(value = "continent", required = false) continent:String?):List<Question>?{
        if (region != null) {
            return questionRepository.findByRegion(region)
        }
        if (continent != null) {
            return questionRepository.findByContinent(continent)
        }
        return null
    }

    @PostMapping()
    fun save(@RequestBody question: Question): Question = questionRepository.save(question)

    @RequestMapping("list")
    fun list(): List<Question> = questionRepository.findAll().toList()
}