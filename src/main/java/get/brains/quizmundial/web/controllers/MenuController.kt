package get.brains.quizmundial.web.controllers

import org.ocpsoft.rewrite.el.ELBeanName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct


@Scope(value = "session")
@Component(value = "menuController")
@ELBeanName(value = "menuController")
class MenuController {

    @Autowired
    private val playerBean : PlayerBean? = null

    var playerName : String = "Visitante"
        get() {
            if (playerBean!!.player != null)
                return playerBean!!.player!!.nick
            else
                return "Visitante"
        }
}