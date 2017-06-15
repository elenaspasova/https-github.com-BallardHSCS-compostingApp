package com.example.bal_esspasova.compostingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import static android.R.string.ok;

/**
 * Created by mykal on 6/14/2017.
 */

public class newSearchActivity extends AppCompatActivity {
    Button mButton;
    EditText mEdit;
    TextView mText;

    List<String[]> itemList;
    int itemLocation;
    int listLocation;
    int resLocation;
    Boolean matchItem;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mButton = (Button) findViewById(R.id.enterButton);
        mButton.setOnClickListener(enterButton);
        mEdit = (EditText) findViewById(R.id.editText);
        mText = (TextView) findViewById(R.id.textView);



    }
    private View.OnClickListener enterButton = new View.OnClickListener() {
        public void onClick(View v) {
            String txt = mEdit.getText().toString(); //Get txt from et when button is clicked
            mText.setText(txt);
            searchWord(txt);
        }
    };


    public String searchWord(String text) {

        if (text != null) {
            for (int i = 1; i < itemList.size()-1; i++) {
                String[] itemArray = itemList.get(i);
                if (itemArray[0].equals(text)) {
                    matchItem = true;
                    listLocation = i;
                    break;
                }
            }
            //user searched for an item
            if (matchItem) {
                itemLocation = listLocation;
                //display that items info based off the value of listLocation
            }
            else {
                for (int i = 1; i < itemList.size() - 1; i++) {
                    String[] itemArray = itemList.get(i);
                        //user searched for a restaurant
                    if (itemArray[2].equals(text)) {
                        matchItem = true;
                        listLocation = i;
                        break;
                        }

                    }
                    if(matchItem){
                        resLocation = listLocation;
                    }
                    else{
                        resLocation = 0;
                    }
                }



            }

        if(itemLocation != 0){
            return "item: " + itemLocation;
            //user typed an item that matches
        }
        else if(listLocation != 0){
            return "restaurant: " + listLocation;
            //user typed a restaurant that matches
        }
        else{
            return "no results were found";
            //user input matched nothing
        }

        }


    }
