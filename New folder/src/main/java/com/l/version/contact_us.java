package com.l.version;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class contact_us extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
/*        WebView contact = (WebView)this.findViewById(R.id.contact);
        String url = "file:///android_asset/conn.html";
        contact.getSettings().setJavaScriptEnabled(true);
        contact.clearHistory();
        contact.getSettings().setJavaScriptEnabled(true);
        contact.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        contact.loadUrl(url);*/
    }
}
