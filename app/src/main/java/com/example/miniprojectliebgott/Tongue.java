package com.example.miniprojectliebgott;

public class Tongue {
    private String feeling;
    private String date;
    private String typeOfTongue;

    //contructor of the class Tongue
    public Tongue(String feeling, String day, String typeOfTongue) {
        this.feeling = feeling;
        this.date = day;
        this.typeOfTongue = typeOfTongue;
    }
    //getteur of the text feeling
    public String getFeeling() {
        return feeling;
    }
    //setteur of the text feeling
    public void setFeeling(String nameUser) {
        this.feeling = nameUser;
    }
    //getteur of the day
    public String getDay() {
        return date;
    }
    //setteur of the date
    public void setdate(String date) {
        this.date = date;
    }
    //getteur of the text feeling
    public String getTypeOfTongue() {
        return typeOfTongue;
    }
    //setteur of the type of tongue
    public void setTypeOfTongue(String typeOfTongue) {
        this.typeOfTongue = typeOfTongue;
    }
}
