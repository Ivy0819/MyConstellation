package com.swufestu.myconstellation;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;

public class MyThread_getFortune implements Runnable{

    private static final String TAG = "ThreadPage";
    private Handler handler_show;
    Bundle bundle = new Bundle();
    HashMap<String,ArrayList<Item>> map = new HashMap<String,ArrayList<Item>>();
    public String Stella;
    private Document doc;
    private Elements tables;

    public MyThread_getFortune(String Stella_ch) {
        HashMap<String,String> ch2en = new HashMap<String,String>();
        ch2en.put("白羊座","aries");
        ch2en.put("金牛座","taurus");
        ch2en.put("双子座","gemini");
        ch2en.put("巨蟹座","cancer");
        ch2en.put("狮子座","leo");
        ch2en.put("处女座","virgo");
        ch2en.put("天秤座","libra");
        ch2en.put("天蝎座","scorpio");
        ch2en.put("射手座","sagittarius");
        ch2en.put("摩羯座","capricorn");
        ch2en.put("水瓶座","aquarius");
        ch2en.put("双鱼座","pisces");
        Stella = ch2en.get(Stella_ch);
        Log.i(TAG, "MyThread_getFortune: Stella_Eng = "+Stella);

       }



    public void setHandler(Handler handler) {
        this.handler_show = handler;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.i(TAG, "run: 线程运行");

        for (int i = 0;i < 3;i++){
            ArrayList<Item> listItems = new ArrayList<Item>();


            try {
                doc = Jsoup.connect("https://www.xzw.com/fortune/"+Stella+"/"+i).get();

                tables = doc.getElementsByTag("p");
            } catch (IOException e) {
                e.printStackTrace();
            }
            for (int j =0;j<tables.size();j++){
                String info = tables.get(j).getElementsByTag("strong").text();
                String intro;
                if (info.equals("综合运势")){
                    String intro_temp = tables.get(j).getElementsByTag("span").text();
                    intro = intro_temp.substring(0,intro_temp.length()-5);
                }
                else {
                    intro = tables.get(j).getElementsByTag("span").text();
                }
                Item item = new Item(" "+info+" ",intro);
                listItems.add(item);

            }
            map.put("Array"+i,listItems);
        }


        Message msg_show = handler_show.obtainMessage(9,map);
        handler_show.sendMessage(msg_show);
        Log.i(TAG, "run: 数据已发送给主线程DetailPage");

    }
}
