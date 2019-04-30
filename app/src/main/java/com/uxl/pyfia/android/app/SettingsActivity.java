package com.uxl.pyfia.android.app;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class SettingsActivity extends PreferenceActivity {

    public final static String KEY_PREF_PROXY_USER = "proxy_user_preference";
    public final static String KEY_PREF_PROXY_PASSWORD = "proxy_password_preference";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        if (key.equals(KEY_PREF_PROXY_USER)) {
            Preference prefUser = findPreference(key);
            // Set summary to be the user-description for the selected value
            prefUser.setSummary(sharedPreferences.getString(key, ""));
        } else if (key.equals(KEY_PREF_PROXY_PASSWORD)) {
            Preference prefPwd = findPreference(key);
            // Set summary to be the user-description for the selected value
            prefPwd.setSummary(sharedPreferences.getString(key, ""));
        }
    }

}
