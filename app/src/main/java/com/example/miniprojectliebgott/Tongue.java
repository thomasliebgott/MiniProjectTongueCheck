package com.example.miniprojectliebgott;

public class Tongue {
    private String feeling;
    private String date;
    private String typeOfTongue;

    public Tongue(String feeling, String day, String typeOfTongue) {
        this.feeling = feeling;
        this.date = day;
        this.typeOfTongue = typeOfTongue;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String nameUser) {
        this.feeling = nameUser;
    }

    public String getDay() {
        return date;
    }

    public void setdate(String date) {
        this.date = date;
    }

    public String getTypeOfTongue() {
        return typeOfTongue;
    }

    public void setTypeOfTongue(String typeOfTongue) {
        this.typeOfTongue = typeOfTongue;
    }
}
