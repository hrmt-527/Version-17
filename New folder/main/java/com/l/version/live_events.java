package com.l.version;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Vector;

public class live_events extends AppCompatActivity {
    String tmp;
    String json_string;
    Vector v;
    String update;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_events);
        Toast.makeText(live_events.this , "Swipe to Referesh ",Toast.LENGTH_SHORT);
        swipeRefreshLayout    =(SwipeRefreshLayout)findViewById(R.id.refresh);
        getJson(getLayoutInflater().inflate(R.layout.activity_live_events , null));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                        getJson(getLayoutInflater().inflate(R.layout.activity_live_events,null ));
                    }
                },1000);

            }
        });
    }

    private void take(String json_string)
    {
        char a;
        v = new Vector();
        StringBuilder stringBuilder = new StringBuilder();
        int f = 0;
        //update = new String;
        int n = json_string.length();
        for (int i = 0; i < n; i++) {
            if (json_string.charAt(i) == '.') {
                update = stringBuilder.toString();
                v.add(update);
                stringBuilder = new StringBuilder();
                f++;
            } else {
                a = json_string.charAt(i);
                stringBuilder.append(a);
            }
        }
        ListView listView = (ListView) findViewById(R.id.detail);
        ArrayAdapter<Vector> adapter = new ArrayAdapter<Vector>(live_events.this, R.layout.details, v);
        listView.setAdapter(adapter);

    }
    void getJson(View view)
    {
        LoadData data = new LoadData();
        data.execute("http://version17.in/android_data/android_read_data.php");
    }



    class LoadData extends AsyncTask<String, Void, String>
    {
        String json_url ;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(String result)
        {
            json_string = result;
            take(json_string);
        }

        @Override
        protected String doInBackground(String... params) {
            String jUrl = params[0];
            URL url;
            StringBuilder res = new StringBuilder();

            HttpURLConnection urlConnection = null;

            try {
                //converting to the url
                url = new URL(jUrl);
                //opening the url connection
                urlConnection =(HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                //String tmp;
                //res is a string that have the all the string that is echoing
                while((tmp = bufferedReader.readLine())!=null)
                    res.append(tmp);
                json_string = res.toString();
            }  catch (IOException e) {
                e.printStackTrace();
            }
            return res.toString();
        }
    }
}
