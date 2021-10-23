package com.swufestu.myconstellation;

import android.util.Log;

import java.util.HashMap;

public class MyBirth {
    private static final String TAG  = "MyBirthPage";
    int year,month,day,hour,minute;
    String Stella,onStella;
    HashMap<Integer, String> cal_1;

    public MyBirth(int year, int month, int day, int hour, int minute) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.cal_1 = getCal_1();
    }

    public HashMap<Integer, String> getCal_1() {
        HashMap<Integer, String> temp = new HashMap<Integer, String>();
        temp.put(1,"6：40");
        temp.put(2,"8：43");
        temp.put(3,"10：33");
        temp.put(4,"12：35");
        temp.put(5,"14：33");
        temp.put(6,"16：36");
        temp.put(7,"18：34");
        temp.put(8,"20：36");
        temp.put(9,"22：38");
        temp.put(10,"0：37");
        temp.put(11,"2：39");
        temp.put(12,"4：37");


        return temp;
    }

    /*获得太阳星座*/
    public String getStella() {
        switch (month){
            case 1:
                Stella=day<20?"摩羯座":"水瓶座";
                break;
            case 2:
                Stella=day<19? "水瓶座":"双鱼座";
                break;
            case 3:
                Stella=day<21?"双鱼座":"白羊座";
                break;
            case 4:
                Stella=day<20?"白羊座":"金牛座";
                break;
            case 5:
                Stella=day<21?"金牛座":"双子座";
                break;
            case 6:
                Stella=day<22?"双子座":"巨蟹座";
                break;
            case 7:
                Stella=day<23?"巨蟹座":"狮子座";
                break;
            case 8:
                Stella=day<23?"狮子座":"处女座";
                break;
            case 9:
                Stella=day<23?"处女座":"天秤座";
                break;
            case 10:
                Stella=day<24?"天秤座":"天蝎座";
                break;
            case 11:
                Stella=day<23?"天蝎座":"射手座";
                break;
            case 12:
                Stella=day<22?"射手座":"摩羯座";
                break;
        }


        return Stella;
    }

    public void setStella(String stella) {
        Stella = stella;
    }

    /*获得上升星座*/
    public String getOnStella() {
        String cal1 = cal_1.get(month);
        String[] strArr = cal1.split("：");
        int hour_1 = Integer.parseInt(strArr[0]);
        int minute_1 = Integer.parseInt(strArr[1]);

        //分位运算
        int sum = minute_1 + day*4 + minute;
        int digit_1 = sum%Integer.valueOf(60);
        int digit_1_re = sum/Integer.valueOf(60);
        Log.i(TAG, "getOnStella: digit_1="+digit_1);
        Log.i(TAG, "getOnStella: digit_1_re="+digit_1_re);

        //时位运算
        int digit_2 = (hour+hour_1+digit_1_re)%Integer.valueOf(24);
        Log.i(TAG, "getOnStella: digit_2="+digit_2);
        //上升星座判断
        int digit = digit_2*100+digit_1;
        Log.i(TAG, "getOnStella: digit = "+digit);
        if (digit>129&&digit<=346) onStella = "狮子座";
        else if (digit<=600) onStella="处女座";
        else if (digit<=813) onStella="天秤座";
        else if (digit<=1030) onStella="天蝎座";
        else if (digit<=1246) onStella="射手座";
        else if (digit<=1448) onStella="摩羯座";
        else if (digit<=1630) onStella="水瓶座";
        else if (digit<=1800) onStella="双鱼座";
        else if (digit<=1929) onStella="白羊座";
        else if (digit<=2111) onStella="金牛座";
        else if (digit<=2313) onStella="双子座";
        else  onStella="巨蟹座";


        return onStella;
    }

    public void setOnStella(String onStella) {
        this.onStella = onStella;
    }




}
