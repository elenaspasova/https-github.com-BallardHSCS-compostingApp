package com.example.bal_esspasova.compostingapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.view.menu.MenuView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bal_esspasova on 5/8/2017.
 */

//this array adapter is necessary for the information in our csv files to come to life
//ie be organized into arrays and thus be able to be displayed
public class ItemArrayAdapter extends ArrayAdapter<String[]>{

    //constructors
    private List<String[]> scoreList = new ArrayList<String[]>();

    static class ItemViewHolder{
        TextView itemName;
        TextView bin;
        TextView location;
    }

    public ItemArrayAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    /**
     * method adds strings to the scoreList array
     */
    public void add(String[] object) {
        scoreList.add(object);
        super.add(object);
    }

    @Nullable
    @Override
    /**
     * gets the item at a given position in scoreList array
     */
    public String[] getItem(int position) {
        return this.scoreList.get(position);
    }

    @NonNull
    @Override
    /**
     * populates the listView in item_layout.xml so users can see the items from our csv files and they can scroll through them
     */
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //inflates the listView item_layout because that is the one where it is desirable to put our data into
            //once the listView is filled with data from scoreList it can be displayed
            row = inflater.inflate(R.layout.item_layout, parent, false);
            viewHolder = new ItemViewHolder();
            viewHolder.itemName = (TextView) row.findViewById(R.id.itemName);
            viewHolder.bin = (TextView) row.findViewById(R.id.bin);
            viewHolder.location = (TextView) row.findViewById(R.id.location);
            row.setTag(viewHolder);

        }
        else{
            viewHolder = (ItemViewHolder) row.getTag();
        }
        String[] stat = getItem(position);

        //one line of our data includes three items arranged horizontally
        //the first part of the line is the name of the item located at index 0
        //second is the proper bin for the item, index 1
        //last is the location from which the item comes from, index 2
        //.setText populates the listView so users can see the data from our csv file
        //this data represents sets of items, bins, and locations
        viewHolder.itemName.setText(stat[0]);
        viewHolder.bin.setText(stat[1]);
        viewHolder.location.setText(stat[2]);
        return row;
    }
}
