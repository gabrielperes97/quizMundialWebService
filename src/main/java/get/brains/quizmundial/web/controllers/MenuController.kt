package get.brains.quizmundial.web.controllers

import get.brains.quizmundial.web.objects.Game
import get.brains.quizmundial.webservice.repository.PlayerRepository
import get.brains.quizmundial.webservice.repository.QuestionRepository
import org.ocpsoft.rewrite.el.ELBeanName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletResponse
import javax.faces.context.FacesContext




@Scope(value = "session")
@Component(value = "menuController")
@ELBeanName(value = "menuController")
class MenuController {

    @Autowired
    lateinit var playerBean : PlayerBean

    @Autowired
    lateinit var questionDao : QuestionRepository

    @Autowired
    lateinit var playerRepository: PlayerRepository

    var hasGame : Boolean = false
        get(){
            return (playerBean.game != null)
        }

    var playerName : String = "Visitante"
        get() {
            if (playerBean.player.nick != null)
                return playerBean.player.nick
            else
                return "Visitante"
        }

    fun novoJogo() {
        playerBean.game = Game(questionDao, playerRepository, playerBean)
        FacesContext.getCurrentInstance().externalContext.redirect("questao.jsf")
    }

}