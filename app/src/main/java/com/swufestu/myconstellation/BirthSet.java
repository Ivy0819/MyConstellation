package com.swufestu.myconstellation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class BirthSet extends AppCompatActivity {

    public final String TAG = "ChangeBirthPage";

    // 定义5个记录当前时间的变量
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_birth_set);

        DatePicker datePicker = (DatePicker)
                findViewById(R.id.datepicker);
        TimePicker timePicker = (TimePicker)
                findViewById(R.id.timepicker);
        timePicker.setIs24HourView(true);

        // 获取当前的年、月、日、小时、分钟
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        hour = c.get(Calendar.HOUR);
        minute = c.get(Calendar.MINUTE);

        // 初始化DatePicker组件，初始化时指定监听器
        datePicker.init(year, month, day, new DatePicker.OnDateChangedListener()
        {

            @Override
            public void onDateChanged(DatePicker arg0, int year, int month, int day)
            {
                BirthSet.this.year = year;
                BirthSet.this.month = month;
                BirthSet.this.day = day;
            }
        });
        // 为TimePicker指定监听器
        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener()
        {

            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute)
            {
                BirthSet.this.hour = hourOfDay;
                BirthSet.this.minute = minute;
            }
        });

    }

    public void Change_Birth(View btn){
        Log.i(TAG, "Change_birth: ");

        if (btn.getId() == R.id.Changed){
            SharedPreferences sp = getSharedPreferences("mybirth", Activity.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putInt("year", year);
            editor.putInt("month", month+1);
            editor.putInt("day", day);
            editor.putInt("hour", hour);
            editor.putInt("minute", minute);
            editor.apply();

            Log.i(TAG,"保存生日本地成功");
            Toast.makeText(BirthSet.this,"您选择的日期："+year+"年  "
                    +(month+1)+"月  "+day+"日"+hour+"时"+minute+"分保存成功", Toast.LENGTH_SHORT).show();

            Intent first = new Intent(this, MainActivity.class);
            setResult(2,first);
            finish();
        }
        else if (btn.getId() == R.id.UnChanged){
            finish();
        }
    }


}