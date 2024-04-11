package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.os.Bundle;
import android.webkit.WebSettings;

import android.webkit.WebViewClient;
import android.widget.Toast;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new WebAppInterface_one(), "Android_one");
        webView.addJavascriptInterface(new WebAppInterface_two(), "Android_two");
        webView.addJavascriptInterface(new WebAppInterface_three(), "Android_three");
        webView.addJavascriptInterface(new WebAppInterface_four(), "Android_four");
        webView.addJavascriptInterface(new WebAppInterface_five(), "Android_five");
        webView.loadUrl("file:///android_asset/index.html");
    }

       public class WebAppInterface_one {
        @android.webkit.JavascriptInterface
        public void startScannerActivity() {
            Log.d("WebAppInterface_one", "startScannerActivity() called");
            // Start another activity
            Intent intent = new Intent(MainActivity.this, newscan.class);
            startActivity(intent);
        }
    }

    public class WebAppInterface_two{
        @android.webkit.JavascriptInterface
        public void startSpellerActivity() {
            Log.d("WebAppInterface_two", "startSpellerActivity() called");

            Intent intent = new Intent(MainActivity.this, speller.class);
            startActivity(intent);
        }
    }

    public class WebAppInterface_three{
        @android.webkit.JavascriptInterface
        public void startSettingActivity() {
            Log.d("WebAppInterface_three", "startSettingActivity() called");

            Intent intent = new Intent(MainActivity.this, settings.class);
            startActivity(intent);
}}
    public class WebAppInterface_four{
        @android.webkit.JavascriptInterface
        public void starthelpActivity() {
            Log.d("WebAppInterface_four", "starthelpActivity() called");

            Intent intent = new Intent(MainActivity.this, Helpline.class);
            startActivity(intent);
        }}

    public class WebAppInterface_five{
        @android.webkit.JavascriptInterface
        public void startmapsActivity() {
            Log.d("WebAppInterface_five", "startmapsActivity() called");

            Intent intent = new Intent(MainActivity.this, Navigation.class);
            startActivity(intent);
        }}
}