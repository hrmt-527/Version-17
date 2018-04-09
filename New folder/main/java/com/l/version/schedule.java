package com.l.version;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by love_y on 14-07-2017.
 */

public class schedule extends Fragment {

   private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.schedule,container,false);


        tabLayout  = (TabLayout)view.findViewById(R.id.tab);
        viewPager = (ViewPager)view.findViewById(R.id.pager);
        /*Creating the adapter and setting the adapter to the viewPager
        getSupportActionBar takes the toolbar and set it as the default action bar
        thus making the toolbar works like a normal action bar*/
        adapter = new ViewPagerAdapter(getFragmentManager());
        viewPager.setAdapter(adapter);


        final TabLayout.Tab test_1 = tabLayout.newTab().setText("Day 1");
        final TabLayout.Tab test_2 = tabLayout.newTab().setText("Day 2");

        tabLayout.addTab(test_1,0);
        tabLayout.addTab(test_2,1);

        tabLayout.setTabTextColors(ContextCompat.getColorStateList(getActivity(),R.color.tab_selector));
        tabLayout.setSelectedTabIndicatorHeight(ContextCompat.getColor(getActivity(),R.color.colorAccent));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener()
                                          {@Override public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


                                          }

                                              @Override
                                              public void onPageSelected(int position) {
                                                  switch (position)
                                                  {
                                                      case 0:
                                                          break;
                                                      case 1:
                                                          break;
                                                  }
                                              }

                                              @Override
                                              public void onPageScrollStateChanged(int state) {

                                              }
                                          }
        );
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Schedule");
        super.onViewCreated(view, savedInstanceState);
    }
}
