package get.brains.quizmundial.webservice.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "AnsweredQuestion")
@Table(name = "answered_question", schema = "quizmundial")
public class AnsweredQuestion {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "answered_question_id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Player.class)
    @JoinColumn(name = "playerid_fk", nullable = false)
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Question.class)
    @JoinColumn(name = "questionid_fk", nullable = false)
    private Question question;

    @Column(name = "difficult", nullable = false)
    private Integer difficult;

    @Column(name = "hit", nullable = false)
    private Boolean hit;

    public AnsweredQuestion() {
    }

    public AnsweredQuestion(Player player, Question question, Integer difficult, Boolean hit) {
        this.player = player;
        this.question = question;
        this.difficult = difficult;
        this.hit = hit;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Integer getDifficult() {
        return difficult;
    }

    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }

    public Boolean getHit() {
        return hit;
    }

    public void setHit(Boolean hit) {
        this.hit = hit;
    }
}
