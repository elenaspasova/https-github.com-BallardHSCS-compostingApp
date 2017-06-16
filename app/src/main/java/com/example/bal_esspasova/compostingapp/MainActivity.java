package com.example.bal_esspasova.compostingapp;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

//this is the first activity that runs and it opens the main menu as well as detects button clicks for which page to inflate next
public class MainActivity extends AppCompatActivity {

    //constructing buttons
    Button editText;
    Button editText2;

    @Override
    //on create the activity_main layout inflates
    //the layout includes two buttons that lead to other activities and pages and an imageView of Bucky the Beaver (*AKA Elena in stuffy mascot suit*)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (Button) findViewById(R.id.button);
        editText2 = (Button) findViewById(R.id.button2);
    }


    /**
     * Called when the user taps the Map button
     * launches an intent to launch the next activity which generates the map layout
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        String message = editText2.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    /**
     * Called when the user taps the How to Compost button
     * launches an intent to launch the next activity which generates the listView layout of all the items in our csv file
     */
    public void openGuide(View view) {
        Intent intent = new Intent(this, SingleListItemActivity.class);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
