package com.example.android.myshopapplistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Context context, List<Item> items) {
        super(context, R.layout.row_layout, R.id.nameText, items );
    }


    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        //build view from layout file
        View view = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);
        //get code ref to each ui element we need
        TextView price = view.findViewById(R.id.priceText);
        TextView name = view.findViewById(R.id.nameText);
        TextView description = view.findViewById(R.id.descriptionText);

        //get current list item
        Item item = getItem(position);

        price.setText(String.format("%.2f", item.getPrice()));
        name.setText(item.getName());
        description.setText(item.getDescription());


        return view;
    }
}
