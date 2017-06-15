package com.example.bal_esspasova.compostingapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    int listLocation;
    Boolean matchItem;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mButton = (Button) findViewById(R.id.enterButton);
        mEdit = (EditText) findViewById(R.id.editText);
        mText = (TextView) findViewById(R.id.textView);


        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String txt = mEdit.getText().toString(); //Get txt from et when button is clicked
                mText.setText(txt);
                searchWord(txt);

            }
        });
    }

    public String[] searchWord(String text) {

        if (text != null) {
            for (int i = 0; i < itemList.size(); i++) {
                String[] itemArray = itemList.get(i);
                if (itemArray[0].equals(text)) {
                    matchItem = true;
                    listLocation = i;
                    break;
                }
            }
            //user searched for an item
            if (matchItem) {
                return itemList.get(listLocation);
                //display that items info based off the value of listLocation
            } else {
                for (int i = 0; i < itemList.size(); i++) {
                    String[] itemArray = itemList.get(i);
                    for (int j = 0; j < itemArray.length - 1; j++) {
                        //user searched for a restaurant
                        if (itemArray[2].equals(text)) {
                            matchItem = true;
                            listLocation = i;
                            break;
                        }

                    }
                }
                if(matchItem){

                }
            }
        }
        return itemList.get(listLocation);
    }
}