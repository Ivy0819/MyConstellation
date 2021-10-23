package com.swufestu.myconstellation;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainPage";
    int year,month,day,hour,minute;
    TextView birth_show,Stella,onStella,bar_text;
    MyBirth myBirth;
    ImageButton Hide_btn;
    ImageButton Stella_btn,onStella_btn;
    ImageView home,back,pencil_change;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        birth_show = findViewById(R.id.birth_show);
        Stella = findViewById(R.id.stella);
        onStella = findViewById(R.id.onstella);
        Stella_btn = findViewById(R.id.imageButton);
        onStella_btn = findViewById(R.id.imageButton2);
        Hide_btn = findViewById(R.id.hideButton);
        Hide_btn.setBackgroundResource(R.mipmap.show);
        bar_text = findViewById(R.id.bar_text);
        bar_text.setText("星座运势");
        home = findViewById(R.id.bar_home);
        home.setVisibility(View.INVISIBLE);
        back = findViewById(R.id.bar_back);
        back.setVisibility(View.INVISIBLE);
        pencil_change = findViewById(R.id.pencil_change);


        load_Birth();



    }

    //加载本地生日
    private void load_Birth() {
        SharedPreferences sp = getSharedPreferences("mybirth", Activity.MODE_PRIVATE);

        year = sp.getInt("year", 1970);
        month = sp.getInt("month",1);
        day = sp.getInt("day",1);
        hour = sp.getInt("hour",1);
        minute = sp.getInt("minute",1);
        Log.i(TAG,"读取生日成功");
        myBirth = new MyBirth(year,month,day,hour,minute);

        if (Hide_btn.getContentDescription().equals("show")){
            birth_show.setText(year+"年"+month+"月"+day+"日"+hour+"时"+minute+"分");

        }
        Stella.setText("太阳星座"+myBirth.getStella());
        getButtonSet(myBirth.getStella(), 1);
        onStella.setText("上升星座"+myBirth.getOnStella());
        getButtonSet(myBirth.getOnStella(), 0);
    }

    //通过生日设置星座名和图片
    private void getButtonSet(String stella,int k){
        switch (stella){
            case "白羊座": {
                if (k == 1) {
                    Stella_btn.setBackgroundResource(R.drawable.aries);
                } else {
                    onStella_btn.setBackgroundResource(R.drawable.aries);
                }
                break;
            }
            case "金牛座": {
                if (k == 1) {
                    Stella_btn.setBackgroundResource(R.drawable.taurus);
                } else {
                    onStella_btn.setBackgroundResource(R.drawable.taurus);
                }
                break;

            }
            case "双子座": {
                if (k == 1) {
                    Stella_btn.setBackgroundResource(R.drawable.gemini);
                } else {
                    onStella_btn.setBackgroundResource(R.drawable.gemini);
                }
                break;

            }
            case "巨蟹座": {
                if (k == 1) {
                    Stella_btn.setBackgroundResource(R.drawable.cancer);
                } else {
                    onStella_btn.setBackgroundResource(R.drawable.cancer);
                }
                break;

            }
            case "狮子座":{
                if (k == 1) {
                    Stella_btn.setBackgroundResource(R.drawable.leo);
                } else {
                    onStella_btn.setBackgroundResource(R.drawable.leo);
                }
                break;

            }
            case "处女座": {
                if (k == 1) {
                    Stella_btn.setBackgroundResource(R.drawable.virgo);
                } else {
                    onStella_btn.setBackgroundResource(R.drawable.virgo);
                }
                break;

            }
            case "天秤座": {
                if (k == 1) {
                    Stella_btn.setBackgroundResource(R.drawable.libra);
                } else {
                    onStella_btn.setBackgroundResource(R.drawable.libra);
                }
                break;

            }
            case "天蝎座": {
                if (k == 1) {
                    Stella_btn.setBackgroundResource(R.drawable.scorpio);
                } else {
                    onStella_btn.setBackgroundResource(R.drawable.scorpio);
                }
                break;

            }
            case "射手座" : {
                if (k == 1) {
                    Stella_btn.setBackgroundResource(R.drawable.sagittarius);
                } else {
                    onStella_btn.setBackgroundResource(R.drawable.sagittarius);
                }
                break;

            }
            case "摩羯座": {
                if (k == 1) {
                    Stella_btn.setBackgroundResource(R.drawable.capricorn);
                } else {
                    onStella_btn.setBackgroundResource(R.drawable.capricorn);
                }
                break;

            }
            case "水瓶座": {
                if (k == 1) {
                    Stella_btn.setBackgroundResource(R.drawable.aquarius);
                } else {
                    onStella_btn.setBackgroundResource(R.drawable.aquarius);
                }
                break;

            }
            case "双鱼座": {
                if (k == 1) {
                    Stella_btn.setBackgroundResource(R.drawable.pisces);
                } else {
                    onStella_btn.setBackgroundResource(R.drawable.pisces);
                }
                break;

            }
        }
    }

    //生日的隐藏与显示
    public void hide(View view){
        ImageButton btn = (ImageButton) view;
        if (btn.getContentDescription().equals("show")){
            btn.setContentDescription("hide");
            birth_show.setText("***");
            btn.setBackgroundResource(R.mipmap.hide);
            pencil_change.setVisibility(View.INVISIBLE);
        }
        else {
            btn.setContentDescription("show");
            birth_show.setText(year+"年"+month+"月"+day+"日"+hour+"时"+minute+"分");
            btn.setBackgroundResource(R.mipmap.show);
            pencil_change.setVisibility(View.VISIBLE);
        }

    }

    //修改生日之后的显示
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == 1 && resultCode == 2){
            Log.i(TAG, "onActivityResult: 更改日期");
            load_Birth();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    //跳转到修改生日界面
    public  void Change2BirthSet(View btn){
        Intent birthset = new Intent(this,BirthSet.class);
        startActivityForResult(birthset, 1);
    }
    //跳转到运势界面
    public void Change2Detail(View btn){
        Log.i(TAG, "Change2Detail: ");
        String stella_change,str;
        switch (btn.getId()){
            case R.id.imageButton:
                str = Stella.getText().toString();
                break;
            case R.id.imageButton2:
                str = onStella.getText().toString();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + btn.getId());
        }
        stella_change = str.substring(4,7);
        Log.i(TAG, "Change2Detail: stella = "+stella_change);

        Intent detailset = new Intent(this,Detail.class);
        detailset.putExtra("stella",stella_change);
        startActivityForResult(detailset, 3);


    }
    //跳转到所有星座界面
    public void Change2All(View btn){
        Log.i(TAG, "Change2ALL: ");

        ArrayList<String> all_stella = new ArrayList<String>();

        all_stella.add("白羊座");
        all_stella.add("金牛座");
        all_stella.add("双子座");
        all_stella.add("巨蟹座");
        all_stella.add("狮子座");
        all_stella.add("处女座");
        all_stella.add("天秤座");
        all_stella.add("天蝎座");
        all_stella.add("射手座");
        all_stella.add("摩羯座");
        all_stella.add("水瓶座");
        all_stella.add("双鱼座");


        Intent allStellaSet = new Intent(this,All_Stella.class);
        allStellaSet.putExtra("all_stella",all_stella);
        startActivityForResult(allStellaSet, 5);


    }


}