package com.getbrains.qmrestservice.model;

import javax.persistence.*;

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
    private Integer score;

    @Column(name = "current_level", nullable = false)
    private Integer currentLevel;

    @Column(name = "hits", nullable = false)
    private Integer hits;

    @Column(name = "answered_questions", nullable = false)
    private Integer answeredQuestions;


    public Player() {
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

}
