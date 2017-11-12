package getbrains.models;


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "questoes")
public class Questao {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="idquestoes", unique=true, nullable=false)
    private Integer id;

    @Column(name = "Continente", nullable = false, length = 50)
    private String continente;

    @Column(name = "Pais_Regiao", nullable = true, length = 60)
    private String paisRegiao;

    @Column(name = "Dificuldade", nullable = false)
    private Integer dificuldade;

    @Column(name = "Enunciado", nullable = true, length = 200)
    private String enunciado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String getPaisRegiao() {
        return paisRegiao;
    }

    public void setPaisRegiao(String paisRegiao) {
        this.paisRegiao = paisRegiao;
    }

    public Integer getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(Integer dificuldade) {
        this.dificuldade = dificuldade;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }
}
