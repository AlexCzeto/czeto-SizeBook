package com.czeto.czeto_sizebook;

import java.util.Date;

/**
 * Created by Alex on 2/3/2017.
 */

/*
* Person
* Represents a single person entry.
* Holds all data about a given person.
* Has a getter and setter for every variable.
* Over rides toString so to format seen in the MainActivity.
 */

public class Person {
    private String name;
    private Date date;
    private Double neck;
    private Double bust;
    private Double chest;
    private Double waist;
    private Double hip;
    private Double inseam;
    private String comment ;


    public Person(String name, Date date,Double neck, Double bust, Double chest, Double waist, Double hip, Double inseam, String comment) {
        this.date = date;
        this.neck = neck;
        this.name = name;
        this.bust = bust;
        this.chest = chest;
        this.waist = waist;
        this.hip = hip;
        this.inseam = inseam;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Double getInseam() {
        return inseam;
    }

    public void setInseam(Double inseam) {
        this.inseam = inseam;
    }

    public Double getHip() {
        return hip;
    }

    public void setHip(Double hip) {
        this.hip = hip;
    }

    public Double getWaist() {
        return waist;
    }

    public void setWaist(Double waist) {
        this.waist = waist;
    }

    public Double getChest() {
        return chest;
    }

    public void setChest(Double chest) {
        this.chest = chest;
    }

    public Double getBust() {
        return bust;
    }

    public void setBust(Double bust) {
        this.bust = bust;
    }

    public Double getNeck() {
        return neck;
    }

    public void setNeck(Double neck) {
        this.neck = neck;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString(){
        String listItem = this.name;
        if (this.bust != 0){
            listItem = listItem + String.format("\nBust Size : %1$.1f",this.bust);
        }
        if (this.chest != 0){
            listItem = listItem + String.format("\nChest Size : %1$.1f",this.chest);
        }
        if (this.waist != 0){
            listItem = listItem + String.format("\nWaist Size : %1$.1f",this.waist);
        }
        if (this.inseam != 0){
            listItem = listItem + String.format("\nInseam Size : %1$.1f",this.inseam);
        }

        return listItem;
    }

}
