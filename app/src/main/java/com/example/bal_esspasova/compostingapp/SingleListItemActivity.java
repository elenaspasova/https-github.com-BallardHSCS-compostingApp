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

//this class is launched immediately when How to Compost is clicked
//it's function is to populate the listView for the first time
public class SingleListItemActivity extends AppCompatActivity {

    ListView listView;
    ItemArrayAdapter itemArrayAdapter;
    private List<String[]> itemList = new ArrayList();
    Button editText;

    @Override
    //when created, single_item_layout is set as content view
    //single item layout includes search bar, enter key, choice of filters, headings for data, and all the data displayed in order alphabetically by name
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_item_layout);

        editText = (Button) findViewById(R.id.enterButton);
        listView = (ListView) findViewById(R.id.single_item_list_view);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.compostableitemsbyitem);

        CSVReader csv = new CSVReader(inputStream);
        itemList = csv.read();

        for (String[] itemData : itemList) {
            itemArrayAdapter.add(itemData);
        }
        Intent intent = new Intent(this, newSearchActivity.class);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);


    }
}
