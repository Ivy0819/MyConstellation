package com.swufestu.myconstellation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.HashMap;

public class Fortune_Detail extends AppCompatActivity {

    String TAG = "Fortune_DetailPage";
    ImageView home,back;
    TextView bar_text;
    String stella;
    Intent intent;
    Handler handler;
    HashMap<String,ArrayList<Item>> rlist = new HashMap<String,ArrayList<Item>>();
    ViewPager2 viewPager;
    TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fortune_detail);
        home = findViewById(R.id.bar_home);
        back = findViewById(R.id.bar_back);
        bar_text = findViewById(R.id.bar_text);
        CustomProgressDialog customProgressDialog = new CustomProgressDialog(this, "加载数据中...");

        //获得其他页面传来的数据
        intent = getIntent();
        stella = intent.getStringExtra("stella");
        bar_text.setText(stella+"运势查询");
        Log.i(TAG, "onCreate: 接受参数 stella="+stella);

        //转盘等待显示
        customProgressDialog.show();

        //开启线程,获得MyThread_getFortune传来的数据
        MyThread_getFortune td = new MyThread_getFortune(stella);
        Log.i(TAG, "onCreate:开启线程");

        handler = new Handler(Looper.myLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                Log.i(TAG, "handleMessage: 收到消息");
                if (msg.what == 9){
                    rlist = (HashMap<String, ArrayList<Item>>) msg.obj;
                    Log.i(TAG, "handleMessage: 获取的rlist的size为："+rlist.size());
                    //配置viewpage
                    viewPager = findViewById(R.id.viewpager2);
                    MyPageAdapter pageAdapter = new MyPageAdapter(Fortune_Detail.this,rlist);
                    viewPager.setAdapter(pageAdapter);
                    Log.i(TAG, "handleMessage: 配置pageadapter成功");
                    //配置layout
                    tabLayout = findViewById(R.id.tab_layout);
                    TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout,viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
                        @Override
                        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                            switch (position){
                                case 0:
                                    tab.setText("今日运势");
                                    break;
                                case 1:
                                    tab.setText("明日运势");
                                    break;
                                case 2:
                                    tab.setText("本周运势");
                                    break;
                            }
                        }
                    });
                    tabLayoutMediator.attach();
                }
                //取消转盘等待显示
                customProgressDialog.dismiss();
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