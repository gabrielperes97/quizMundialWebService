package get.brains.quizmundial.web.controllers;

import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "olarController")
@ELBeanName(value = "olarController")
public class OlarController {

    private String text;
    private String olar = "Ola mundo";

    public String getOlar() {
        return olar;
    }

    public void setOlar(String olar) {
        this.olar = olar;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
