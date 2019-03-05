package app.saikat.waspdroid.Services;

import android.content.SharedPreferences;

import com.google.firebase.messaging.FirebaseMessagingService;

import app.saikat.waspdroid.SharedPreferenceLayer.SharedPreferenceKey;
import app.saikat.waspdroid.SharedPreferenceLayer.SharedPreferencesManager;
import app.saikat.waspdroid.Application.WaspdroidApplication;

public class FirebaseService extends FirebaseMessagingService {

    private static String TAG = FirebaseService.class.getSimpleName();
    private SharedPreferences sharedPreferances;

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);

//        ((WaspdroidApplication) getApplication()).getSharedPreferencesManager()
//        sharedPreferances = SharedPreferencesManager.getSharedPreferences(this.getBaseContext());
//        SharedPreferencesManager.put(sharedPreferances, SharedPreferenceKey.FIREBASE_TOKEN, token);
    }
}


