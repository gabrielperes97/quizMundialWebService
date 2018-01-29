package get.brains.quizmundial.web.controllers

import get.brains.quizmundial.webservice.model.Player
import get.brains.quizmundial.webservice.model.Question
import get.brains.quizmundial.webservice.repository.PlayerRepository
import get.brains.quizmundial.webservice.repository.QuestionRepository
import org.ocpsoft.rewrite.annotation.Join
import org.ocpsoft.rewrite.el.ELBeanName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(value = "session")
@Component(value = "loginController")
@ELBeanName(value = "loginController")
class LoginController {
    @Autowired
    private val playerRepository: PlayerRepository? = null

    @Autowired
    private val playerBean : PlayerBean? = null


    var login: String = ""

    var password: String = ""

    fun doLogin(): String {
        var hash = Player.hash(password);
        if (hash != null)
        {
            var player = playerRepository!!.findByNickAndPassword(login, hash)
            println("Searching " + login + " with "+ hash)
            if (player != null)
            {
                println("Logou \\o/")
                playerBean!!.player = player
                return "menu.jsf"
            }
        }
        return ""
    }
}
