package com.l.version;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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
import java.net.URLConnection;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by love_y on 31-07-2017.
 */

public class participants_registration extends Fragment {

    String college, username  ,password , email , contact;

    participants_registration(String college ,String username ,String password ,String email ,String contact)
    {
        this.college = college;
        this.username = username;
        this.password = password;
        this.email = email;
        this.contact = contact;
    }
    EditText [] gender;
    EditText[] p;
    EditText [] c;
    TextView participants;
    Button submit;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.participants_registration ,container ,false );
        gender = new EditText[5];
        p = new EditText[5];
        c = new EditText[5];

        participants = (TextView)view.findViewById(R.id.participants);
        submit = (Button)view.findViewById(R.id.run);
        p[0] = (EditText)view.findViewById(R.id.participant_1);
        c[0] = (EditText)view.findViewById(R.id.contact_1);
        p[1] = (EditText)view.findViewById(R.id.participant_2);
        c[1] = (EditText)view.findViewById(R.id.contact_2);
        p[2] = (EditText)view.findViewById(R.id.participant_3);
        c[2] = (EditText)view.findViewById(R.id.contact_3);
        p[3] = (EditText)view.findViewById(R.id.participant_4);
        c[3]= (EditText)view.findViewById(R.id.contact_4);
        p[4] = (EditText)view.findViewById(R.id.participant_5);
        c[4] = (EditText)view.findViewById(R.id.contact_5);
        gender[0] = (EditText)view.findViewById(R.id.gender_1);
        gender[1] = (EditText)view.findViewById(R.id.gender_2);
        gender[2] = (EditText)view.findViewById(R.id.gender_3);
        gender[3] = (EditText)view.findViewById(R.id.gender_4);
        gender[4] = (EditText)view.findViewById(R.id.gender_5);


        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                int status = 0;
                int a = 0 ;
                final String number = participants.getText().toString();
                try{
                    a = Integer.parseInt(number);
                }catch(NumberFormatException e){
                    a = 0;
                    status = 1;
                }

                if(a>=3) {
                    for (int i = 0; i < a; i++) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            if (!isValidUsername(p[i].getText().toString())) {
                                p[i].setError("Invalid Name");
                                Toast.makeText(getActivity(), "Syntactical Error", Toast.LENGTH_SHORT).show();
                                p[i].requestFocus();
                                status = 1;
                            } else if (!isValidContact(c[i].getText().toString())) {
                                c[i].setError("Invalid Contact");
                                Toast.makeText(getActivity(), "10 Integers Allowed", Toast.LENGTH_SHORT).show();
                                c[i].requestFocus();
                            } else if (!Objects.equals(gender[i].getText().toString(), "M") && !Objects.equals(gender[i].getText().toString(), "F")) {
                                status = 1;
                                gender[i].setError("Invalid Gender");
                                Toast.makeText(getActivity(), "right the correct gender", Toast.LENGTH_SHORT).show();
                                gender[i].requestFocus();
                            }
                            if (status == 1)
                                break;
                        }
                    }
                } else
                {
                    status = 1;
                    submit.setError("Fill the Form Correctly");
                    Toast.makeText(getActivity(), "Fill it!", Toast.LENGTH_SHORT).show();
                    submit.requestFocus();
                }

                for(int i=1;i<a;i++)
                {
                    if(Objects.equals(p[i].getText().toString(),p[i-1].getText().toString()))
                    {
                        p[i].setError("Don't Keep the Same Name");
                        Toast.makeText(getActivity(), "Syntactical Error", Toast.LENGTH_SHORT).show();
                        p[i].requestFocus();

                        status = 1;
                    }
                    if(Objects.equals(c[i].getText().toString(),c[i-1].getText().toString()))
                    {
                        c[i].setError("Don't Keep the Same Contact");
                        Toast.makeText(getActivity(), "Syntactical Error", Toast.LENGTH_SHORT).show();
                        c[i].requestFocus();
                        status = 1;
                    }
                    if(status == 1)
                        break;
                }
                //we can send the data
                if (status == 0) {
                    LoadData data = new LoadData(getContext());
                    data.execute(new String[]{"http://version17.in/android_data/android_register_participant_version.php", p[0].getText().toString(), p[1].getText().toString(), p[2].getText().toString(), p[3].getText().toString(), p[4].getText().toString(), c[0].getText().toString(), c[1].getText().toString(), c[2].getText().toString(), c[3].getText().toString(), c[4].getText().toString(), gender[0].getText().toString(), gender[1].getText().toString(), gender[2].getText().toString(), gender[3].getText().toString(), gender[4].getText().toString(),participants.getText().toString(),college,username,password,email,contact});
                }
                           }
        });
         return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }



    private boolean isValidPassword(String pass) {
        if (pass != null && pass.length() >= 8 && pass.length() <=25)
        {
            return true;
        }
        return false;
    }

    private boolean isValidCollege(String college) {
        String UNAME_PATTERN = "^[A-Za-z0-9_-]{1,25}$";
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
        String UNAME_PATTERN = "^[A-Za-z0-9_-]{1,25}$";
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
class LoadData extends AsyncTask<String, String , String>
{
    String response;
    Context context;
    LoadData(Context context)

    {
     this.context = context;
    }

    @Override
    protected void onPostExecute(String s) {

        super.onPostExecute(s);
        getFragmentManager().popBackStack();
//getFragmentManager().popBackStack();
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
            jsonObject.put("p1" , params[1]);
            jsonObject.put("p2", params[2]);
            jsonObject.put("p3", params[3]);
            jsonObject.put("p4", params[4]);
            jsonObject.put("p5", params[5]);
            jsonObject.put("c1", params[6]);
            jsonObject.put("c2", params[7]);
            jsonObject.put("c3", params[8]);
            jsonObject.put("c4", params[9]);
            jsonObject.put("c5", params[10]);
            jsonObject.put("g1", params[11]);
            jsonObject.put("g2", params[12]);
            jsonObject.put("g3", params[13]);
            jsonObject.put("g4", params[14]);
            jsonObject.put("g5", params[15]);
            jsonObject.put("no", params[16]);
            jsonObject.put("username", params[18]);
            jsonObject.put("college", params[17]);
            jsonObject.put("password", params[19]);
            jsonObject.put("email", params[20]);
            jsonObject.put("contact", params[21]);

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

