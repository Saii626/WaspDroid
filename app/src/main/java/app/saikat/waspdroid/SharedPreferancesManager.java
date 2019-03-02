package app.saikat.waspdroid;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferancesManager {

    private SharedPreferancesManager() {}

    public static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences("wasp-pref", Context.MODE_PRIVATE);
    }

    public static boolean get(SharedPreferences prefs, String key, boolean defaultValue) {
        return prefs.getBoolean(key, defaultValue);
    }

    public static int get(SharedPreferences prefs, String key, int defaultValue) {
        return prefs.getInt(key, defaultValue);
    }

    public static String get(SharedPreferences prefs, String key) {
        return prefs.getString(key, null);
    }

    public static void put(SharedPreferences prefs, String key, boolean val) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, val);
        editor.apply();
    }

    public static void put(SharedPreferences prefs, String key, int val) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, val);
        editor.apply();
    }

    public static void put(SharedPreferences prefs, String key, String val) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, val);
        editor.apply();
    }

    public static String USER_ID = "user_id";
    public static String FIREBASE_TOKEN = "firebase_token";
}
