package com.swufestu.myconstellation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.jsoup.nodes.Document;

import java.util.ArrayList;

public class Detail extends AppCompatActivity {
    private static final String TAG = "DetailPage" ;
    String stella;
    ListView list2;
    Handler handler;
    ArrayList<Item> rlist = new ArrayList<Item>();
    MyAdapter myAdapter;
    ImageView home,back,nodata;
    TextView bar_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        list2 = findViewById(R.id.list2);
        home = findViewById(R.id.bar_home);
        back = findViewById(R.id.bar_back);
        bar_text = findViewById(R.id.bar_text);
        CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, "加载数据中...");


        Intent intent = getIntent();
        stella = intent.getStringExtra("stella");
        bar_text.setText(stella+"今日运势");
        Log.i(TAG, "onCreate: 接受参数 stella="+stella);
        customProgressDialog.show();


        //开启线程
        MyThread_getFortune td = new MyThread_getFortune(stella);
        Log.i(TAG, "onCreate:开启线程");

        //定义对象时不会调用非构造函数的方法
        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handleMessage: 收到消息");
                if (msg.what == 9){
                    rlist = (ArrayList<Item>) msg.obj;
                    Log.i(TAG, "handleMessage: rlist="+rlist);
                    myAdapter = new MyAdapter(Detail.this,
                            R.layout.list_item,
                            rlist);
                    list2.setAdapter(myAdapter);
                    list2.setEmptyView(findViewById(R.id.nodata));
                    customProgressDialog.dismiss();

                }

                super.handleMessage(msg);
            }
        };

        td.setHandler(handler);
        Thread t = new Thread(td);
        t.start();

    }
    public void homeClick(View btn){
        Intent goHome = new Intent(this,MainActivity.class);
        startActivity(goHome);
    }

    public void backClick(View btn){
        finish();
    }


}