package com.example.bal_esspasova.compostingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import static android.R.string.ok;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by mykal on 6/14/2017.
 */

public class newSearchActivity extends AppCompatActivity {
    Button mButton;
    EditText mEdit;
    TextView mText;
    ListView listView;
    ItemArrayAdapter itemArrayAdapter;

    private List<String[]> itemList;
    private int itemLocation;
    int listLocation;
    int resLocation;
    Boolean matchItem = false;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_item_layout);
        mButton = (Button) findViewById(R.id.enterButton);
        mButton.setOnClickListener(enterButton);
        mEdit = (EditText) findViewById(R.id.editText);
        mText = (TextView) findViewById(R.id.textView);

        listView = (ListView) findViewById(R.id.single_item_layout);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.single_item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.compostableitems);

        CSVReader csv = new CSVReader(inputStream);
        itemList = csv.read();

        for(String [] itemData : itemList) {
            itemArrayAdapter.add(itemData);
        }
    }

    private View.OnClickListener enterButton = new View.OnClickListener() {
        public void onClick(View v) {
            String txt = mEdit.getText().toString(); //Get txt from et when button is clicked
            mText.setText(txt);
            searchWord(txt, itemList);

        }
    };


    public void searchWord(String text, List<String[]> itemList) {

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
                itemLocation = listLocation;
                resLocation = -1;
                //display that items info based off the value of listLocation
            }
            else if(!matchItem) {
                for (int i = 0; i < itemList.size(); i++) {
                    String[] itemArray = itemList.get(i);
                        //user searched for a restaurant
                    if (itemArray[2].trim().equals(text)) {
                        matchItem = true;
                        listLocation = i;
                        break;
                        }

                }
                    if(matchItem){
                        resLocation = listLocation;
                        itemLocation = -1;
                    }
                    else{
                        resLocation = -1;
                        itemLocation = -1;
                    }
                }



            }

        if(itemLocation != -1){

            Intent intent = new Intent(newSearchActivity.this, SearchItemResults.class);
            Bundle b = new Bundle();
            b.putInt("itemlocation", itemLocation);
            b.putInt("reslocation", resLocation);
            intent.putExtras(b);
            startActivity(intent);
//            return "item: " + itemLocation;
//            user typed an item that matches
        }
        else if(resLocation != -1){
            Intent intent = new Intent(newSearchActivity.this, SearchItemResults.class);
            Bundle b = new Bundle();
            b.putInt("reslocation", resLocation);
            b.putInt("itemlocation", itemLocation);
            intent.putExtras(b);
            startActivity(intent);
            //return "restaurant: " + listLocation;
            //user typed a restaurant that matches
        }
        else{
            LinearLayout lView3 = new LinearLayout(this);
            setContentView(lView3);
            TextView noResult = new TextView(this);
            noResult.setText("No results were found!! Try again.");
            noResult.setTextSize(getResources().getDimension(R.dimen.search_result_res_size));
            lView3.addView(noResult);
            //return "no results were found";
            //user input matched nothing
        }

        }


    }
