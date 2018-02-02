package get.brains.quizmundial.web.controllers

import net.bootsfaces.utils.FacesMessages
import org.ocpsoft.rewrite.el.ELBeanName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.faces.context.FacesContext

@Scope(value = "session")
@Component(value = "gameController")
@ELBeanName(value = "gameController")
class GameController {

    @Autowired
    var playerBean : PlayerBean? = null

    var buttonDisabled = Array<Boolean>(5){_ -> false}


    fun answerAQuestion(index : Int) {
        if (playerBean!!.game!!.answerQuestion(playerBean!!.game!!.actualQuestion!!.answers[index]))
            //mensagem questão correta
        {
            FacesMessages.info("Parabéns", "Você acertou a questão")
        }
        else {
            FacesMessages.error(":(", "Que pena!, você errou")
        }
        if (playerBean!!.game!!.getNextQuestion() == null)
        {
            //finaliza jogo
            FacesContext.getCurrentInstance().externalContext.redirect("resumo.jsf")
        }
        buttonDisabled = Array<Boolean>(5){_ -> false}
    }

    fun pular() {
        if (playerBean!!.game!!.pulosDisponiveis > 0)
        {
            playerBean!!.game!!.pulo()
        }
        buttonDisabled = Array<Boolean>(5){_ -> false}
    }

    fun dica() {
        if (playerBean!!.game!!.dicasDisponiveis > 0)
        {
            val i = playerBean!!.game!!.dica()
            if (i >= 0)
                buttonDisabled[i] = true
        }
    }

    fun finalizarJogo()
    {
        playerBean!!.game = null
        FacesContext.getCurrentInstance().externalContext.redirect("menu.jsf")
    }

    fun background():String
    {
        when(playerBean!!.game!!.level)
        {
            0 -> return "globe_america.png"
            1 -> return "globe_asia.png"
            2 -> return "globe_africa.png"
            3 -> return "globe_europe.png"
            4 -> return "globe_oceania.png"
            else -> return ""
        }
    }



}