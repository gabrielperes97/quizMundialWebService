package get.brains.quizmundial.web.controllers;

import get.brains.quizmundial.webservice.model.Question;
import get.brains.quizmundial.webservice.repository.QuestionRepository;
import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "indexController")
@ELBeanName(value = "indexController")
@Join(path = "/", to = "index.jsf")
public class IndexController {

    @Autowired
    private QuestionRepository questionRepository;

    public Iterable<Question> getQuestions()
    {
        return questionRepository.findAll();
    }
}
