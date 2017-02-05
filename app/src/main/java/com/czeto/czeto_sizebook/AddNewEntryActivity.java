package com.czeto.czeto_sizebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AddNewEntryActivity extends AppCompatActivity {

    private PeopleList EntryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_entry);
        this.EntryList = PeopleList.getInstance();
    }

    public void addNewEntry(View v){
        final EditText nameText = (EditText) findViewById(R.id.name);
        final EditText neckText = (EditText) findViewById(R.id.neck);
        final EditText bustText = (EditText) findViewById(R.id.bust);
        final EditText chestText = (EditText) findViewById(R.id.chest);
        final EditText waistText = (EditText) findViewById(R.id.waist);
        final EditText hipText = (EditText) findViewById(R.id.hip);
        final EditText inseamText = (EditText) findViewById(R.id.inseam);
        final EditText commentText = (EditText) findViewById(R.id.comment);

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


        if(!name.isEmpty() && cleanData == Boolean.TRUE){
            newFriend = new Person(name,neckCir ,bustCir,chestCir,waistCir,hipCir,inseam,comment);
            Log.d("tag",newFriend.textVersion());
        }

        if(newFriend != null){
            finish();
        }
    }
}
