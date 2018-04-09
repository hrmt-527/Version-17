package com.l.version;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by love_y on 26-07-2017.
 */

public class customSwipeAdapter_submission extends PagerAdapter   {
    private int []image_resources = {R.drawable.creatrix};
    private  String []events = {"Creatrix"};
    private String []description =
            {"Creativity is the act of turning new and imaginative ideas into reality. This is an event for the most creative people out there. It is a Poster Submission Event. You have to scratch your brain to design a poster on any of the theme provided.\n" +
                    "\n" +
                    "INTO THE FUTURE :\n" +
                    "\n" +
                    "Radical transformation of society through technological development. Mankind In Transition; A View of the Distant Past, the Present and the Far Future.\n" +
                    "\n" +
                    "POP CULTURE :\n" +
                    "\n" +
                    "Popular culture is constantly evolving and occurs uniquely in place and time. It forms currents and eddies, and represents a complex of mutually interdependent perspectives and values that influence society and its institutions in various ways."};
    private String[] tagline = {"Shape Your Imagination"};
    private String[] rules = {"Dimensions : 300 dpi (print) = 2480 X 3508 pixels\n" +
            "\n" +
            "The design must NOT be an unmodified or barely modified stock image or photograph or standard clipart.\n" +
            "\n" +
            "The design must NOT contain any text or graphic that is considered vulgar, insulting or derogatory to any part of society, or humanity as a whole. Interesting misspells of explicit language will not be encouraged either. However, other word play is permitted.\n" +
            "\n" +
            "Participants are advised to keep their design elements to distinct layers and to rasterize any smart object or text layer before submission.\n" +
            "\n" +
            "The entries must be submitted in .jpeg (maximum resolution) format. In addition a .psd OR .eps (illustrator) file of the design must be submitted.\n" +
            "\n" +
            "A short (max. 250 word) abstract of the design must be submitted in a *.pdf document, explaining the design and putting it in perspective, to allow for unbiased judging.\n" +
            "\n" +
            "Send a zip file on creatrix@version17.in, containing your .psd OR .eps, .jpeg, .pdf and a text file with your college name, passkey and participant's name in it. Ex:- XYZ_COLLEGE ,PassKey, NAME_ABC\n" +
            "\n" +
            "IMPORTANT :   To be submitted on or before  15th August 2017, 11:59 PM"};
    private String[] judging = {
            "Creativity\n" +
                    "\n" +
                    "Typography(Fonts)\n" +
                    "\n" +
                    "Symbolism"};
    private String[] nxt  ={" "};//showing the next
    private Context context;
    private LayoutInflater layoutInflater;

    public customSwipeAdapter_submission(Context ctx)
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
        View view = layoutInflater.inflate(R.layout.submission_swipe,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.event_image);
        TextView textView = (TextView)view.findViewById(R.id.event_name);
        TextView descript = (TextView)view.findViewById(R.id.description);
        TextView tag = (TextView)view.findViewById(R.id.tagline);
        TextView rule = (TextView)view.findViewById(R.id.rules);
        TextView judge = (TextView)view.findViewById(R.id.judging);
        TextView next = (TextView)view.findViewById(R.id.swipe);
        imageView.setImageResource(image_resources[position]);
        textView.setText(events[position]);
        descript.setText(description[position]);
        tag.setText(tagline[position]);
        rule.setText(rules[position]);
        judge.setText(judging[position]);
        next.setText(nxt[position]);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout)object);
    }
}
