package com.l.version;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by love_y on 14-07-2017.
 */

public class Tab_2_Fragment extends Fragment implements View.OnClickListener {

    String []events = {"Inaguration" ,"Seminar" , "Papayron" , "Code Buzz" , "Bizzmart" , "Quizller" ,"Cognition Quest" ,"Mystic Mover"};
    String  [] taglines = {"VERSION 2017 INAGURATION","MACHINE LEARNING SEMINNAR","Paper Presentation","Coding","Marketing","Quiz","Maths Quiz","Surprise"};
    String [] venues = {"EEE" ,"EEE","EEE","DEPT LAB","DEPT LAB","DEPT LAB","DEPT LAB","DEPT LAB" ,"DEPT LAB"};
    String  [] timings =
            {"8:30 AM  - 9:30 AM",
            "9:30 AM  - 11:00 AM",
            "11:00 AM  - 12:30 PM", "2:00 PM  - 3:00 PM",
            "3:00 PM  -3:45 PM",
                    "3:45 PM  - 4:15 PM",
                    "4:15 PM  - 4:45 PM",
                    "8:30 PM  - 9:30 PM"};
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     View view = inflater.inflate(R.layout.tab_1,container , false);
        ListView listView = (ListView)view.findViewById(R.id.tab_2);
        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {

    }
    class CustomAdapter extends BaseAdapter
    {

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
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = getLayoutInflater().inflate(R.layout.schedule_item, null);
            TextView event =(TextView)view.findViewById(R.id.event_name);
            TextView tagline =(TextView)view.findViewById(R.id.tagline);
            TextView venue =(TextView)view.findViewById(R.id.venue);
            TextView timing =(TextView)view.findViewById(R.id.timing);
            event.setText(events[position]);
            tagline.setText(taglines[position]);
            timing.setText(timings[position]);
            venue.setText(venues[position]);
            return view;
        }

        private LayoutInflater getLayoutInflater() {

            LayoutInflater mInflater = (LayoutInflater)getActivity().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            return  mInflater;
        }

    }
}
