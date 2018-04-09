package com.l.version;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Register extends Fragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.register,container,false);

        final EditText name = (EditText)view.findViewById(R.id.name);
        final EditText college = (EditText)view.findViewById(R.id.college);
        final EditText email = (EditText)view.findViewById(R.id.email);
        final EditText contact = (EditText)view.findViewById(R.id.contact);
        final EditText password = (EditText)view.findViewById(R.id.password);
        EditText Cpassword = (EditText)view.findViewById(R.id.confirm_password);
        Button register = (Button)view.findViewById(R.id.button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isValidCollege(college.getText().toString())) {
                    college.setError("Invalid College Name");
                    Toast.makeText(getActivity(), "1-25 characters allowed", Toast.LENGTH_SHORT).show();
                    college.requestFocus();
                } else
                if (!isValidUsername(name.getText().toString())) {
                    name.setError("Invalid Username");
                    Toast.makeText(getActivity(), "1-25 characters allowed", Toast.LENGTH_SHORT).show();
                    name.requestFocus();
                } else if (!isValidPassword(password.getText().toString())) {
                    password.setError("Invalid Password");
                    Toast.makeText(getActivity(), "8-25 character long", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                }/*else if(password.getText().toString() != confirm_password.getText().toString() ) {
                    confirm_password.setError("Deviation between the Password");
                    Toast.makeText(MainActivity.this, "Password atleast have the  8 character long", Toast.LENGTH_SHORT).show();
                    confirm_password.requestFocus();
                }*/else if (!isValidEmail(email.getText().toString())) {
                    email.setError("Invalid Email");
                    Toast.makeText(getActivity(), "Enter the email Id in a right Proper Way", Toast.LENGTH_SHORT).show();
                    email.requestFocus();
                }else
                if (!isValidContact(contact.getText().toString())) {
                    contact.setError("Invalid Contact");
                    Toast.makeText(getActivity(), "10 Integers Allowed", Toast.LENGTH_SHORT).show();
                    contact.requestFocus();
                }
                else {
                    LoadData data = new LoadData(view.getContext());//view.getApplicationContext
                    data.execute(new String[]{"http://version17.in/android_data/android_register_version.php",college.getText().toString(), name.getText().toString(),password.getText().toString(), email.getText().toString(),contact.getText().toString()});
                }
            }
        });


        return view;
    }

    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 8 && pass.length() <=25)
        {
            return true;
        }
        return false;
    }

    private boolean isValidCollege(String college) {
        String UNAME_PATTERN = "^[A-Za-z ]{1,100}$";
        Pattern pattern = Pattern.compile(UNAME_PATTERN);
        Matcher matcher = pattern.matcher(college);
        return matcher.matches();
    }

    private boolean isValidContact(String contact)
    {
        String PKEY_PATTERN = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(PKEY_PATTERN);
        Matcher matcher = pattern.matcher(contact);
        return matcher.matches();
    }
    private boolean isValidUsername(String uname) {
        String UNAME_PATTERN = "^[A-Za-z0-9_ -]{1,25}$";
        Pattern pattern = Pattern.compile(UNAME_PATTERN);
        Matcher matcher = pattern.matcher(uname);
        return matcher.matches();
    }
    private boolean isValidEmail(String pkey) {
        String PKEY_PATTERN = "^[A-Za-z0-9_@.]{5,60}$";
        Pattern pattern = Pattern.compile(PKEY_PATTERN);
        Matcher matcher = pattern.matcher(pkey);
        return matcher.matches();
    }

    class LoadData extends AsyncTask<String , String , String>
    {
        String college, username  ,password , email , contact;
        String response = null ;
        Context context ;
        public LoadData(Context context)
        {
            this.context = context;
        }
        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected void onPostExecute(String s)
        {
          super.onPostExecute(s);
            String a = "Registered Successfully";
            if(Objects.equals(s,a))
            {
                Toast.makeText(context, s , Toast.LENGTH_SHORT).show();
                Fragment fragment = new participants_registration(college,username,password,email,contact);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.registration,fragment).commit();
                //getFragmentManager().popBackStack();
              }
            else
                Toast.makeText(context, "Not Registered Yet", Toast.LENGTH_SHORT).show();
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
                jsonObject.put("college" , params[1]);
                jsonObject.put("username", params[2]);
                jsonObject.put("password", params[3]);
                jsonObject.put("email"   , params[4]);
                jsonObject.put("contact" , params[5]);
                college = new String(params[1]);
                username = new String(params[2]);
                password = new String(params[3]);
                email = new String(params[4]);
                contact = new String(params[5]);
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
