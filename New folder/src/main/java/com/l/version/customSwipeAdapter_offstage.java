package com.l.version;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by love_y on 26-07-2017.
 */

public class customSwipeAdapter_offstage extends PagerAdapter {
    private int []image_resources = {R.drawable.crack_back,R.drawable.mystic_mover,R.drawable.code_buzz,R.drawable.maths_quiz,R.drawable.kurukshetra};
    private  String []events = {"Crack Back","Mystic Mover","Code Buzz","Cognition Quest","Kurushetra"};
    private String []description =
            {"The key to a man's success is always when he pushes his limits forward. But wait, is it always that easy? There is a twist in the tale. The code is flipped and you have to backtrack the code. So put on your thinking caps, find your way in the reverse direction and code."
                    ,"Surprise for You",
                    "Coding is just not about knowing a language. Problems lie in practical scenarios. Identifying and analyzing them is the real task. So, brush up your coding skills and get ready for some programming! Identify a solution for problems through coding.\n" +
                    "\n","Machine Learning is a skill assessment challenge. The aim of this challenge is to test your machine learning skills and validate how well equipped, are you? Automation has always been a driving force for technological advancements. Techniques like machine learning enable us to explore automation in every domain possible. As time goes on, you see machine learning everywhere from mobile personal assistants to recommendation systems in an e-commerce website. It's getting harder and harder to ignore this flourishing technology."
            ,"After all the stressful day, a peaceful sleep charges your mind. But we don’t get what we want, do we? A night event which will require some brainstorming with enjoyment as a flavor.\n" +
                    "\n"};
    private String[] tagline = {"Flip The Code" ,"Elixir Of Euphoria", "Code Combat" ,"Human Needs Techno Meets","Shoot'em All"};
    private String[] rules = {
            "It is a coding event.\n" +
            "\n" +
            "Participants will be provided with the executable files before the start on Cape of the event.\n" +
            "\n" +
            "The participants have to write the code for the given outputs file. They can input as many input as they want to understand the algorithm\n" +
            "\n" +
            "The participants will be judged based on the correctness of the code and code optimization\n" +
            "\n" +
            "Two participants can participants from each college","It is a surprise event.",
            "The event will consist of two rounds.\n" +
            "\n" +
            "The preliminary round will consist of objective questions based on C/C++.\n" +
            "\n" +
            "A team will consist of 3 participants\n" +
            "\n" +
            "Candidates will be shortlisted on the basis of results of the preliminary round and will be qualified for the final round.\n" +
            "\n" +
            "In the final round, participants will be asked to write a code in C/C++ for a problem given to them. Time allotted will be 45 minutes.\n" +
            "\n" +
            "In the final round, all 3 participants will be provided separate systems.\n" +
            "\n" +
            "Decision of judges will be final and binding\n" +
            "\n","he event will consist of two rounds.\n" +
            "\n" +
            "The preliminary round will consist of objective questions based on Mathematical & logical puzzles & basic Algorithm of Machine learning.\n" +
            "\n" +
            "Candidates will be shortlisted on the basis of results of the preliminary round and will be qualified for final round.\n" +
            "\n" +
            "In the final round, participants will be asked to write a code in an appropriate programming language for a problem given to them. Time allotted will be 1 hour 30 minutes.\n" +
            "\n" +
            "A team of 2 participants is allowed from each college.\n" +
            "\n" +
            "The Decision of the organizing team will be final and binding\n" +
            "\n",
            "It is a gaming event.\n" +
                    "\n" +
                    "Participants will be playing counter strike.\n" +
                    "\n" +
                    "A team of 2 participants (only boys) will be allowed from each college.\n" +
                    "\n"
    };
    private String[] judging = {
            "Decision of judges will be final and Binding","Decision of judges will be final and Binding","The Ranking on the Online compiler will be the final"
            ,"Decision of judges will be final and Binding","Decision of judges will be final and binding." +
            "           8                     "};
    private String []nxt = {"swipe for next","swipe for next","swipe for next","swipe for next"," "};
    private Context context;
    private LayoutInflater layoutInflater;

    public customSwipeAdapter_offstage(Context ctx)
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
    public Object instantiateItem(ViewGroup container, int position) {
        layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.offstage_swipe,container,false);
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
