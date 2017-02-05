package com.czeto.czeto_sizebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private PeopleList EntryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.EntryList = PeopleList.getInstance();
    }

    public void moveToEdit(View v){
        Intent i = new Intent(this,AddNewEntryActivity.class);
        startActivity(i);
    }
}
