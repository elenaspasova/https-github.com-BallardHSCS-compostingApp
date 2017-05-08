package com.example.bal_esspasova.compostingapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.io.InputStream;
import java.util.List;

/**
 * Created by bal_esspasova on 5/8/2017.
 */

public class SingleListItem extends AppCompatActivity{

    private ListView listView;
    private ItemArrayAdapter itemArrayAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_item_layout);

        listView = (ListView) findViewById(R.id.list_view);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.single_item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.stats);
        CSVReader csv = new CSVReader(inputStream);
        List<String[]> itemList = csv.read();

        for(String [] itemData : itemList) {
            itemArrayAdapter.add(itemData);
        }
    }
}
