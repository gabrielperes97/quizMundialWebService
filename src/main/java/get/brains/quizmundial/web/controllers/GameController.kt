package get.brains.quizmundial.web.controllers

import get.brains.quizmundial.web.objects.AnsweredQuestion
import net.bootsfaces.utils.FacesMessages
import org.ocpsoft.rewrite.config.False
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
        get() {
            if (field!!.game == null)
                FacesContext.getCurrentInstance().externalContext.redirect("menu.jsf")
            return field
        }

    var buttonDisabled = Array<Boolean>(5){i -> false}


    fun answerAQuestion(index : Int) {
        if (playerBean!!.game!!.answerQuestion(playerBean!!.game!!.actualQuestion!!.answers[index]))
            //mensagem questão correta
        {
            FacesMessages.info("Parabéns", "Você acertou a questão")
        }
        else {
            FacesMessages.error(":(", "Que pena!, você errou");
        }
        if (playerBean!!.game!!.getNextQuestion() != null)
        {
            //finaliza jogo
        }
        buttonDisabled = Array<Boolean>(5){i -> false}


    }

    fun pular() {
        if (playerBean!!.game!!.pulosDisponiveis > 0)
        {
            playerBean!!.game!!.pulo()
        }
        buttonDisabled = Array<Boolean>(5){i -> false}
    }

    fun dica() {
        if (playerBean!!.game!!.dicasDisponiveis > 0)
        {
            val i = playerBean!!.game!!.dica()
            if (i >= 0)
                buttonDisabled[i] = true
            print("desabilitou "+i+"\n")
        }
        
    }




}