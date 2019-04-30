package com.uxl.pyfia.android.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.util.Log;
import android.webkit.HttpAuthHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;

class AndroidWebViewClient extends WebViewClient {

    private static final String TAG = "WEBVW";

    private String proxyUser = "";
    private String proxyPwd = "";

    public String getProxyUser() {
        return proxyUser;
    }

    public String getProxyPwd() {
        return proxyPwd;
    }

    public void setProxyUser(String proxyUser) {
        this.proxyUser = proxyUser;
    }

    public void setProxyPwd(String proxyPwd) {
        this.proxyPwd = proxyPwd;
    }

    public AndroidWebViewClient() {
        super();
    }

    public AndroidWebViewClient(String proxyUser, String proxyPwd) {
        super();
        this.proxyUser = proxyUser;
        this.proxyPwd = proxyPwd;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        Uri uri = Uri.parse(url);
        if (uri.getHost() != null && uri.getHost().toLowerCase().contains("pyfia.com")) {
            return false;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        view.getContext().startActivity(intent);
        return true;
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView view,
                                          HttpAuthHandler handler, String host, String realm) {
        Log.i(TAG, "auth thru proxy host " + host + " and realm " + realm + ", proxy user: " + proxyUser + ", proxyPwd: " + proxyPwd);
        handler.proceed(proxyUser, proxyPwd);
    }
}
