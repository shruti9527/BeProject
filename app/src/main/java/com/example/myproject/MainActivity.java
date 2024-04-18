package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings;

import android.webkit.WebViewClient;
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
        webView.addJavascriptInterface(new WebAppInterface_atm(), "Android_atm");
        webView.addJavascriptInterface(new WebAppInterface_food(), "Android_food");
        webView.addJavascriptInterface(new WebAppInterface_hospital(), "Android_hospital");
        webView.addJavascriptInterface(new WebAppInterface_PublicBuilding(), "Android_PublicBuilding");
        webView.addJavascriptInterface(new WebAppInterface_education(), "Android_education");
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
    public class WebAppInterface_atm{
        @android.webkit.JavascriptInterface
        public void startatmActivity() {
            Log.d("WebAppInterface_atm", "startatmActivity() called");

            Intent intent = new Intent(MainActivity.this, atm.class);
            startActivity(intent);
        }}

    public class WebAppInterface_food{
        @android.webkit.JavascriptInterface
        public void startfoodActivity() {
            Log.d("WebAppInterface_food", "startfoodActivity() called");

            Intent intent = new Intent(MainActivity.this, food.class);
            startActivity(intent);
        }}

    public class WebAppInterface_hospital{
        @android.webkit.JavascriptInterface
        public void starthospitalActivity() {
            Log.d("WebAppInterface_hospital", "starthospitalActivity() called");

            Intent intent = new Intent(MainActivity.this, hospital.class);
            startActivity(intent);
        }}

    public class WebAppInterface_PublicBuilding{
        @android.webkit.JavascriptInterface
        public void startPublicBuildingActivity() {
            Log.d("WebAppInterface_PublicBuilding", "startPublicBuildingActivity() called");

            Intent intent = new Intent(MainActivity.this, PublicBuilding.class);
            startActivity(intent);
        }}

    public class WebAppInterface_education{
        @android.webkit.JavascriptInterface
        public void starteducationActivity() {
            Log.d("WebAppInterface_education", "starteducationActivity() called");

            Intent intent = new Intent(MainActivity.this, education.class);
            startActivity(intent);
        }}
}