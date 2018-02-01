package get.brains.quizmundial.web.objects

import get.brains.quizmundial.webservice.model.Question
import get.brains.quizmundial.webservice.repository.QuestionRepository

class Game{
    private val answeredQuestions: MutableList<AnsweredQuestion>? = null
    private var questionsToAnswer: MutableList<Question>? = null
    var points : Int = 0
        private set

    var actualQuestion: Question? = null
        private set

    constructor(questionDao:QuestionRepository){
        questionsToAnswer = questionDao.findByContinent("América").toMutableList()
        actualQuestion = questionsToAnswer!!.removeAt(questionsToAnswer!!.size - 1)
    }

    fun answerQuestion(answeredQuestion: AnsweredQuestion): Boolean
    {
        answeredQuestions!!.add(answeredQuestion)
        if (answeredQuestion.answer.correct)
        {
            points += answeredQuestion.question.difficult*2+3
            return true
        }
        return false
    }

    fun getNextQuestion(): Question? {

        if (questionsToAnswer!!.size >= 1) {
            //Pega a ultima questão da lista
            actualQuestion = questionsToAnswer!!.removeAt(questionsToAnswer!!.size - 1)
            return actualQuestion
        } else
            return null
    }
}

