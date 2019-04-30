package com.uxl.pyfia.android.app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView mWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebView = findViewById(R
                .id.activity_main_webview);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String proxyUser = sharedPreferences.getString(SettingsActivity.KEY_PREF_PROXY_USER, "");
        String proxyPwd = sharedPreferences.getString(SettingsActivity.KEY_PREF_PROXY_PASSWORD, "");

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new AndroidWebViewClient(proxyUser, proxyPwd));

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // REMOTE RESOURCE
        mWebView.loadUrl("http://www.pyfia.com");

        // LOCAL RESOURCE
        // mWebView.loadUrl("file:///android_asset/index.html");
    }

    // Prevent the back-button from closing the app
    @Override
    public void onBackPressed() {
        if(mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }

}