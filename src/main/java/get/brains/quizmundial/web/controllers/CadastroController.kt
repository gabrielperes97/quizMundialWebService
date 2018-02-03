package get.brains.quizmundial.web.controllers

import get.brains.quizmundial.webservice.model.Player
import get.brains.quizmundial.webservice.repository.PlayerRepository
import net.bootsfaces.utils.FacesMessages
import org.ocpsoft.rewrite.el.ELBeanName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import javax.faces.context.FacesContext


@Scope(value = "session")
@Component(value = "cadastroController")
@ELBeanName(value = "cadastroController")
class CadastroController {

    @Autowired
    lateinit var playerDao : PlayerRepository

    @Autowired
    lateinit var playerBean : PlayerBean

    var player = Player()

    var passwordConfirmation: String = ""

    fun cadastrar(): String
    {
        if (player.password != passwordConfirmation)
        {
            FacesMessages.error(":(", "As senhas não conferem")
            passwordConfirmation=""
            playerBean.player!!.password = ""
            return ""
        }
        else {
            player.password = Player.hash(player.password)
            playerDao.save(player)
            playerBean.player = player
            player = Player()
            return "menu.jsf"
        }
    }

}