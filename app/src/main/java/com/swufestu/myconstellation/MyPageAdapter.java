package com.swufestu.myconstellation;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MyPageAdapter extends FragmentStateAdapter {
    HashMap<String, ArrayList<Item>> rlist;

    public MyPageAdapter(@NonNull FragmentActivity fa, HashMap<String, ArrayList<Item>> rlist){
        super(fa);
        this.rlist = rlist;
    }



    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position==0){
            return new Fragment_today((ArrayList<Item>)rlist.get("Array0"));
        }else if(position==1){
            return new Fragment_today((ArrayList<Item>)rlist.get("Array1"));
        }else{
            return new Fragment_today((ArrayList<Item>)rlist.get("Array2"));
        }
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}