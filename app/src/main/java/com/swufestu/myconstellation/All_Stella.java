package com.swufestu.myconstellation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class All_Stella extends AppCompatActivity implements AdapterView.OnItemClickListener{
    ArrayList<String> all_stella = new ArrayList<String>();
    private static final String TAG = "AllStellaPage";
    Adapter_All adapter_all;
    GridView Grid2 ;
    ImageView home,back;
    TextView bar_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_stella);

        Grid2 = findViewById(R.id.myGridList);
        Grid2.setOnItemClickListener(this);//绑定监听
        home = findViewById(R.id.bar_home);
        back = findViewById(R.id.bar_back);
        bar_text = findViewById(R.id.bar_text);
        bar_text.setText("所有星座");


        Intent intent = getIntent();
        all_stella = intent.getStringArrayListExtra("all_stella");
        Log.i(TAG, "onCreate: all_stella="+all_stella);

        adapter_all = new Adapter_All(All_Stella.this,
                R.layout.list_all_item,
                all_stella);
        Grid2.setAdapter(adapter_all);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Log.i(TAG, "onItemClick: 监听Item成功");

        //获得Item星座
        Object itemAtPosition = Grid2.getItemAtPosition(position);
        String stella_name = (String)itemAtPosition;


        Log.i(TAG, "onItemClick: 监听到的stella_name="+stella_name);

        Intent detailset = new Intent(this,Detail.class);
        detailset.putExtra("stella",stella_name);
        startActivityForResult(detailset, 3);

    }

    public void homeClick(View btn){
        Intent goHome = new Intent(this,MainActivity.class);
        startActivity(goHome);
    }

    public void backClick(View btn){
        finish();
    }
}