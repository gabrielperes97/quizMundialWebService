package get.brains.quizmundial.web.controllers

import org.ocpsoft.rewrite.el.ELBeanName
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(value = "session")
@Component(value = "menuController")
@ELBeanName(value = "menuController")
class GameController {
}