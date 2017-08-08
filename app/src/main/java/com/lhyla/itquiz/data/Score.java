package com.lhyla.itquiz.data;

import java.util.Date;

/**
 * Created by RENT on 2017-08-08.
 */

public class Score {
    private Date date;
    private Integer points;
    private Integer questions;
    private Integer ID;

    public Score() {
    }

    public Score(Date date, Integer points, Integer questions, Integer ID) {
        this.date = date;
        this.points = points;
        this.questions = questions;
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getQuestions() {
        return questions;
    }

    public void setQuestions(Integer questions) {
        this.questions = questions;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}
