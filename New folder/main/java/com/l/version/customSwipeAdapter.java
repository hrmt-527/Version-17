package com.l.version;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nightonke.boommenu.Eases.Linear;

import org.w3c.dom.Text;

/**
 * Created by love_y on 26-07-2017.
 */

public class customSwipeAdapter extends PagerAdapter {
    private int []image_resources = {R.drawable.smash_n_leap,R.drawable.hunt_the_mania,R.drawable.kod_e_geek};
    private  String []events = {"Smash And Leap" , "Hunt Mania" , "techtonic"};
    private String []description =
            {"If you think you are a cyber legend, then this event tailor-made for you. Well, sure you won't be the only one thinking so, and that's what base the fun factor of this event.We have given some hint, find that hint and cross the level."
            ,"Coding sparks creativity, inspiration, and innovation but bid farewell to the orthodox coding practices in Version'17. Think hard, scrabble your mind and fortify yourself to race against time. Test your coding skills on an online platform and compete against the world to know thy self!"
            ,"It is a fun exercise to work with others and it feels exciting to compete with others. The hunt challenges your ability to see and notice things that we all might normally just overlook in our busy lives. Take time to appreciate some of these things that are hidden in plain sight."
            };
    private String[] tagline = {"Explore Beyond Limit" , "Clear Vision Holds The Key" , "Think Before You Click"};
    private String[] rules = {"Online Registration is compulsory.\n" +
            "\n" +
            "Each level must be crossed prior to be able to access any further levels.\n" +
            "\n" +
            "Multiple participants from a single college can participate.\n" +
            "\n" +
            "There will be only 2 overall winners,and the winners will be from different colleges.","It is an online event.\n" +
            "\n" +
            "The participant will have to unlock the treasure hidden behind the images.\n" +
            "\n" +
            "The team at the highest level will be declared the winner.\n" +
            "\n" +
            "The decision of the judges will be final and binding.",
            "It is an online event.\n" +
                    "\n" +
                    "Participants will have to perform coding on codechef.\n" +
                    "\n" +
                    "To participate on codechef, They need to register on codechef website.\n"+
                    "\n"+
                    "There will be only 2 overall winners,and the winners will be from different colleges.","It is an online event.\n"

    };
    private String[] judging = {
            "for Example, there are 5 people on top 5 ranks on the leaderboard but from the same college, so only the 1st top rank player from that college will be awarded the 1st Prize, and the second prize will go to the topmost ranking participant from the other college in the leaderboard.\n" +
                    "\n" +
                    "In a case of the tie one who submitted earlier will be the winner.\n" +
                    "\n" +
                    "The decision of the organizing team will be final and abiding.\n" +
                    "\n" +
                    "For Further Qeries CONTACT:    7276997800,8148893181",
            "Winner will be decided on the basis of score he/she is getting on the LeaderBoard\n" +
                    "\n" +
                    "From one college only one person will be declared as the winner.\n" +
                    "\n" +
                    "There will only be First and Second Prize for the first and second position respectively.",
            "for Example, there are 5 people on top 5 ranks on the leaderboard but from the same college, so only the 1st top rank player from that college will be awarded the 1st Prize, and the second prize will go to the topmost ranking participant from the other college in the leaderboard.\n" +
                    "\n" +
                    "In a case of the tie one who submitted earlier will be the winner.               "
    };
         private String []nxt = {"swipe for next","swipe for next"," "};
            private Context context;
    private LayoutInflater layoutInflater;
    public customSwipeAdapter (Context ctx)
    {
        this.context = ctx;
    }
    @Override
    public int getCount() {
        return image_resources.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (LinearLayout)object);
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.event_swipe,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.event_image);
        TextView textView = (TextView)view.findViewById(R.id.event_name);
        TextView descript = (TextView)view.findViewById(R.id.description);
        TextView tag = (TextView)view.findViewById(R.id.tagline);
        TextView rule = (TextView)view.findViewById(R.id.rules);
        TextView judge = (TextView)view.findViewById(R.id.judging);
        TextView next = (TextView)view.findViewById(R.id.swipe);
        next.setText(nxt[position]);
        imageView.setImageResource(image_resources[position]);
        textView.setText(events[position]);
        descript.setText(description[position]);
        tag.setText(tagline[position]);
        rule.setText(rules[position]);
        judge.setText(judging[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
