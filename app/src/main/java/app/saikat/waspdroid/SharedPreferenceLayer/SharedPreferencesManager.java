package app.saikat.waspdroid.SharedPreferenceLayer;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SharedPreferencesManager {

    private Map<SharedPreferenceKey, List<OnPreferenceChangeListener>> observerMap = new HashMap<>();
    private SharedPreferences.OnSharedPreferenceChangeListener listener;
    private SharedPreferences sharedPreferences;

    SharedPreferencesManager(Application application) {
        this.sharedPreferences = application.getSharedPreferences("wasp-droid", Context.MODE_PRIVATE);

        this.listener = (preferences, keyChange) -> {
            try {
                SharedPreferenceKey preferenceKey = SharedPreferenceKey.valueOf(keyChange);
                List<OnPreferenceChangeListener> observers = this.observerMap.get(preferenceKey);

                if (observers != null) {
                    observers.forEach(OnPreferenceChangeListener::onPreferenceChange);
                }
            }catch (Exception ignored) {}
        };

        this.sharedPreferences.registerOnSharedPreferenceChangeListener(this.listener);
    }

    public boolean get(SharedPreferenceKey key, boolean defaultValue) {
        return this.sharedPreferences.getBoolean(key.getKey(), defaultValue);
    }

    public int get(SharedPreferenceKey key, int defaultValue) {
        return this.sharedPreferences.getInt(key.getKey(), defaultValue);
    }

    public String get(SharedPreferenceKey key) {
        return this.sharedPreferences.getString(key.getKey(), null);
    }

    public void put(SharedPreferenceKey key, boolean val) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putBoolean(key.getKey(), val);
        editor.apply();
    }

    public void put(SharedPreferenceKey key, int val) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putInt(key.getKey(), val);
        editor.apply();
    }

    public void put(SharedPreferenceKey key, String val) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.putString(key.getKey(), val);
        editor.apply();
    }

    public void delete(SharedPreferenceKey key) {
        SharedPreferences.Editor editor = this.sharedPreferences.edit();
        editor.remove(key.getKey());
        editor.apply();
    }

    public void addOnPreferenceChangeListener(SharedPreferenceKey key, OnPreferenceChangeListener listener) {
        this.observerMap.compute(key, (k, v) -> {
            if (v == null) {
                v = new ArrayList<>();
            }
            v.add(listener);
            return v;
        });
    }

    public void removeOnPreferenceChangeListener(SharedPreferenceKey key, OnPreferenceChangeListener listener) {
        this.observerMap.computeIfPresent(key, (k,v) -> {
            v.remove(listener);
            return v;
        });
    }
}
