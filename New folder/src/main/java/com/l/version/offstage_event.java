package com.l.version;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class offstage_event extends AppCompatActivity {
    ViewPager viewPager;
    customSwipeAdapter_offstage adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offstage_event);
        viewPager = (ViewPager)findViewById(R.id.offstage_pager);
        adapter = new customSwipeAdapter_offstage(this);
        viewPager.setAdapter(adapter);

    }
}
