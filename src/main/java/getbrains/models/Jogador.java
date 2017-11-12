package getbrains.models;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "jogador")
public class Jogador {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="idjogador", unique=true, nullable=false)
    private Integer id;

    @Column(name = "Nick", nullable = true, length = 45)
    private String nick;

    @Column(name = "Idade", nullable = true)
    private Integer idade;

    @Column(name = "Pontuacao", nullable = true)
    private Integer pontuacao;

    @Column(name = "NivelAtual", nullable = true)
    private Integer nivelAtual;

    @Column(name = "Acertos", nullable = true)
    private Integer acertos;

    @Column(name = "Respostas", nullable = true)
    private Integer respostas;

    public Jogador() {
    }

    public Jogador(Integer id, String nick, Integer idade, Integer pontuacao, Integer nivelAtual, Integer acertos, Integer respostas) {
        this.id = id;
        this.nick = nick;
        this.idade = idade;
        this.pontuacao = pontuacao;
        this.nivelAtual = nivelAtual;
        this.acertos = acertos;
        this.respostas = respostas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public Integer getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(Integer pontuacao) {
        this.pontuacao = pontuacao;
    }

    public Integer getNivelAtual() {
        return nivelAtual;
    }

    public void setNivelAtual(Integer nivelAtual) {
        this.nivelAtual = nivelAtual;
    }

    public Integer getAcertos() {
        return acertos;
    }

    public void setAcertos(Integer acertos) {
        this.acertos = acertos;
    }

    public Integer getRespostas() {
        return respostas;
    }

    public void setRespostas(Integer respostas) {
        this.respostas = respostas;
    }
}
