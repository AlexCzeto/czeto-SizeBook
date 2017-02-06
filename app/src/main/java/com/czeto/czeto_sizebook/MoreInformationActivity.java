package com.czeto.czeto_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/*
* Displays all data about a selected person.
* Handles if use chooses to edit or delete this specific entry.
*
 */

public class MoreInformationActivity extends AppCompatActivity {

    private PeopleList entryList;
    private Person oldFriend;
    private int index;

    @Override

    /*
    * Displays and formats all data of a specific person using TextViews.
    * If a given field is set to zero, it is ignored.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_information);

        this.entryList = PeopleList.getInstance();

        //Taken from http://stackoverflow.com/questions/5265913/how-to-use-putextra-and-getextra-for-string-data
        // 2017-02-04 23:00:00
        Bundle extras = getIntent().getExtras();
        this.index = extras.getInt("INDEX");

        this.oldFriend = this.entryList.getPerson(index);

        final TextView nameText = (TextView) findViewById(R.id.nameView);
        final TextView dateText = (TextView) findViewById(R.id.dateView);
        final TextView neckText = (TextView) findViewById(R.id.neckView);
        final TextView bustText = (TextView) findViewById(R.id.bustView);
        final TextView chestText = (TextView) findViewById(R.id.chestView);
        final TextView waistText = (TextView) findViewById(R.id.waistView);
        final TextView hipText  = (TextView) findViewById(R.id.hipView);
        final TextView inseamText = (TextView) findViewById(R.id.inseamView);
        final TextView commentText = (TextView) findViewById(R.id.commentView);

        nameText.setText(oldFriend.getName());
        dateText.setText(oldFriend.getDate().toString());

        if (!oldFriend.getComment().isEmpty()){
            commentText.setText(oldFriend.getComment());
        }

        if(oldFriend.getNeck() != 0){
            neckText.setText(String.format("Neck circumfrenece in inches : %1$.1f",oldFriend.getNeck()));
        }

        if(oldFriend.getBust() != 0){
            bustText.setText(String.format("Bust circumfrenece in inches : %1$.1f",oldFriend.getBust()));
        }

        if(oldFriend.getChest() != 0){
            chestText.setText(String.format("Chest circumfrenece in inches : %1$.1f",oldFriend.getChest()));
        }

        if(oldFriend.getWaist() != 0){
            waistText.setText(String.format("Waist circumfrenece in inches : %1$.1f",oldFriend.getWaist()));
        }

        if(oldFriend.getHip() != 0){
            hipText.setText(String.format("Hip circumfrenece in inches : %1$.1f",oldFriend.getHip()));
        }

        if(oldFriend.getInseam() != 0){
            inseamText.setText(String.format("Inseam in inches : %1$.1f",oldFriend.getInseam()));
        }

    }

    /*
    * Calls the list of people to handle a deletion of a person
    * Intents back to main activity
     */
    public void delete(View v){
        this.entryList.deleteFromList(this.index,this);
        Intent i = new Intent(this,MainActivity.class);
        startActivity(i);
        finish();
    }

    /*
    * Sends index of specific person the user is viewing to the EditExistingEntryActivity
     */
    public void moveToEditEntry(View v){
        Intent i = new Intent(this,EditExistingEntryActivity.class);
        i.putExtra("INDEX",index);
        startActivity(i);
        finish();
    }
}
