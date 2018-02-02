package get.brains.quizmundial.web.objects

import get.brains.quizmundial.webservice.model.Answer
import get.brains.quizmundial.webservice.model.Question
import get.brains.quizmundial.webservice.repository.QuestionRepository
import java.util.*
import kotlin.collections.ArrayList

class Game{
    private val answeredQuestions: MutableList<AnsweredQuestion> = ArrayList()
    private var questionsToAnswer: MutableList<Question>? = null
    var points : Int = 0
        private set
    var dicasDisponiveis = 3
    var pulosDisponiveis = 3

    var dicasUsadas = ArrayList<Int>()
    val continentes = arrayOf("América", "Ásia", "Africa", "Europa", "Oceania") // nessa ordem

    var actualQuestion: Question? = null
        private set(value) {
            field = value
           field!!.answers.shuffle()
        }

    constructor(questionDao:QuestionRepository){
        questionsToAnswer = questionDao.findByContinent("América").toMutableList()
        actualQuestion = questionsToAnswer!!.removeAt(questionsToAnswer!!.size - 1)
    }

    fun answerQuestion(answer: Answer?): Boolean
    {
        answeredQuestions!!.add(AnsweredQuestion(actualQuestion!!, answer))
        if (answer != null) {
            if (answer!!.correct) {
                points += actualQuestion!!.difficult * 2 + 3
                return true
            }
        }
        return false
    }

    fun getNextQuestion(): Question? {

        if (questionsToAnswer!!.size >= 1) {
            //Pega a ultima questão da lista
            actualQuestion = questionsToAnswer!!.removeAt(questionsToAnswer!!.size - 1)
            dicasUsadas.clear()
            return actualQuestion
        } else
            return null
    }

    fun pulo(): Boolean
    {
        if (pulosDisponiveis > 0) {
            answerQuestion(null)
            getNextQuestion()
            pulosDisponiveis--
            return true
        }
        return false
    }

    fun dica(): Int{
        if (dicasDisponiveis > 0) {
            val number = Random().nextInt(5)
            if (actualQuestion!!.answers[number].correct || dicasUsadas.contains(number))
                return dica()
            dicasDisponiveis--
            dicasUsadas.add(number)
            return number
        }
        return -1
    }


}

