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

import com.nightonke.boommenu.Eases.Linear;

/**
 * Created by love_y on 26-07-2017.
 */

public class customSwipeAdapter_onstage extends PagerAdapter {
    private int []image_resources = {R.drawable.quizzeller,R.drawable.paper_presentation,R.drawable.bizzmart,R.drawable.triuhmpher};
    private  String []events = {"Quizller","Papyron","Bizmart","Triumpher"};
    private String []description =
            {"Quizller is organized for all quiz enthusiasts. This event is to inspect the knowledge and the quizzing speed of the participant. Spontaneity plays the main factor here. Here is the chance to scrutinize your awareness in all domains be it technology, sports, history entertainment, etc.",
                    "A golden opportunity to expose yout talent and in depth knowledge .This event will give you a great experience.",
            "Get ready to build your dream team. Bizmart is not only about selling and advertising, It includes the market strategies, the ongoing, the challenges and the analysis. Join this to fulfill your dream and dare to take risk to prove your innovative ideas and grab the platform to showcase it. You are at the right place to hang on with your marketing skills.",
             "The most awaited event of Version, The ultimate goal of every individual participant. Candidates will be selected on the basis of their performance in Version'17. The candidates who will be selected will get to experience a personal interview session."
            };
    private String[] tagline = {"let's Get Quizzical","Let Speak Your Mind","Turning Ideas Into Action","The Ultimate Winner"};
    private String[] rules = {
            "It will be a quiz event of two rounds.\n" +
                    "\n" +
                    "A team of 2 members from each college is allowed.\n" +
                    "\n" +
                    "Preliminary round consists of a test of 30 minutes consisting of 30 questions.\n" +
                    "\n" +
                    "Candidates will be shortlisted on the basis of this test. Final round will be on stage.",
"The event consists of two rounds. First round will be an online submission.\n" +
        "\n" +
        "Participants have to prepare a paper on one of the given topics. Topics will be updated soon. Paper should be submitted in IEEE format with maximum 6 pages, font family \"Times New Roman\" and Font size \"12\".\n" +
        "\n" +
        "Send Your Paper in PDF Format to version2k17@gmail.com\n" +
        "\n" +
        "Shortlisted candidates will have to give an onstage presentation.\n" +
        "\n" +
        "Presentation will be of 10 minutes. 6 minutes for presenting and 4 minutes for questions from judges and audience\n" +
        "\n" +
        "No. of participants will be 2 from each college\n"+"TOPICS\n" + ".Humanoid Robot\n" +
        "\n" +
        "Haptic Technology\n" +
        "\n" +
        "Red Tacton\n" +
        "\n" +
        "Semantic Web\n" +
        "\n" +
        "Cryptovirology\n" +
        "\n" +
        "IMPORTANT :   To be submitted on or before  13th August 2017, 11:59 PM",
            "This event will be an entrepreneur event consisting of 2 rounds\n" +
                    "\n" +
                    "First round will be a written test and shortlisted candidates will be qualified for second round.\n" +
                    "\n" +
                    "Second round will be on stage.Participants will have to come up with a business plan of a particular firm. Details of the firm will be provided at the time of second round itself.\n" +
                    "\n" +
                    "Time given will be 10 minutes. 6 minutes for presenting Business Plan and 4 minutes for questions from judges and audience.\n" +
                    "\n" +
                    "Number of participants will be 3 persons from each college",
            "It will be the star event, which means the winner of this event will be the overall performer of VERSION'17.\n" +
                    "\n" +
                    "Participants on the basis of their performance in the events of VERSION'17 will be shortlisted and they will face a personal interview round.\n" +
                    "\n"
    };
    private String[] judging = {
            "Decision of judges will be final and Binding","Decision of judges will be final and Binding",
            "Decision of judges will be final and Binding","Decision of judges will be final and Binding                    "
     };
    private String []nxt = {"swipe for next","swipe for next","swipe for next"," "};

    private Context context;
    private LayoutInflater layoutInflater;

    public customSwipeAdapter_onstage(Context ctx)
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
        View view = layoutInflater.inflate(R.layout.onstage_swipe,container,false);
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
