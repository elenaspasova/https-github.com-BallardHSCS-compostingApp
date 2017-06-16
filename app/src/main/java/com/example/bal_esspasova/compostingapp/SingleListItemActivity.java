package com.example.bal_esspasova.compostingapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by bal_esspasova on 5/8/2017.
 */

public class SingleListItemActivity extends AppCompatActivity{

    ListView listView;
    ItemArrayAdapter itemArrayAdapter;
    private List<String[]> itemList = new ArrayList();
    Button editText;
    int filter;
//    Button itemFilter;
//    Button binFilter;
//    Button locationFilter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_item_layout);
//        itemFilter = (Button) findViewById(R.id.nameFilter);
//        itemFilter.setOnClickListener(filterByItem);
//        binFilter = (Button) findViewById(R.id.binFilter);
//        binFilter.setOnClickListener(filterByBin);
//        locationFilter = (Button) findViewById(R.id.locationFilter);
//        locationFilter.setOnClickListener(filterByLocation);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        editText = (Button) findViewById(R.id.enterButton);
        listView = (ListView) findViewById(R.id.single_item_list_view);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.compostableitemsbyitem);

        CSVReader csv = new CSVReader(inputStream);
        itemList = csv.read();

        for(String [] itemData : itemList) {
            itemArrayAdapter.add(itemData);
        }
        Intent intent = new Intent(this, newSearchActivity.class);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
//        filter = R.raw.compostableitemsbyitem;
//        createTheArray(filter);


    }

//    private View.OnClickListener filterByItem = new View.OnClickListener() {
//        public void onClick(View v) {
//            createTheArray(R.raw.compostableitemsbyitem);
//        }
//    };

//    public void filterByBin(View view) {
//        createTheArray(R.raw.compostableitemsbybin);
//    }

//    private View.OnClickListener filterByBin = new View.OnClickListener() {
//        public void onClick(View v) {
//            createTheArray(R.raw.compostableitemsbybin);
//        }
//    };
//
//    private View.OnClickListener filterByLocation = new View.OnClickListener() {
//        public void onClick(View v) {
//
//        }
//    };

//    private void createTheArray(int filter)
//    {
//        editText = (Button) findViewById(R.id.enterButton);
//        listView = (ListView) findViewById(R.id.single_item_list_view);
//        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);
//
//        Parcelable state = listView.onSaveInstanceState();
//        listView.setAdapter(itemArrayAdapter);
//        listView.onRestoreInstanceState(state);
//
//        InputStream inputStream = getResources().openRawResource(filter);
//
//        CSVReader csv = new CSVReader(inputStream);
//        itemList = csv.read();
//
//        for(String [] itemData : itemList) {
//            itemArrayAdapter.add(itemData);
//        }
//        Intent intent = new Intent(this, newSearchActivity.class);
//        String message = editText.getText().toString();
//        intent.putExtra(EXTRA_MESSAGE, message);
//        startActivity(intent);
//    }



}
