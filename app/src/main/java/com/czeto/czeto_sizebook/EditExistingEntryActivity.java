package com.czeto.czeto_sizebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* Shows user editable space with all existing information about a person.
 */
public class EditExistingEntryActivity extends AppCompatActivity {

    private int index;
    private PeopleList entryList;
    private Person oldFriend;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    /*
    *  Displays existing information about selected person in editable text fields
    *  If a size is set to zero , the field is not altered.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_existing_entry);
        final EditText nameText = (EditText) findViewById(R.id.name);
        final EditText neckText = (EditText) findViewById(R.id.neck);
        final EditText chestText = (EditText) findViewById(R.id.chest);
        final EditText hipText = (EditText) findViewById(R.id.hip);
        final EditText waistText = (EditText) findViewById(R.id.waist);
        final EditText bustText = (EditText) findViewById(R.id.bust);
        final EditText inseamText = (EditText) findViewById(R.id.inseam);
        final EditText commentText = (EditText) findViewById(R.id.comment);
        final EditText dateText = (EditText) findViewById(R.id.date);

        this.entryList = PeopleList.getInstance();

        //Taken from http://stackoverflow.com/questions/5265913/how-to-use-putextra-and-getextra-for-string-data
        // 2017-02-04 23:00:00
        Bundle extras = getIntent().getExtras();
        this.index = extras.getInt("INDEX");
        this.oldFriend = this.entryList.getPerson(index);

        nameText.setText(this.oldFriend.getName());

        sdf.setLenient(false);
        dateText.setText(sdf.format(oldFriend.getDate()));

        if (!oldFriend.getComment().isEmpty()){
            commentText.setText(oldFriend.getComment());
        }

        if(oldFriend.getNeck() != 0){
            neckText.setText(String.format("%1$.3f",oldFriend.getNeck()));
        }

        if(oldFriend.getBust() != 0){
            bustText.setText(String.format("%1$.3f",oldFriend.getBust()));
        }

        if(oldFriend.getChest() != 0){
            chestText.setText(String.format("%1$.3f",oldFriend.getChest()));
        }

        if(oldFriend.getWaist() != 0){
            waistText.setText(String.format("%1$.3f",oldFriend.getWaist()));
        }

        if(oldFriend.getHip() != 0){
            hipText.setText(String.format("%1$.3f",oldFriend.getHip()));
        }

        if(oldFriend.getInseam() != 0){
            inseamText.setText(String.format("%1$.3f",oldFriend.getInseam()));
        }

    }

    /*
    * Called when finish button is clicked.
    * Checks over data in the EditText views, whether it has been altered or not.
    * If the data is parsable, considered clean and will change the person object.
    * If field , other than name or date, is blank , it will be set to zero.
    * If a field is not able to be parsed, error message displayed for user.
     */
    public void alterEntry(View v){
        final EditText nameText = (EditText) findViewById(R.id.name);
        final EditText neckText = (EditText) findViewById(R.id.neck);
        final EditText chestText = (EditText) findViewById(R.id.chest);
        final EditText hipText = (EditText) findViewById(R.id.hip);
        final EditText waistText = (EditText) findViewById(R.id.waist);
        final EditText bustText = (EditText) findViewById(R.id.bust);
        final EditText inseamText = (EditText) findViewById(R.id.inseam);
        final EditText commentText = (EditText) findViewById(R.id.comment);
        final EditText dateText = (EditText) findViewById(R.id.date);

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

//        //https://www.mkyong.com/java/how-to-check-if-date-is-valid-in-java/
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
//
        }

        if(!name.isEmpty() && cleanData == Boolean.TRUE){
            oldFriend.setName(name);
            oldFriend.setBust(bustCir);
            oldFriend.setChest(chestCir);
            oldFriend.setHip(hipCir);
            oldFriend.setWaist(waistCir);
            oldFriend.setInseam(inseam);
            oldFriend.setNeck(neckCir);
            oldFriend.setComment(comment);
            oldFriend.setDate(date);


            finish();
        }
    }
}
