package com.l.version;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class onstage_event extends AppCompatActivity {
    ViewPager viewPager;
    customSwipeAdapter_onstage adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onstage);
        viewPager = (ViewPager)findViewById(R.id.onstage_pager);
        adapter = new customSwipeAdapter_onstage(this);
        viewPager.setAdapter(adapter);
    }
}
