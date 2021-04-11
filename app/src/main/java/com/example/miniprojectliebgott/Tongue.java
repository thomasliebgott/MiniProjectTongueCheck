package com.example.miniprojectliebgott;

public class Tongue {
    private String feeling;
    private String day;
    private String typeOfTongue;

    public Tongue(String feeling, String day, String typeOfTongue) {
        this.feeling = feeling;
        this.day = day;
        this.typeOfTongue = typeOfTongue;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String nameUser) {
        this.feeling = nameUser;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTypeOfTongue() {
        return typeOfTongue;
    }

    public void setTypeOfTongue(String typeOfTongue) {
        this.typeOfTongue = typeOfTongue;
    }
}
