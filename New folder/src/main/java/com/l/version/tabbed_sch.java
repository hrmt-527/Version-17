package com.l.version;

import android.app.Activity;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class tabbed_sch extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_sch);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        String[] events = {"INAUGURATION", "PAPYRON (Finals)", "LUNCH","CODE BUZZ (Prelims + Finals)", "BIZMART (Prelims)", "CRACK BACK (Prelims)","QUIZLLER(Prelims)", "COGNITIION QUEST(Prelims)","INFORMALS","DINNER","KURUSHETRA","MYSTIC MOVER"," END OF DAY 0 "};
        String[] taglines = {"VERSION 2017 INAGURATION", "Let Your Mind Speak"," ", "Code Combat","Turning Ideas Into Action","Flip The Code", "Let's Get Quizzical", "Human Needs Techno Meets"," "," ","Shoot'em All", "Elixir Of Euphoria","  "};
        String[] venues = {"Venue : EEE", "Venue : EEE", " ", "Venue :CA DEPT LAB", "Venue :CA DEPT LAB", "Venue :CA DEPT LAB", "Venue :CA DEPT LAB", "Venue :CA DEPT LAB", "Venue : EEE"," ","Venue :CA DEPT LAB","Venue :CA DEPT LAB"," "};
        String[] timings =
                       {"8:30 AM - 11:00 AM",
                        "11:00 AM - 12:30 PM",
                        "12:30 PM - 1:30 PM",
                        "1:30 PM - 2:30 PM",
                        "2:30 PM - 3:15 PM",
                        "3:15 PM - 4:00 PM",
                               "4:00 PM - 4:45 PM",
                               "4:45 PM - 5:15 PM",
                               "5:30 PM - 8:00 PM",
                               "8:00 PM - 9:00 PM",
                               "10:00 PM - 11:30 PM",
                               "11:30 PM - 1:00 PM",
                       "    "};
        String[] event_s = {"CRACK BACK (Finals)", "COGNITION QUEST (Finals)", "QUIZLLER (Finals)","Lunch" ,"BIZMART(Finals)", "TRIUMPHER","Prize Distribution","Version'17 VIDEO","Vote of Thanks","  " };
        String[] tagline_s = {"Flip The Code", "Human Needs Techno Meets", "Let's Get Quizzical", " " ,"Turning Ideas Into Action", "The Ultimate Winner","","Made With Love By Team Version","Thank You"," "};
        String[] venue_s = {"Venue : CA DEPT LAB", "Venue : CA DEPT LAB", "Venue : EEE", " ","Venue : EEE", "Venue : EEE", "Venue : EEE", "Venue : EEE", "Venue : EEE"," "};
        String[] timing_s =
                {       "9:00 AM - 10:00 AM",
                        "10:00 AM - 11:00 AM",
                        "11:15 AM - 12:30 PM",
                        "12:30 PM - 01:30 PM",
                        "1:30 PM - 3:00 PM"
                        ,"3:00 PM - 4:30 PM",
                        "4:30 PM - 5:00 PM",
                        "5:00 PM - 5:15 PM",
                        "5:15 PM - 5:30 PM"," "};

        private static final String ARG_SECTION_NUMBER = "section_number";


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        //here we are switching the elements
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                View rootView = inflater.inflate(R.layout.tab_2, container, false);
                ListView listView = (ListView) rootView.findViewById(R.id.tab_3);
                Custom_Adapter customAdapter = new Custom_Adapter();
                listView.setAdapter(customAdapter);
                return rootView;
            } else {

                View rootView = inflater.inflate(R.layout.tab_1, container, false);
                ListView listView = (ListView) rootView.findViewById(R.id.tab_2);
                CustomAdapter customAdapter = new CustomAdapter();
                listView.setAdapter(customAdapter);
                return rootView;
            }
        }

        class CustomAdapter extends BaseAdapter {

            @Override
            public int getCount() {
                return events.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {
                view = getLayoutInflater().inflate(R.layout.schedule_item, null);
                TextView event = (TextView) view.findViewById(R.id.event_name);
                TextView tagline = (TextView) view.findViewById(R.id.tagline);
                TextView venue = (TextView) view.findViewById(R.id.venue);
                TextView timing = (TextView) view.findViewById(R.id.timing);
                event.setText(events[position]);
                tagline.setText(taglines[position]);
                timing.setText(timings[position]);
                venue.setText(venues[position]);
                return view;
            }

            private LayoutInflater getLayoutInflater() {

                LayoutInflater mInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                return mInflater;
            }

        }
        class Custom_Adapter extends BaseAdapter {

            @Override
            public int getCount() {
                return event_s.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View view, ViewGroup parent) {
                view = getLayoutInflater().inflate(R.layout.schedule_item, null);
                TextView event = (TextView) view.findViewById(R.id.event_name);
                TextView tagline = (TextView) view.findViewById(R.id.tagline);
                TextView venue = (TextView) view.findViewById(R.id.venue);
                TextView timing = (TextView) view.findViewById(R.id.timing);
                event.setText(event_s[position]);
                tagline.setText(tagline_s[position]);
                timing.setText(timing_s[position]);
                venue.setText(venue_s[position]);
                return view;
            }

            private LayoutInflater getLayoutInflater() {

                LayoutInflater mInflater = (LayoutInflater) getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                return mInflater;
            }

        }
    }


        /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "DAY 0";
                case 1:
                    return "DAY 1";
              }
            return null;
        }
    }
}
