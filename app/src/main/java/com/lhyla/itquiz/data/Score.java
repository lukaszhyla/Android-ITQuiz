package com.lhyla.itquiz.data;

public class Score {
    private String date;
    private String points;
    private String questions;
    private Integer ID;

    public Score() {
    }

    public Score(String date, String points, String questions) {
        this.date = date;
        this.points = points;
        this.questions = questions;
    }

    public Score(String date, String points, String questions, Integer ID) {
        this.date = date;
        this.points = points;
        this.questions = questions;
        this.ID = ID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }


    @Override
    public String toString() {
        return "Score{" +
                "date='" + date + '\'' +
                ", points='" + points + '\'' +
                ", questions='" + questions + '\'' +
                ", ID=" + ID +
                '}' + "\r";
    }
}
