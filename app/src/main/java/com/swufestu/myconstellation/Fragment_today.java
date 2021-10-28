package com.swufestu.myconstellation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashMap;

public class Fragment_today extends Fragment {
    ArrayList<Item> rlist_get;
    private static final String TAG = "DetailPage" ;
    ListView list2;
    Handler handler;
    MyAdapter myAdapter;

    public Fragment_today(ArrayList<Item> rlist_get) {
        this.rlist_get = rlist_get;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_fragment,container,false);
        Log.i(TAG, "handleMessage: test,rlist="+rlist_get.get(1).getIntro());
        list2 = view.findViewById(R.id.list2);
        myAdapter = new MyAdapter(getActivity(), R.layout.list_item, rlist_get);
        Log.i(TAG, "onCreateView: fragment运行");
        list2.setAdapter(myAdapter);

        return view;
    }
}
