package com.l.version;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class help_desk extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_desk);
        final EditText comment = (EditText) findViewById(R.id.comments);
        final EditText college = (EditText) findViewById(R.id.college);
        final EditText username= (EditText) findViewById(R.id.user_name);

        Button send = (Button) findViewById(R.id.submit);
        final EditText contact = (EditText) findViewById(R.id.contact);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if(Objects.equals(college.getText().toString(),null))
            {
                Toast.makeText(help_desk.this, "Put Your College", Toast.LENGTH_SHORT).show();
            }
            else
            if (Objects.equals(username.getText().toString(), null)) {
                Toast.makeText(help_desk.this, "Put Your College", Toast.LENGTH_SHORT).show();
            }
              else if(Objects.equals(contact.getText().toString(), null)) {
                Toast.makeText(help_desk.this, "Put Your Contact", Toast.LENGTH_SHORT).show();
            } else if  (Objects.equals(comment.getText().toString(), null)) {
                Toast.makeText(help_desk.this, "Can't Leave it Blank", Toast.LENGTH_SHORT).show();
            }   else {

                send.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        LoadData data = new LoadData(getApplicationContext());
                        data.execute(new String[]{"http://version17.in/android_data/android_comment.php", comment.getText().toString(), contact.getText().toString(), username.getText().toString(), college.getText().toString()});
                    }
                });
            }
        }

    }

    class LoadData extends AsyncTask<String, String, String> {
        String response = null;
        Context context;

        public LoadData(Context context) {
            this.context = context;
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String a = "Send Successfully";
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }

        @Override
        protected String doInBackground(String... params) {
            URL url;
            HttpURLConnection urlConnection = null;
            JSONObject jsonObject = new JSONObject();
            StringBuilder res = new StringBuilder();

            response = new String("");
            try {
                url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setReadTimeout(15000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.setRequestProperty("Content-type", "application/json;charset=UTF-8");
                urlConnection.setRequestProperty("Accept", "application/json;charset-UTF-8");
                urlConnection.setRequestMethod("POST");
                jsonObject.put("comment", params[1]);
                jsonObject.put("contact", params[2]);
                jsonObject.put("name", params[3]);
                jsonObject.put("college", params[4]);
                urlConnection.setDoOutput(true);
                urlConnection.setDoInput(true);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                //server is an output stream
                OutputStream outputStream = urlConnection.getOutputStream();
                //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter())
                outputStream.write(jsonObject.toString().getBytes());
                outputStream.close();
                response = urlConnection.getResponseMessage();
                //here we are getting the response back from the server
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String tmp;
                //res is a string that have the all the string that is echoing
                while ((tmp = bufferedReader.readLine()) != null)
                    res.append(tmp);
                //Toast.makeText(getApplicationContext() ,tmp ,  Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return res.toString();
        }
    }
}
