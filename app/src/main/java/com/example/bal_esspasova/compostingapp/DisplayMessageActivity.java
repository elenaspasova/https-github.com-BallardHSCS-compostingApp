package com.example.bal_esspasova.compostingapp;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

//this class corresponds with the map page of our activity which shows:
//a map of the school with dots for bins
//buttons to choose whether to look at the first or second floor
//a color coordinated key for the map
public class DisplayMessageActivity extends AppCompatActivity{

    @Override
    /**
     * upon creation of the activity, the layout it set to the desired layout for the map
     * the buttons and their setOnClickListeners are also activated
     * the last thing it does is call a method to set the initial map image which is to be an image of level 1 of the school
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_message);

        Button level1 = (Button)findViewById(R.id.level1);
        Button level2 = (Button)findViewById(R.id.level2);
        level1.setOnClickListener(changeToFirstFloor);
        level2.setOnClickListener(changeToSecondFloor);

        setInitialImage();
    }

    /**
     * displays the first floor image on the screen
     * is the initial image when the map screen is launched
     */
    private void setInitialImage() {
        final ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
        imageView.setImageResource(R.drawable.floor1);

    }

    /**
     * displays the first floor image on the screen
     * runs upon clicking of the "FIRST FLOOR" button
     */
    private View.OnClickListener changeToFirstFloor = new View.OnClickListener() {
        public void onClick(View v) {
            final ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
            imageView.setImageResource(R.drawable.floor1);
        }
    };

    /**
     * displays the second floor image on the screen
     * runs upon clicking of the "SECOND FLOOR" button
     */
    private View.OnClickListener changeToSecondFloor = new View.OnClickListener() {
        public void onClick(View v) {
            final ImageView imageView = (ImageView) findViewById(R.id.imageDisplay);
            imageView.setImageResource(R.drawable.floor2);
        }
    };
}
