package get.brains.quizmundial.webservice.model;

import javax.persistence.*;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "Player")
@Table(name = "player", schema = "quizmundial")
public class Player {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="playerid", unique=true, nullable=false)
    private Integer ID;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nick", nullable = false)
    private String nick;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "score", nullable = false)
    private Integer score =0;

    @Column(name = "current_level", nullable = false)
    private Integer currentLevel =0;

    @Column(name = "hits", nullable = false)
    private Integer hits=0;

    @Column(name = "answered_questions", nullable = false)
    private Integer answeredQuestions=0;


    public Player() {
    }

    public Player(String nick) {
        this.nick = nick;
    }

    public Player(Integer ID, String nick, Integer age, Integer score, Integer currentLevel, Integer hits, Integer answeredQuestions, String password) {
        this.ID = ID;
        this.nick = nick;
        this.age = age;
        this.score = score;
        this.currentLevel = currentLevel;
        this.hits = hits;
        this.answeredQuestions = answeredQuestions;
        this.password = password;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(Integer currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits =    hits;
}

    public Integer getAnsweredQuestions() {
        return answeredQuestions;
    }

    public void setAnsweredQuestions(Integer answeredQuestions) {
        this.answeredQuestions = answeredQuestions;
    }

    public static String hash(String input)
    {
        MessageDigest digest;
        try
        {
            digest = MessageDigest.getInstance("SHA-1");
            byte[] sha1 = digest.digest(input.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for(byte b : sha1)
                sb.append(Integer.toHexString((b&0xFF) | 0x100).substring(1,3));
            return sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
