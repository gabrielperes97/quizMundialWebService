package get.brains.quizmundial.web.objects

import get.brains.quizmundial.web.controllers.PlayerBean
import get.brains.quizmundial.webservice.model.Answer
import get.brains.quizmundial.webservice.model.Question
import get.brains.quizmundial.webservice.repository.PlayerRepository
import get.brains.quizmundial.webservice.repository.QuestionRepository
import org.springframework.beans.factory.annotation.Autowired
import java.util.*
import javax.faces.context.FacesContext
import kotlin.collections.ArrayList

class Game(questionDao: QuestionRepository, playerRepository: PlayerRepository, playerBean: PlayerBean){


    val POINTS_TO_NEXT_LEVEL = 25
    val NUMBER_OF_STAGES = 8
    val POINTS_TO_GAME_OVER = NUMBER_OF_STAGES * POINTS_TO_NEXT_LEVEL


    val questionDao: QuestionRepository
    val playerBean: PlayerBean
    val playerRepository: PlayerRepository
    private var questionsToAnswer: MutableList<Question> = mutableListOf()
    var points = 0
        get (){
            if (field > POINTS_TO_NEXT_LEVEL)
                return POINTS_TO_NEXT_LEVEL
            else return field
        }
        private set
    var dicasDisponiveis = 3
    var pulosDisponiveis = 3
    var erros = 0
    var acertos = 0
    var dicasUsadas = ArrayList<Int>()
    val continentes = arrayOf("América", "Ásia", "África", "Europa", "Oceania") // nessa ordem
    var timer = 15
    var timerRunning = true


    var actualQuestion: Question? = null
        private set(value) {
            field = value
            field!!.answers.shuffle()
        }

    init{ //mudar isso se continuar o jogo, com o nivel do usuário
        this.questionDao = questionDao
        this.playerBean = playerBean
        this.playerRepository = playerRepository
        getNextQuestion()
    }

    fun answerQuestion(answer: Answer?): Boolean
    {
        if (answer != null) {
            timerRunning = false
            playerBean.player.answeredQuestions++
            if (answer.correct) {
                points += actualQuestion!!.difficult * 2 + 3
                playerBean.player.hits++
                acertos++
                return true
            }
            else
                erros++
            if (playerBean.player.id != null)
                playerRepository.save(playerBean.player)
        }
        return false
    }

    fun proximoNivel()
    {
        playerBean.player.score += points
        points = 0
        playerBean.player.currentLevel++
        if (playerBean.player.id != null)
            playerRepository.save(playerBean.player)
        if (playerBean.player.currentLevel <= NUMBER_OF_STAGES) {
            questionsToAnswer = questionDao.find10RandonByStage(playerBean.player.currentLevel).toMutableList()
            dicasDisponiveis = 3
            pulosDisponiveis = 3
        }
    }

    fun getNextQuestion(): Question? {
        //Se for level 5, então acabou o jogo
        if (playerBean.player.currentLevel <= NUMBER_OF_STAGES) {
            if (points >= POINTS_TO_NEXT_LEVEL) {
                proximoNivel()
                if (playerBean.player.currentLevel > NUMBER_OF_STAGES)
                    return null
            } else {
                if (questionsToAnswer.size <= 0) {
                    questionsToAnswer = questionDao.find10RandonByStage(playerBean.player.currentLevel).toMutableList()
                }
            }
            dicasUsadas.clear()
            actualQuestion = questionsToAnswer.removeAt(questionsToAnswer.size - 1)
            timer = 15
            timerRunning = true
            return actualQuestion
        }
        else
            return null
    }

    fun pulo(): Boolean
    {
        if (pulosDisponiveis > 0) {
            answerQuestion(null)
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

