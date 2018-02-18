package get.brains.quizmundial.web.controllers

import get.brains.quizmundial.web.objects.Game
import get.brains.quizmundial.webservice.model.Player
import get.brains.quizmundial.webservice.model.Question
import get.brains.quizmundial.webservice.repository.PlayerRepository
import get.brains.quizmundial.webservice.repository.QuestionRepository
import net.bootsfaces.utils.FacesMessages
import org.ocpsoft.rewrite.annotation.Join
import org.ocpsoft.rewrite.el.ELBeanName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.faces.context.FacesContext

@Scope(value = "request")
@Component(value = "loginController")
@ELBeanName(value = "loginController")
class LoginController {

    @Autowired
    lateinit var questionRepository: QuestionRepository

    @Autowired
    lateinit var playerRepository: PlayerRepository

    @Autowired
    lateinit var playerBean : PlayerBean


    var login: String = ""

    var password: String = ""

    fun doLogin(): String {
        var hash = Player.hash(password)
        if (hash != null)
        {
            var player = playerRepository.findByNickAndPassword(login, hash)
            if (player != null)
            {
                playerBean.player = player
                if (playerBean.player.currentLevel > 0)
                {
                    playerBean.game = Game(questionRepository, playerRepository, playerBean)
                }
                return "menu.jsf"
            }
        }
        FacesMessages.error("Erro", "Usuário ou senha incorretos")
        return ""
    }

    fun undoLogin() : String
    {
        playerBean.player = Player()
        playerBean.game = null
        return "login.jsf"
    }
}
