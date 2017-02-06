package com.czeto.czeto_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

/*
* Main Activity
*
* Acts as main page of the app.
* Handles the display of the people entries in a list format.
* Loads list from file and updates adapter.
* Moves to other activities based on user selection.
 */
public class MainActivity extends AppCompatActivity {
    private PeopleList entryList;
    private ListView displayedPeople;
    private ArrayAdapter<Person> adapter;
    public static final String FILENAME = "pfile.sav";


    /*
    * Handles the set up of the app.
    * Makes sure the list of people is instantiated and is filled with people from file.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.entryList = PeopleList.getInstance();
        loadFromFile();


    }

    //From Lonely Twitter

    /*
    * Handles main activity updates.
    * Sets up list adapter, and moves to more information activity if a list item is selected
    * Updates count of the number of entries.
     */
    protected void onStart() {

        super.onStart();
        displayedPeople = (ListView) findViewById(R.id.displayedPeople);

        adapter = new ArrayAdapter<Person>(this,
                R.layout.person_description, entryList.getListOfPeople());
        displayedPeople.setAdapter(adapter);

        displayedPeople.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                moveToMoreInformation(view ,position);
            }
        });

        adapter.notifyDataSetChanged();

        final TextView countText = (TextView) findViewById(R.id.count);
        int count = entryList.returnCount();
        countText.setText("Number of Entries : " + Integer.toString(count));


    }

    /*
    * Loads people from file into the people array
    * People are stored as Gson type
    * Loadformfile function taken from lonelyTwitter
    *
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            // Taken from http://stackoverflow.com/questions/27014417/how-to-use-gson-to-convert-json-to-arraylist-if-the-list-contain-different-class
            // 2017-01-24 18:19
            Type listType = new TypeToken<ArrayList<Person>>(){}.getType();
            ArrayList<Person> tempPeopleList = gson.fromJson(in,listType);
            entryList.setListOfPeople(tempPeopleList);

        } catch (FileNotFoundException e) {
            this.entryList = PeopleList.getInstance();
        } catch (IOException e) {
            // TODO FIX
            throw new RuntimeException();
        }
    }

    /*
    * Intents into the AddNewEntryActivity
     */
    public void moveAddEntry(View v){
        Intent i = new Intent(this,AddNewEntryActivity.class);
        startActivity(i);
        //finish();
    }

    /*
    * Intents into the MoreInformationActivity
    * Adds index of the person that user wants more information on from the people list as extra.
    */
    public void moveToMoreInformation(View v,int index){
        Intent i = new Intent(this,MoreInformationActivity.class);
        i.putExtra("INDEX",index);
        startActivity(i);
    }
}
