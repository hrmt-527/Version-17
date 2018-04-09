package com.l.version;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class splash extends AppCompatActivity {

    //GifImageView gifImageView;
    //ProgressBar progressBar;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       /* gifImageView = (GifImageView)findViewById(R.id.splash);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        progressBar.setVisibility(progressBar.VISIBLE);
        try
        {
            InputStream inputStream = getAssets().open("giphy.gif");
            byte []bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }
        catch(IOException ex)
        {

        }*/

        ImageView version  = (ImageView)findViewById(R.id.version_);
        animation = AnimationUtils.loadAnimation(splash.this , R.anim.zoom_in);
        version.startAnimation(animation);
       /* ImageView visnoetic  = (ImageView)findViewById(R.id.visnoetic_);
        animation = AnimationUtils.loadAnimation(splash.this , R.anim.fade_in);
        visnoetic.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(splash.this , R.anim.zoom_in);
        visnoetic.startAnimation(animation);
*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //i have given the next intent as login
                splash.this.startActivity(new Intent(splash.this,login.class));
                splash.this.finish();
            }
        },3000);

    }
}
