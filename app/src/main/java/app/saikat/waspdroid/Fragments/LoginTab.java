package app.saikat.waspdroid.Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import app.saikat.waspdroid.Models.LoginResponse;
import app.saikat.waspdroid.NetworkLayer.APIRequestHandler;
import app.saikat.waspdroid.NetworkLayer.BASE_URLs;
import app.saikat.waspdroid.NetworkLayer.OnResponse;
import app.saikat.waspdroid.NetworkLayer.URLs;
import app.saikat.waspdroid.R;
import app.saikat.waspdroid.SharedPreferancesManager;

public class LoginTab extends Fragment {

    private EditText username, password;
    private Button submit;
    private ToggleButton toggleButton;

    private SharedPreferences sharedPreferences;

    private static String TAG = LoginTab.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstance) {
        return layoutInflater.inflate(R.layout.login_tab, viewGroup, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        submit = view.findViewById(R.id.login);
        toggleButton = view.findViewById(R.id.toggleButton);

        sharedPreferences = SharedPreferancesManager.getSharedPreferences(this.getContext());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, String> formData = new HashMap<>();
                formData.put("username", username.getText().toString());
                formData.put("password", password.getText().toString());

                APIRequestHandler.getInstance().request(URLs.LOGIN, Optional.empty(), Optional.of(formData), (OnResponse<LoginResponse>) (statusCode, resp) -> {
                    if (resp.status.equals("success")) {
                        Toast.makeText(getContext(), String.format("Logged in with ID: %s", resp.userId), Toast.LENGTH_SHORT).show();
                        SharedPreferancesManager.put(sharedPreferences, SharedPreferancesManager.USER_ID, resp.userId);
                    } else {
                        Toast.makeText(getContext(), "Log failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    URLs.changeBaseUrl(BASE_URLs.REMOTE_URL);
                } else {
                    URLs.changeBaseUrl(BASE_URLs.LOCAL_URL);
                }

                Toast.makeText(getContext(), URLs.getBase_url().name(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
