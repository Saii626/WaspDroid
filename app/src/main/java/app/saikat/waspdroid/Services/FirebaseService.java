package app.saikat.waspdroid.Services;

import android.content.SharedPreferences;

import com.google.firebase.messaging.FirebaseMessagingService;

import app.saikat.waspdroid.SharedPreferancesManager;

public class FirebaseService extends FirebaseMessagingService {

    private static String TAG = FirebaseService.class.getSimpleName();
    private SharedPreferences sharedPreferances;

    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);

        sharedPreferances = SharedPreferancesManager.getSharedPreferences(this.getBaseContext());
        SharedPreferancesManager.put(sharedPreferances, SharedPreferancesManager.FIREBASE_TOKEN, token);
    }
}


