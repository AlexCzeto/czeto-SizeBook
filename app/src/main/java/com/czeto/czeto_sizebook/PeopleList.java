package com.czeto.czeto_sizebook;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.ListIterator;

import static com.czeto.czeto_sizebook.MainActivity.FILENAME;


/**
 * Created by Alex on 2/3/2017.
 */

/*
* PeopleList
* Global class that only ever has one instance.
* Has an ArrayList of all user entered entries
* Handles the management of the people ArrayList
* Handles the saving of the list to a file.
*
 */

public class PeopleList {
    private ArrayList<Person> listOfPeople;
    private static PeopleList instance = null;

    private PeopleList() {
        this.listOfPeople = new ArrayList<Person>();
    }
    // Taken from : http://www.javaworld.com/article/2073352/core-java/simply-singleton.html
    // 2017-02-05 20:00
    // Makes sure only one instance of PeopleList exists
    public static PeopleList getInstance(){
        if(instance == null) {
            instance = new PeopleList();
        }
        return instance;
    }

    public ArrayList<Person> getListOfPeople(){
        return this.listOfPeople;
    }

    public void setListOfPeople(ArrayList<Person> newPeopleList){
        listOfPeople = newPeopleList;
    }

    public Person getPerson(int index){
        return listOfPeople.get(index);
    }

    public void addToList(Person newFriend,Context ctx){
        this.listOfPeople.add(newFriend);
        this.saveInFile(ctx);
    }

    public void deleteFromList(int index,Context ctx){
        this.listOfPeople.remove(index);
        this.saveInFile(ctx);
    }

    public int returnCount(){
        return this.listOfPeople.size();
    }

    // Taken from lonely Twitter
    public void saveInFile(Context ctx) {
        Log.d("Hello","Save Hello");
        try {
            FileOutputStream fos;
            fos = ctx.openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(listOfPeople,out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

}
