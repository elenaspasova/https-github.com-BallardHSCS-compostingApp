package com.example.bal_esspasova.compostingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

import static android.R.attr.filter;
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

    Button itemFilter;
    Button binFilter;
    Button locationFilter;
    int filter;


    List<String[]> itemList;
    int itemLocation;
    int listLocation;
    int resLocation;
    Boolean matchItem;

    Button editText;

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


        itemFilter = (Button) findViewById(R.id.nameFilter);
        itemFilter.setOnClickListener(filterByItem);
        binFilter = (Button) findViewById(R.id.binFilter);
        binFilter.setOnClickListener(filterByBin);
        locationFilter = (Button) findViewById(R.id.locationFilter);
        locationFilter.setOnClickListener(filterByLocation);

        filter = R.raw.compostableitemsbyitem;
        createTheArray(filter);
    }

    private void createTheArray(int filter)
    {
        editText = (Button)findViewById(R.id.enterButton);
        listView = (ListView) findViewById(R.id.single_item_list_view);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(filter);

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

        private View.OnClickListener filterByItem = new View.OnClickListener() {
        public void onClick(View v) {
            createTheArray(R.raw.compostableitemsbyitem);
        }
    };

        private View.OnClickListener filterByBin = new View.OnClickListener() {
        public void onClick(View v) {
            createTheArray(R.raw.compostableitemsbybin);
        }
    };

    private View.OnClickListener filterByLocation = new View.OnClickListener() {
        public void onClick(View v) {
            createTheArray(R.raw.compostableitemsbylocation);
        }
    };


    public String searchWord(String text, List<String[]> itemList) {

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
