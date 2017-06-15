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

public class ItemArrayAdapter extends ArrayAdapter<String[]>{

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
    public void add(String[] object) {
        scoreList.add(object);
        super.add(object);
    }

    @Nullable
    @Override
    public String[] getItem(int position) {
        return this.scoreList.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ItemViewHolder viewHolder;
        if(row == null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_item_layout, parent, false);
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
        viewHolder.itemName.setText(stat[0]);
        viewHolder.bin.setText(stat[1]);
        viewHolder.location.setText(stat[2]);
        return row;
    }
}
