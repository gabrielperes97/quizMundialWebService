package com.getbrains.qmrestservice.model;


import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity(name = "Question")
@Table(name = "question", schema = "quizmundial")
public class Question {

    @Id
    @GeneratedValue(strategy=IDENTITY)
    @Column(name="questionid", unique=true, nullable=false)
    private Integer ID;

    @Column(name = "continent", nullable = false, length = 24)
    private String continent;

    @Column(name = "region", nullable = false, length = 36)
    private String region;

    @Column(name = "difficult", nullable = false)
    private Integer difficult;

    @Column(name = "statement", nullable = false, length = 256)
    private String statement;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getDifficult() {
        return difficult;
    }

    public void setDifficult(Integer difficult) {
        this.difficult = difficult;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
