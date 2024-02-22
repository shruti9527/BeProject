package com.example.myproject;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.os.Bundle;
import android.webkit.WebSettings;

import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        WebView webView = findViewById(R.id.webview);
//        webView.getSettings().setJavaScriptEnabled(true); // Enable JavaScript
//        webView.addJavascriptInterface(new WebAppInterface(this), "Android");
//
////        WebView webView = (WebView) findViewById(R.id.webview);
//        webView.loadUrl("file:///android_asset/index.html");
////        webView.getSettings().setJavaScriptEnabled(true);

        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Inject JavaScript interface
        webView.addJavascriptInterface(new WebAppInterface(), "Android");
        webView.addJavascriptInterface(new WebAppInterface_two(), "Android");
        // Load the HTML file from the assets folder
        webView.loadUrl("file:///android_asset/index.html");
    }

    // Define the Java method that can be called from JavaScript
    public class WebAppInterface {
        @android.webkit.JavascriptInterface
        public void startNewActivity() {
            // Start another activity
            Intent intent = new Intent(MainActivity.this, scanner.class);
            startActivity(intent);
        }
    }
    public class WebAppInterface_two{
        @android.webkit.JavascriptInterface
        public void startSpellerActivity() {
            // Start another activity
            Intent intent = new Intent(MainActivity.this, speller.class);
            startActivity(intent);

    }
}}