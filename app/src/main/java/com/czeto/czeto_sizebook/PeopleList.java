package com.czeto.czeto_sizebook;

import android.util.Log;

import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Created by Alex on 2/3/2017.
 */

// http://www.javaworld.com/article/2073352/core-java/simply-singleton.html <- Singleton

public class PeopleList {
    private ArrayList<Person> listOfPeople;
    private static PeopleList instance = null;

    private PeopleList() {
        this.listOfPeople = new ArrayList<Person>();
    }

    public static PeopleList getInstance(){
        if(instance == null) {
            instance = new PeopleList();
        }
        return instance;
    }

    public ArrayList<Person> getListOfPeople(){
        return this.listOfPeople;
    }

    public void addToList(Person newFriend){
        this.listOfPeople.add(newFriend);
    }

    //http://stackoverflow.com/questions/18410035/ways-to-iterate-over-a-list-in-java
    public void printPL(){
        Person memberOfList;
        for (ListIterator<Person> iter = this.listOfPeople.listIterator(); iter.hasNext(); ) {
            memberOfList = iter.next();
            Log.d("tag", memberOfList.textVersion());
        }
    }
}
