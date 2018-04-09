package com.l.version;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class login extends AppCompatActivity {
    EditText name;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button = (Button) findViewById(R.id.submit);
        TextView register = (TextView) findViewById(R.id.register);
        name = (EditText) findViewById(R.id.name);
        final EditText passkey = (EditText) findViewById(R.id.passkey);
        final EditText password = (EditText) findViewById(R.id.password);
        TextView info = (TextView)findViewById(R.id.pr_info);
       if (!Objects.equals(readMessage(),"null")) {
           // Toast.makeText(login.this, readMessage() , Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(login.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(login.this, "Login Page" , Toast.LENGTH_SHORT).show();

            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = new Register();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.login, fragment);
                    transaction.addToBackStack("register");
                    transaction.commit();
                }
            });

           info.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   information(view);
               }
           });

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!isValidUsername(name.getText().toString())) {
                        name.setError("Invalid Username");
                        Toast.makeText(login.this, "1-25 characters allowed", Toast.LENGTH_SHORT).show();
                        name.requestFocus();
                    } else if (!isValidPassword(password.getText().toString())) {
                        password.setError("Invalid Password");
                        Toast.makeText(login.this, "8-25 character long", Toast.LENGTH_SHORT).show();
                        password.requestFocus();
                    }/* else if (!isValidPasskey(passkey.getText().toString())) {
                    passkey.setError("Invalid Passkey");
                    Toast.makeText(login.this, "Enter 6 character passkey.", Toast.LENGTH_SHORT).show();
                    passkey.requestFocus();
                } */ else {
                        LoadData data = new LoadData(getApplicationContext());
                        data.execute(new String[]{"http://version17.in/android_data/android_login_version.php", name.getText().toString(), passkey.getText().toString(), password.getText().toString()});
                    }
                }
            });
        }
    }

      private  void information(View view)
        {
            Intent intent = new Intent(login.this,information.class);
            startActivity(intent);

        }

    //returning the name of the logged inuser
    private String take_name()
    {
        return name.getText().toString();
    }

    private void writeMessage()
        {
            String filename = "login";
            // here we are saving the data into the user's internal storage
            try {
                String Message = take_name();
                FileOutputStream fileOutputStream = openFileOutput(filename, MODE_PRIVATE);
                fileOutputStream.write(Message.getBytes());
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    private String readMessage() {
        String Message;
        String filename = "login";
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = openFileInput(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if(fileInputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuffer stringBuffer = new StringBuffer();
            try {
                Message = bufferedReader.readLine();
                if (Message != null)
                    return Message;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "null";
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 8 && pass.length() <=25)
        {
            return true;
        }
        return false;
    }
    private boolean isValidUsername(String uname) {
        String UNAME_PATTERN = "^[A-Za-z0-9_-]{1,25}$";

        Pattern pattern = Pattern.compile(UNAME_PATTERN);
        Matcher matcher = pattern.matcher(uname);
        return matcher.matches();
    }
    private boolean isValidPasskey(String pkey) {
        String PKEY_PATTERN = "^[0-9]{6}$";

        Pattern pattern = Pattern.compile(PKEY_PATTERN);
        Matcher matcher = pattern.matcher(pkey);
        return matcher.matches();
    }

class LoadData extends AsyncTask<String , String , String>
{


    String response = null ;
    Context context ;
    public LoadData(Context context)
    {
        this.context = context;
    }
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        String a = "Login Successfully";
        //it will take to the main activity
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            if(Objects.equals(s,a))
            {
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
                writeMessage();
                Intent intent = new Intent(login.this,MainActivity.class);
                startActivity(intent);
            }
            else
                Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected String doInBackground(String... params)
    {
        URL url;
        HttpURLConnection urlConnection = null;
        JSONObject jsonObject = new JSONObject();
        StringBuilder res = new StringBuilder();

        response  = new String ("");
        try
        {
            url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);

            urlConnection.setRequestProperty("Content-type" , "application/json;charset=UTF-8");
            urlConnection.setRequestProperty("Accept", "application/json;charset-UTF-8");
            urlConnection.setRequestMethod("POST");
            jsonObject.put("username" , params[1]);
            jsonObject.put("passkey" , params[2]);
            jsonObject.put("password" , params[3]);
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
        }
        catch (MalformedURLException e) {
             e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            OutputStream outputStream = urlConnection.getOutputStream();
            //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter())
            outputStream.write(jsonObject.toString().getBytes());
            outputStream.close();
            response = urlConnection.getResponseMessage();
            //here we are getting the response back from the server
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String tmp;
            //res is a string that have the all the string that is echoing
            while((tmp = bufferedReader.readLine())!=null)
                res.append(tmp);
            //Toast.makeText(getApplicationContext() ,tmp ,  Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return res.toString();
    }
}

}
