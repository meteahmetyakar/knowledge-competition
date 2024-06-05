package com.example.knocomy.model;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SharedPrefsUtils {
    private SharedPrefsUtils() {}

    public static int getIntegerPreference(Context context, String key, int defaultValue) { //girilen keye göre değer çekiliyor, eğer herhangi bir değer bulunamazsa, defaultValue değeri atanıyor.
        int value = defaultValue;
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            value = preferences.getInt(key, defaultValue);
        }
        return value;
    }

    public static boolean setIntegerPreference(Context context, String key, int value) { //girilen keye, girilen değeri atıyor.
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences != null) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(key, value);
            return editor.commit();
        }
        return false;
    }

}
