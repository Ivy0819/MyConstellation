package com.swufestu.myconstellation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends ArrayAdapter {
    private static final String TAG = "MyAdapter";
    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Item> data) {
        super(context, resource,data);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,
                    parent,
                    false);
        }

        Item map = (Item) getItem(position);
        TextView title = (TextView) itemView.findViewById(R.id.itemFortune);
        TextView detail = (TextView) itemView.findViewById(R.id.itemDetail);
        title.setText(map.getInfo());
        detail.setText(map.getIntro());

        return  itemView;
    }
}
