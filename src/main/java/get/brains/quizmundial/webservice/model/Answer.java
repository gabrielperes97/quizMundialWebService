package get.brains.quizmundial.webservice.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "Answer")
@Table(name = "answer", schema = "quizmundial")
public class Answer {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "answerid", nullable = false)
    private Integer ID;

    @Column(name = "label", nullable = false)
    private String label;

    @Column(name = "correct", nullable = false)
    private Boolean correct;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Question.class)
    @JoinColumn(name = "questionid_fk", nullable = false)
    private Question question;

    public Answer() {
    }

    public Answer(Integer ID, String label, Boolean correct, Question questionID) {
        this.ID = ID;
        this.label = label;
        this.correct = correct;
        this.question = questionID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
