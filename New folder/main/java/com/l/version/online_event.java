package com.l.version;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class online_event extends AppCompatActivity {

    ViewPager viewPager;
    customSwipeAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_event);
            viewPager = (ViewPager)findViewById(R.id.event_pager);
            adapter = new customSwipeAdapter(this);
            viewPager.setAdapter(adapter);
         }
}
