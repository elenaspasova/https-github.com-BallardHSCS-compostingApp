package com.example.bal_esspasova.compostingapp;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;

import java.util.List;

/**
 * Created by bal_mcsheldon on 4/28/2017.
 */

public class SearchActivity extends AppCompatActivity{
    List<String[]> itemList;
    int listLocation;
    Boolean matchItem;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            //use the query to search your data somehow
            String userInput = query.trim();

            if(userInput != null){
                for(int i = 0; i < itemList.size(); i++){
                    String[] itemArray = itemList.get(i);
                    if(itemArray[0].equals(userInput)){
                        matchItem = true;
                        listLocation = i;
                        break;
                    }
                }
                //user searched for an item
                if(matchItem){
                    String[] itemArray = itemList.get(listLocation);
                    //display that items info based off the value of listLocation
                }
                else{
                    for(int i = 0; i < itemList.size(); i++){
                        String[] itemArray = itemList.get(i);
                        for(int j = 0; j < itemArray.length -1; j++){
                            if(itemArray[2].equals(userInput)){
                                matchItem = true;
                                listLocation = i;
                                break;
                        }

                        }
                    }
                    //display item info
                }
            }

        }
    }
}

