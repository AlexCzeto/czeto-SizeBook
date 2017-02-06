package com.czeto.czeto_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.text.ParseException;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

/*
* AddNewEntryActivity
* Takes in user entered data about a person and sends it to the person constructor.
 */
public class AddNewEntryActivity extends AppCompatActivity {

    private PeopleList EntryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("tag"," Even Less Hello");
        setContentView(R.layout.activity_add_new_entry);
        this.EntryList = PeopleList.getInstance();
    }

    /*
    * Called when finish button is clicked.
    * Parses each field individually. If the name field is left blank , this activity remains open
    * and no new person is created.
    * If any field has data that can not be parsed this activity remains open, no new person is
    * created, and a toast error message pops up letting the user know which fields caused the
    * error.
    * If any of the size fields are left blank they will not be parsed and instead set to zero.
    * If the date field is left blank it will be set to today's date.
    * If a new entry is correctly entered , it will intent back to the main page.
     */
    public void addNewEntry(View v){
        Log.d("Hello"," Even More Hello");

        final EditText nameText = (EditText) findViewById(R.id.name);
        final EditText neckText = (EditText) findViewById(R.id.neck);
        final EditText bustText = (EditText) findViewById(R.id.bust);
        final EditText chestText = (EditText) findViewById(R.id.chest);
        final EditText waistText = (EditText) findViewById(R.id.waist);
        final EditText hipText = (EditText) findViewById(R.id.hip);
        final EditText inseamText = (EditText) findViewById(R.id.inseam);
        final EditText commentText = (EditText) findViewById(R.id.comment);
        final EditText dateText = (EditText) findViewById(R.id.date);

        Person newFriend = null;
        Boolean cleanData = true;
        String name = nameText.getText().toString();
        String comment = commentText.getText().toString();
        Double neckCir = 0.0;
        Double bustCir = 0.0;
        Double chestCir = 0.0;
        Double waistCir = 0.0;
        Double hipCir = 0.0;
        Double inseam = 0.0;
        Date date = new Date();

        if(!neckText.getText().toString().isEmpty()) {
            try {
                neckCir = Double.parseDouble(neckText.getText().toString());
            } catch (NumberFormatException e) {
                String errorString = "Neck circumference entered incorrectly.";
                Toast errorMessage = Toast.makeText(this, errorString, Toast.LENGTH_SHORT);
                errorMessage.show();
                cleanData = Boolean.FALSE;
            }
        }
        if(!bustText.getText().toString().isEmpty()) {
            try {
                bustCir = Double.parseDouble(bustText.getText().toString());
            } catch (NumberFormatException e) {
                String errorString = "Bust circumference entered incorrectly.";
                Toast errorMessage = Toast.makeText(this, errorString, Toast.LENGTH_SHORT);
                errorMessage.show();
                cleanData = Boolean.FALSE;
            }
        }

        if(!chestText.getText().toString().isEmpty()) {
            try {
                chestCir = Double.parseDouble(bustText.getText().toString());
            } catch (NumberFormatException e) {
                String errorString = "Chest circumference entered incorrectly.";
                Toast errorMessage = Toast.makeText(this, errorString, Toast.LENGTH_SHORT);
                errorMessage.show();
                cleanData = Boolean.FALSE;
            }
        }
        if(!waistText.getText().toString().isEmpty()) {
            try {
                waistCir = Double.parseDouble(waistText.getText().toString());
            } catch (NumberFormatException e) {
                String errorString = "Waist circumference entered incorrectly.";
                Toast errorMessage = Toast.makeText(this, errorString, Toast.LENGTH_SHORT);
                errorMessage.show();
                cleanData = Boolean.FALSE;
            }
        }

        if(!hipText.getText().toString().isEmpty()) {
            try {
                hipCir = Double.parseDouble(hipText.getText().toString());
            } catch (NumberFormatException e) {
                String errorString = "Hip circumference entered incorrectly.";
                Toast errorMessage = Toast.makeText(this, errorString, Toast.LENGTH_SHORT);
                errorMessage.show();
                cleanData = Boolean.FALSE;
            }
        }

        if(!inseamText.getText().toString().isEmpty()) {
            try {
                inseam = Double.parseDouble(inseamText.getText().toString());
            } catch (NumberFormatException e) {
                String errorString = "Inseam entered incorrectly.";
                Toast errorMessage = Toast.makeText(this, errorString, Toast.LENGTH_SHORT);
                errorMessage.show();
                cleanData = Boolean.FALSE;
            }
        }

        //Taken from https://www.mkyong.com/java/how-to-check-if-date-is-valid-in-java/
        // 2017-02-04 22:00
        if(!dateText.getText().toString().isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);

            try {
                date = sdf.parse(dateText.getText().toString());
            }
            catch (ParseException e){
                String errorString = "Date entered incorrectly.";
                Toast errorMessage = Toast.makeText(this, errorString, Toast.LENGTH_SHORT);
                errorMessage.show();
                cleanData = Boolean.FALSE;
            }

        }


        if(!name.isEmpty() && cleanData == Boolean.TRUE){
            newFriend = new Person(name,date,neckCir ,bustCir,chestCir,waistCir,hipCir,inseam,comment);
            EntryList.addToList(newFriend,this);
        }

        if(newFriend != null){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}
