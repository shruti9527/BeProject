package com.example.myproject;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

public class WebAppInterface {
    private Context mContext;

    // Instantiate the interface and set the context
    WebAppInterface(Context context) {
        mContext = context;
    }

    // Method called from HTML file when button is clicked
    @JavascriptInterface
    public void openJavaActivity() {
        // Open your Java activity here
        Intent intent = new Intent(mContext, MainActivity.class);
        mContext.startActivity(intent);
    }
}