package com.l.version;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.github.clans.fab.FloatingActionButton;
import com.felipecsl.gifimageview.library.GifImageView;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    //BottomBar bottomBar;

    Animation animation,animation_image;

    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3, floatingActionButton4, floatingActionButton5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        onFloatingMenuItemSelected();
       // TextView nit  = (TextView)findViewById(R.id.nit);
      //  TextView edition  = (TextView)findViewById(R.id.edition);
      //  TextView tagline = (TextView)findViewById(R.id.tagline);
        ImageView nitt = (ImageView)findViewById(R.id.nitt);
        ImageView visnoetic = (ImageView)findViewById(R.id.visnoetic);
        ImageView version = (ImageView)findViewById(R.id.version);
        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);
        //nit.startAnimation(animation);

        animation_image = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
        version.startAnimation(animation_image);
      //  animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.blink);
       // edition.startAnimation(animation);
      animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.zoom_in);
       visnoetic.startAnimation(animation);
      //  animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.bounce);
       // tagline.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade_in);
        nitt.startAnimation(animation);

        version.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, about_version.class);
                startActivity(intent);
            }
        });

        visnoetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, about_theme.class);
                startActivity(intent);
            }
        });

        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.social_floating_menu);
        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.online);
        floatingActionButton4 = (FloatingActionButton) findViewById(R.id.offStage);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.onstage);

        floatingActionButton5 = (FloatingActionButton) findViewById(R.id.submission);
        floatingActionButton2 = (FloatingActionButton)findViewById(R.id.live_events);

           floatingActionButton2.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   //we are checking the internet connection here
                   boolean connected = false;
                   ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                   if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                           connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
                       //we are connected to a network
                       connected = true;
                   }
                   else
                       connected = false;

                   if(connected == true) {
                       Intent intent = new Intent(MainActivity.this, live_events.class);
                       startActivity(intent);
                   }
                   else
                   {
                       Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                   }
               }
           });

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Intent facebookIntent = getOpenFacebookIntent(MainActivity.this);
                startActivity(facebookIntent);

            }
        });
        floatingActionButton4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO something when floating action menu first item clicked
                Intent facebookIntent = getOpenOffStageIntent(MainActivity.this);
                startActivity(facebookIntent);

            }
        });

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent facebookIntent = getOpenOnStageIntent(MainActivity.this);
                startActivity(facebookIntent);
            }
        });

        floatingActionButton5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent facebookIntent = getOpenSubmissionIntent(MainActivity.this);
                startActivity(facebookIntent);
            }
        });
       // gifImageView = (GifImageView)findViewById(R.id.back);
/*        try
        {
            InputStream inputStream = getAssets().open("giphy.gif");
            byte []bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }
        catch(IOException ex)
        {

        }
*/
  /*        if(googleServicesAvailable())
       {
           Toast.makeText(this,"",Toast.LENGTH_LONG).show();
       }
*/
       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //splash.this.startActivity(new Intent(splash.this,MainActivity.class));
                //splash.this.finish();
            }
        },3000);*/

          Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    private void writeMessage_logout()
    {
        String filename = "login";
        // here we are saving the data into the user's internal storage
        try {
            String Message = "null";
            FileOutputStream fileOutputStream = openFileOutput(filename, MODE_PRIVATE);
            fileOutputStream.write(Message.getBytes());
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        String s = null;

        int id = item.getItemId();
        if (id == R.id.nav_schedule) {
            Intent intent = new Intent(MainActivity.this,tabbed_sch.class);
            startActivity(intent);
        }
        else
        if (id == R.id.nav_logout) {
            //here we are changing the file
            writeMessage_logout();
            //here we are finishing the activity
            finish();
        }else if (id == R.id.nav_help_desk) {
            Intent intent = new Intent(MainActivity.this,help_desk.class);
            startActivity(intent);
        }else if (id == R.id.nav_about) {
            Intent intent = new Intent(MainActivity.this,about_version.class);
            startActivity(intent);
        }else if (id == R.id.nav_contact) {
            Intent intent = new Intent(MainActivity.this,contact_us.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_map)
        {
            Intent intent = new Intent(MainActivity.this , map_activity.class);
            startActivity(intent);
        }
        else if(id == R.id.nav_theme)
        {
            Intent intent = new Intent(MainActivity.this , about_theme.class);
            startActivity(intent);
        }

         /*
        else {
            Fragment fragment = null;
            // Handle navigation view item clicks here.
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            /* else if (id == R.id.nav_commity){
                fragment = new commities();
                s = "commity";
            }
     transaction.replace(R.id.content_main, fragment);
            transaction.addToBackStack(s);
            transaction.commit();
        }   */ DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


    //method will check wether the google play services available or not in the users phone
    public boolean googleServicesAvailable ()
    {
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int isAvail = apiAvailability.isGooglePlayServicesAvailable(this);
        if(isAvail == ConnectionResult.SUCCESS)
            return true;
        else
        if(apiAvailability.isUserResolvableError(isAvail))
        {
            //user will get the error when he has mobile with that facility is not there
            Dialog dialog = apiAvailability.getErrorDialog(this,isAvail,0);
            dialog.show();
        }
        else
        {
            Toast.makeText(this,"Sorry You Can't Access",Toast.LENGTH_LONG).show();
        }
        return  false;
    }
            void onFloatingMenuItemSelected()
            {
                FloatingActionMenu materialDesignFAM;
             FloatingActionButton floatingActionButton1 = null, floatingActionButton2, floatingActionButton3, floatingActionButton4, floatingActionButton5;
                floatingActionButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent  = new Intent(MainActivity.this,test.class);
                        startActivity(intent);
                        //intent.getAction();
                    }
                });
            }
    public Intent getOpenFacebookIntent(Context context) {

        try {
            return new Intent(MainActivity.this, online_event.class);
        } catch (Exception e) {
            return null;
        }

    }
       public Intent getOpenOffStageIntent(Context context) {

        try {
            return new Intent(MainActivity.this, offstage_event.class);
        } catch (Exception e) {
            return null;
        }

    }
    public Intent getOpenOnStageIntent(Context context) {

        try {
            return new Intent(MainActivity.this, onstage_event.class);
        } catch (Exception e) {
            return null;
        }

    }
    public Intent getOpenSubmissionIntent(Context context) {

        try {
            return new Intent(MainActivity.this, submission_event.class);
        } catch (Exception e) {
            return null;
        }

    }

}
