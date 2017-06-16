package com.example.bal_esspasova.compostingapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

/**
 * Created by mykal on 6/15/2017.
 */

public class SearchItemResults extends AppCompatActivity {
    private List<String[]> itemList;
    int itemLocation;
    int listLocation;
    ListView listView;
    ItemArrayAdapter itemArrayAdapter;
    private TextView myText = null;
    private TextView myText2 = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_results);
        listView = (ListView) findViewById(R.id.search_results);
        itemArrayAdapter = new ItemArrayAdapter(getApplicationContext(), R.layout.single_item_layout);

        Parcelable state = listView.onSaveInstanceState();
        listView.setAdapter(itemArrayAdapter);
        listView.onRestoreInstanceState(state);

        InputStream inputStream = getResources().openRawResource(R.raw.compostableitems);

        CSVReader csv = new CSVReader(inputStream);
        itemList = csv.read();

        for (String[] itemData : itemList) {
            itemArrayAdapter.add(itemData);
        }


        Bundle b = this.getIntent().getExtras();
        int itemLocation = b.getInt("itemlocation");
        int resLocation = b.getInt("reslocation");
        if (itemLocation != -1) {
            String[] searchedItem = itemList.get(itemLocation);
            String itemName = searchedItem[0];
            String bin = searchedItem[1].trim();

            LinearLayout lView = new LinearLayout(this);
            setContentView(lView);

            myText = new TextView(this);

            String multiLineText = "" + "Item: " + itemName + "\n";
            //multiLineText += "\n";
            multiLineText += "Bin: " + bin + "\n";

            myText.setText(multiLineText);
            myText.setTextSize(getResources().getDimension(R.dimen.search_result_text_size));
            lView.addView(myText);
            if (bin.equals("Trash")) {
                ImageView myImage = new ImageView(this);
                myImage.setImageResource(R.drawable.garbage);
                lView.addView(myImage);
            } else if (bin.equals("Compost")) {
                ImageView myImage = new ImageView(this);
                myImage.setImageResource(R.drawable.compost_bin);
                lView.addView(myImage);
            } else if (bin.equals("Recycle")) {
                ImageView myImage = new ImageView(this);
                myImage.setImageResource(R.drawable.recycle);
                lView.addView(myImage);
            }
        } else {
            //resLocation = b.getInt("resLocation");

            if (resLocation != -1) {
                String[] searchedRes = itemList.get(resLocation);
                String resName = searchedRes[2].trim();
                LinearLayout lView2 = new LinearLayout(this);
                setContentView(lView2);
                myText2 = new TextView(this);
                myText2.setTextSize(getResources().getDimension(R.dimen.search_result_res_size));
                String multiLineText2 = "Items found at " + resName + "\n";
                multiLineText2 += "\n";
                for (int i = 0; i < itemList.size(); i++) {
                    String[] itemArray = itemList.get(i);
                    //user searched for a restaurant
                    if (itemArray[2].trim().equals(resName)) {
                        multiLineText2 += itemArray[0] + "      " + "Bin: " + itemArray[1] + "\n";
                        multiLineText2 += "\n";

                    }
                }
                    myText2.setText(multiLineText2);
                    myText2.setTextSize(getResources().getDimension(R.dimen.search_result_res_size));
                    lView2.addView(myText2);



            }
        }


    }

    }
