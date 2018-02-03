package get.brains.quizmundial.web.controllers

import get.brains.quizmundial.web.objects.Game
import get.brains.quizmundial.webservice.model.Player
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("session")
class PlayerBean {

    var player: Player = Player()
    var game: Game? = null

}