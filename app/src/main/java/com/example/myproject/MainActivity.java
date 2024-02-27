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
        webView.addJavascriptInterface(new WebAppInterface_one(), "Android_one");
        webView.addJavascriptInterface(new WebAppInterface_two(), "Android_two");

        // Load the HTML file from the assets folder
        webView.loadUrl("file:///android_asset/index.html");
    }

    // Define the Java method that can be called from JavaScript
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
            // Start another activity
            Intent intent = new Intent(MainActivity.this, speller.class);
            startActivity(intent);
        }
    }
}