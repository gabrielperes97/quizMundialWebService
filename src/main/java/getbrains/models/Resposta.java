package getbrains.models;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "resposta")
public class Resposta {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name = "idresposta", nullable = false)
    private Integer id;

    @Column(name = "Rotulo", nullable = false, length = 128)
    private String rotulo;

    @Column(name = "Certo", nullable = false)
    private Boolean certo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idquestao", nullable = false)
    private Questao questao;

    public Resposta() {
    }

    public Resposta(Integer id, String rotulo, Boolean certo, Questao questao) {
        this.id = id;
        this.rotulo = rotulo;
        this.certo = certo;
        this.questao = questao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public Boolean getCerto() {
        return certo;
    }

    public void setCerto(Boolean certo) {
        this.certo = certo;
    }

    public Questao getQuestao() {
        return questao;
    }

    public void setQuestao(Questao questao) {
        this.questao = questao;
    }
}
