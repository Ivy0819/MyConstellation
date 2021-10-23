package com.swufestu.myconstellation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Adapter_All extends ArrayAdapter {
    public Adapter_All(@NonNull Context context, int resource, @NonNull ArrayList<String> data) {
        super(context, resource, data);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if(itemView == null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_all_item,
                    parent,
                    false);
        }
        String stella =(String) getItem(position);

        ImageView stella_image = (ImageView) itemView.findViewById(R.id.stella_image);
        TextView stella_name = (TextView) itemView.findViewById(R.id.stella_name);

        stella_name.setText(stella);
        switch (stella){
            case "白羊座": {
                    stella_image.setBackgroundResource(R.drawable.aries);
                    break;
            }
            case "金牛座": {
                    stella_image.setBackgroundResource(R.drawable.taurus);

                    break;

            }
            case "双子座": {
                    stella_image.setBackgroundResource(R.drawable.gemini);

                    break;

            }
            case "巨蟹座": {
                    stella_image.setBackgroundResource(R.drawable.cancer);

                    break;

            }
            case "狮子座":{
                    stella_image.setBackgroundResource(R.drawable.leo);

                    break;

            }
            case "处女座": {
                    stella_image.setBackgroundResource(R.drawable.virgo);

                    break;

            }
            case "天秤座": {
                    stella_image.setBackgroundResource(R.drawable.libra);

                     break;

            }
            case "天蝎座": {
                    stella_image.setBackgroundResource(R.drawable.scorpio);

                    break;

            }
            case "射手座" : {
                    stella_image.setBackgroundResource(R.drawable.sagittarius);

                    break;

            }
            case "摩羯座": {
                    stella_image.setBackgroundResource(R.drawable.capricorn);

                    break;

            }
            case "水瓶座": {
                    stella_image.setBackgroundResource(R.drawable.aquarius);

                break;

            }
            case "双鱼座": {
                    stella_image.setBackgroundResource(R.drawable.pisces);

                    break;

            }
        }



        return itemView;
    }
}
