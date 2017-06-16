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

/**
 * Created by mykal on 6/14/2017.
 */

//this class is launched after the user presses How to Compost on the main menu
    //this class detects several button pushes including those that handle the filtering of data, and start search
public class newSearchActivity extends AppCompatActivity {
    //lots of variables
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
    int binLocation;
    Boolean matchItem = false;

    Button editText;

    /**
     * Called when the activity is first created.
     * onCreate launches the page's layout
     * creates four buttons: three for filtering, one is the enter button the user presses when they want to search for an item
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

        //the initial filter is the byItem filter
        //alphabetizes by item's name
        filter = R.raw.compostableitemsbyitem;
        createTheArray(filter);
    }

    /**
     * there are 3 csv files each corresponding with a desired filter
     * createTheArray needs to repopulate the listView from SingleListActivity in case the user changes the filter
     *
     * @param filter accepts the int value of a csv file in the raw directory
     *
     */
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

    /**
     * if enter button is clicked, searchWord runs in attempt to find the word's position in the itemList
     */
    private View.OnClickListener enterButton = new View.OnClickListener() {
        public void onClick(View v) {
            String txt = mEdit.getText().toString(); //Get txt from et when button is clicked
            mText.setText(txt);
            searchWord(txt, itemList);

        }
    };

    //detects if user chooses to filter by Item Name
    //calls on createTheArray to repopulate the listView with a new csv file, meaning changed settings
        private View.OnClickListener filterByItem = new View.OnClickListener() {
        public void onClick(View v) {
            filter = R.raw.compostableitemsbyitem;
            createTheArray(filter);
        }
    };

    //detects if user chooses to filter by Bin Type
    //calls on createTheArray to repopulate the listView with a new csv file, meaning changed settings
        private View.OnClickListener filterByBin = new View.OnClickListener() {
        public void onClick(View v) {
            filter = R.raw.compostableitemsbybin;
            createTheArray(filter);
        }
    };

    //detects if user chooses to filter by Location
    //calls on createTheArray to repopulate the listView with a new csv file, meaning changed settings
    private View.OnClickListener filterByLocation = new View.OnClickListener() {
        public void onClick(View v) {
            filter = R.raw.compostableitemsbylocation;
            createTheArray(filter);
        }
    };



    /**
     *
     * @param text the user input into the search bar immediately prior to pressing Enter
     * @param itemList the entire list of data according to the desired filter
     * @return
     */
    public void searchWord(String text, List<String[]> itemList) {

        if (text != null) {
            for (int i = 0; i < itemList.size(); i++) {
                String[] itemArray = itemList.get(i);
                if (itemArray[0].equals(text)) {
                    matchItem = true;
                    listLocation = i;
                    break;
                }
                else{
                    matchItem = false;
                }
            }
            //user searched for an item
            if (matchItem) {
                itemLocation = listLocation;
                resLocation = -1;
                binLocation = -1;
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
                        binLocation = -1;
                    }
                    else{
                        for (int i = 0; i < itemList.size(); i++) {
                            String[] itemArray = itemList.get(i);
                            //user searched for a restaurant
                            if (itemArray[1].trim().equals(text)) {
                                matchItem = true;
                                listLocation = i;
                                break;
                            }

                        }
                        if(matchItem){
                            binLocation = listLocation;
                            resLocation = -1;
                            itemLocation = -1;
                        }
                        else{
                            binLocation = -1;
                            resLocation = -1;
                            itemLocation = -1;
                        }


                    }
                }



            }

        if(itemLocation != -1){

            Intent intent = new Intent(newSearchActivity.this, SearchItemResults.class);
            Bundle b = new Bundle();
            b.putInt("itemlocation", itemLocation);
            b.putInt("reslocation", resLocation);
            b.putInt("filter", filter);
            intent.putExtras(b);
            mEdit.setText("");
            startActivity(intent);
//            return "item: " + itemLocation;
//            user typed an item that matches
        }
        else if(resLocation != -1){
            Intent intent = new Intent(newSearchActivity.this, SearchItemResults.class);
            Bundle b = new Bundle();
            b.putInt("reslocation", resLocation);
            b.putInt("itemlocation", itemLocation);
            b.putInt("filter", filter);
            intent.putExtras(b);
            mEdit.setText("");
            startActivity(intent);
            //return "restaurant: " + listLocation;
            //user typed a restaurant that matches
        }
        else if(binLocation != -1){
            Intent intent = new Intent(newSearchActivity.this, SearchItemResults.class);
            Bundle b = new Bundle();
            b.putInt("reslocation", resLocation);
            b.putInt("itemlocation", itemLocation);
            b.putInt("binlocation", binLocation);
            intent.putExtras(b);
            mEdit.setText("");
            startActivity(intent);
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
