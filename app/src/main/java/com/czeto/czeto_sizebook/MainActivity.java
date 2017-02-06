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

public class MainActivity extends AppCompatActivity {

    // Lonely Twitter
    private PeopleList entryList;
    private ListView displayedPeople;
    private ArrayAdapter<Person> adapter;
    public static final String FILENAME = "pfile.sav";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        this.entryList = PeopleList.getInstance();
        loadFromFile();


    }

    //From Lonely Twitter
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



        //displayedPeople.setOnItemClickListener();
        // TODO Auto-generated method stub

    }

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

    public void moveAddEntry(View v){
        Intent i = new Intent(this,AddNewEntryActivity.class);
        startActivity(i);
        finish();
    }

    public void moveToMoreInformation(View v,int index){
        Intent i = new Intent(this,MoreInformationActivity.class);
        i.putExtra("INDEX",index);
        startActivity(i);
    }
}
