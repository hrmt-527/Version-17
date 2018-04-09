package com.l.version;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class submission_event extends AppCompatActivity {
    ViewPager viewPager;
    customSwipeAdapter_submission adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submission_event);
        viewPager = (ViewPager)findViewById(R.id.submission_pager);
        adapter = new customSwipeAdapter_submission(this);
        viewPager.setAdapter(adapter);


    }
}
