package app.saikat.waspdroid.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import app.saikat.waspdroid.Models.Response.NotifyRegister;
import app.saikat.waspdroid.NetworkLayer.APIRequestHandler;
import app.saikat.waspdroid.NetworkLayer.URL;
import app.saikat.waspdroid.R;
import app.saikat.waspdroid.SharedPreferenceLayer.SharedPreferenceKey;
import app.saikat.waspdroid.SharedPreferenceLayer.SharedPreferencesManager;

public class NotifyTab extends BaseFragment{

    private LinearLayout sendNotification;
    private EditText registerAs, msgTitle, msgBody;
    private Button register, send;
    private Spinner devices;
    private SharedPreferences sharedPreferences;


    private static String TAG = NotifyTab.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstance) {
        return layoutInflater.inflate(R.layout.notify_tab, viewGroup, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sendNotification = view.findViewById(R.id.sendNotification);

        registerAs = view.findViewById(R.id.register_name);
        msgTitle = view.findViewById(R.id.title);
        msgBody = view.findViewById(R.id.body);

        register = view.findViewById(R.id.register);
        send = view.findViewById(R.id.send_notification);

        devices = view.findViewById(R.id.devices);


//        sharedPreferences = SharedPreferencesManager.getSharedPreferences(this.getContext());
    }

    @Override
    public void onStart() {
        super.onStart();

//        SharedPreferencesManager.addOnPreferenceChangeListener(sharedPreferences, SharedPreferenceKey.USER_ID, () -> {
//
//        });

//        if(Objects.isNull(SharedPreferencesManager.get(sharedPreferences, SharedPreferenceKey.USER_ID))) {
//            sendNotification.setVisibility(View.INVISIBLE);
//        } else {
//            sendNotification.setVisibility(View.VISIBLE);
//        }

//        register.setOnClickListener(v -> {
//            Log.v(TAG, SharedPreferencesManager.get(sharedPreferences, SharedPreferenceKey.FIREBASE_TOKEN));
//
//            if (Objects.isNull(SharedPreferencesManager.get(sharedPreferences, SharedPreferenceKey.FIREBASE_TOKEN))) {
//                return;
//            }
//
//            Map<String, String> form = new HashMap<>();
//
//            form.put("source", registerAs.getText().toString());
//            form.put("token", SharedPreferencesManager.get(sharedPreferences, SharedPreferenceKey.FIREBASE_TOKEN));
//
//            Log.v(TAG, form.get("source"));
//            Log.v(TAG, form.get("token"));
//
//            APIRequestHandler.getInstance().request(URL.REGISTER_DEVICE, Optional.empty(), Optional.of(form),
//                    NotifyRegister.class, (statusCode, resp) -> {
//
//                if (statusCode == 500) {
//                    Log.e(TAG, "500 status code");
//                } else {
//                    Toast.makeText(getContext(), "Device registered", Toast.LENGTH_SHORT).show();
//                    Log.v(TAG, String.format("Device: %s,\nToken: %s", resp.name, resp.token));
//                }
//            });
//        });
    }

    @Override
    public void fragmentSelected() {

    }

    @Override
    public void fragmentUnselected() {

    }
}
