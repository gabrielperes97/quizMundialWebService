package get.brains.quizmundial.web.controllers

import get.brains.quizmundial.web.objects.AnsweredQuestion
import get.brains.quizmundial.webservice.model.Question
import org.ocpsoft.rewrite.el.ELBeanName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Scope(value = "session")
@Component(value = "gameController")
@ELBeanName(value = "gameController")
class GameController {

    @Autowired
    val playerBean : PlayerBean? = null

    val hue :String = "hue"


}