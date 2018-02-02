package get.brains.quizmundial.web.objects

import get.brains.quizmundial.web.controllers.PlayerBean
import get.brains.quizmundial.webservice.model.Answer
import get.brains.quizmundial.webservice.model.Question
import get.brains.quizmundial.webservice.repository.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import javax.faces.context.FacesContext
import kotlin.collections.ArrayList

class Game(questionDao: QuestionRepository){

    val questionDao: QuestionRepository
    private val answeredQuestions: MutableList<AnsweredQuestion> = ArrayList()
    private var questionsToAnswer: MutableList<Question>? = null
    var points = 0
        private set
    var totalPoints = 0
    var dicasDisponiveis = 3
    var pulosDisponiveis = 3
    var acertos = 0
    var erros = 0

    var dicasUsadas = ArrayList<Int>()
    val continentes = arrayOf("América", "Ásia", "África", "Europa", "Oceania") // nessa ordem
    var level = 0

    var actualQuestion: Question? = null
        private set(value) {
            field = value
           field!!.answers.shuffle()
        }
        get() {
            if (field == null)
                FacesContext.getCurrentInstance().externalContext.redirect("resumo.jsf")
            return field
        }

    init{ //mudar isso se continuar o jogo, com o nivel do usuário
        this.questionDao = questionDao
        startGame()
    }

    fun startGame()
    {
        level = 0
        println(continentes[0])
        questionsToAnswer = questionDao.find10RandonByContinent(continentes[0]).toMutableList()
        actualQuestion = questionsToAnswer!!.removeAt(questionsToAnswer!!.size - 1)
    }

    fun answerQuestion(answer: Answer?): Boolean
    {
        answeredQuestions.add(AnsweredQuestion(actualQuestion!!, answer))
        if (answer != null) {
            if (answer.correct) {
                points += actualQuestion!!.difficult * 2 + 3
                acertos++
                return true
            }
            else
                erros++
        }
        return false
    }

    fun proximoNivel()
    {
        totalPoints += points
        points = 0
        level++
        if (level < 5) {
            questionsToAnswer = questionDao.find10RandonByContinent(continentes[level]).toMutableList()
            dicasDisponiveis = 3
            pulosDisponiveis = 3
        }
    }

    fun getNextQuestion(): Question? {
        //Se for level 5, então acabou o jogo
        if (level < 5) {
            if (points >= 25) {
                proximoNivel()
                if (level >= 5)
                    return null
            } else {
                if (questionsToAnswer!!.size <= 0) {
                    questionsToAnswer = questionDao.find10RandonByContinent(continentes[level]).toMutableList()
                }
            }
            dicasUsadas.clear()
            actualQuestion = questionsToAnswer!!.removeAt(questionsToAnswer!!.size - 1)
            return actualQuestion
        }
        else
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

    fun totalPerguntas():Int
    {
        return acertos+erros
    }

    fun taxaAcertos():Int
    {
        return ((acertos*100) / totalPerguntas())
    }


}

