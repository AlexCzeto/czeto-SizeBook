package com.czeto.czeto_sizebook;

import java.util.Date;

/**
 * Created by Alex on 2/3/2017.
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


    public Person(String name, Double neck, Double bust, Double chest, Double waist, Double hip, Double inseam, String comment) {
        this.neck = neck;
        this.name = name;
        this.bust = bust;
        this.chest = chest;
        this.waist = waist;
        this.hip = hip;
        this.inseam = inseam;
        this.comment = comment;
    }
    public String textVersion(){
        return ("Name : "+this.name+" Neck : "+this.neck+" Bust : "+this.bust+" Chest : "+this.chest+" Waist : "+this.waist+" Hip : "+this.hip+" Inseam : "+this.inseam+" Comment : "+comment);
    }
}
