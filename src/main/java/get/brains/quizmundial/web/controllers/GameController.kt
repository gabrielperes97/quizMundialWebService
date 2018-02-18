package get.brains.quizmundial.web.controllers

import get.brains.quizmundial.web.objects.Game
import get.brains.quizmundial.webservice.repository.PlayerRepository
import org.ocpsoft.rewrite.el.ELBeanName
import org.primefaces.context.RequestContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.faces.context.FacesContext


@Scope(value = "request")
@Component(value = "gameController")
@ELBeanName(value = "gameController")
class GameController {

    @Autowired
    lateinit var playerBean : PlayerBean

    @Autowired
    lateinit var playerRepository: PlayerRepository


    val avalImages = arrayOf("text_youjumped.png", "text_youroright.png", "text_youwrong.png", "text_timeout.png" )
    val avalTexts = arrayOf("Você pulou", "Você acertou", "Você errou", "Tempo esgotado")
    var avalIndex = 0





    fun answerAQuestion(index : Int) {
        if (playerBean.game!!.answerQuestion(playerBean.game!!.actualQuestion!!.answers[index]))
        {
            //Acertou
            avalIndex = 1
        }
        else {
            //Errou
            avalIndex = 2
        }
        playerBean.game!!.lastStage = playerBean.game!!.actualQuestion!!.stage
        val requestContext = RequestContext.getCurrentInstance()
        requestContext.execute("\$('.avalmodal').modal()")
    }

    fun pular() {
        if (playerBean.game!!.pulosDisponiveis > 0)
        {
            playerBean.game!!.pulo()
        }
        //Pulou
        avalIndex = 0
        val requestContext = RequestContext.getCurrentInstance()
        requestContext.execute("\$('.avalmodal').modal()")
    }

    fun dica() {
        if (playerBean.game!!.dicasDisponiveis > 0)
        {
            val i = playerBean.game!!.dica()
            if (i >= 0)
                playerBean.game!!.buttonDisabled[i] = true
        }
    }

    fun finalizarJogo()
    {
        playerBean.game = null
        playerBean.player.currentLevel = 0
        playerBean.player.score = 0
        if (playerBean.player.id != null)
            playerRepository.save(playerBean.player)
        FacesContext.getCurrentInstance().externalContext.redirect("menu.jsf")
    }

    fun background():String
    {
        when(playerBean.game!!.actualQuestion!!.continent)
        {
            "América" -> return "globe_america.png"
            "Ásia" -> return "globe_asia.png"
            "África" -> return "globe_africa.png"
            "Europa" -> return "globe_europe.png"
            "Oceania" -> return "globe_oceania.png"
            else -> return ""
        }
    }

    fun nextQuestion()
    {
        if (playerBean.game!!.getNextQuestion() == null)
        {
            //finaliza jogo
            FacesContext.getCurrentInstance().externalContext.redirect("resumo.jsf")
        }
        playerBean.game!!.buttonDisabled = Array<Boolean>(5){_ -> false}
        if (playerBean.game!!.lastStage != playerBean.game!!.actualQuestion!!.stage)
        {
            playerBean.game!!.timerRunning = false
            val requestContext = RequestContext.getCurrentInstance()
            requestContext.execute("\$('.nivelmodal').modal()")
        }

        /*val ec = FacesContext.getCurrentInstance().externalContext
        ec.redirect((ec.request as HttpServletRequest).getRequestURI())*/
    }

    fun decrementTimer(){
        if (playerBean.game!!.timer <= 0 && playerBean.game!!.timerRunning)
        {
            //Time is Over
            playerBean.game!!.answerQuestion(null)
            avalIndex = 3
            val requestContext = RequestContext.getCurrentInstance()
            requestContext.execute("\$('.avalmodal').modal()")
        }
        else
        {
            if (playerBean.game!!.timerRunning)
                playerBean.game!!.timer--
        }
    }

    fun startTimer()
    {
        playerBean.game!!.timer = 15
        playerBean.game!!.timerRunning = true
    }

    fun getTimerPercent():Int
    {
        if (playerBean.game!!.timer < 0) return 0
        return (playerBean.game!!.timer *100) /15
    }


    fun continentProgresses(index:Int):Int
    {
        when(index) {
            0 -> {
                if (playerBean.player.currentLevel <= 4)
                    return playerBean.player.currentLevel
                else return 5
            }
            1 -> {
                if (playerBean.player.currentLevel <= 5)
                    return 0
                else return 1
            }
            2 -> {
                if (playerBean.player.currentLevel <= 6)
                    return 0
                else return 1
            }
            3 -> {
                if (playerBean.player.currentLevel <= 7)
                    return 0
                else return 1
            }
            4 -> {
                if (playerBean.player.currentLevel <= 8)
                    return 0
                else return 1
            }
        }
        return 1
    }


}