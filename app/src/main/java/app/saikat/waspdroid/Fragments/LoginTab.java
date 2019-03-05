package app.saikat.waspdroid.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import app.saikat.waspdroid.Models.Response.Login;
import app.saikat.waspdroid.NetworkLayer.BASE_URLs;
import app.saikat.waspdroid.NetworkLayer.URL;
import app.saikat.waspdroid.R;
import app.saikat.waspdroid.SharedPreferenceLayer.SharedPreferenceKey;
import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class LoginTab extends BaseFragment{

    @BindView(R.id.username) EditText username;
    @BindView(R.id.password) EditText password;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstance) {
        return this.inflateAndInjectDependencies(layoutInflater,viewGroup, savedInstance, R.layout.login_tab);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @OnCheckedChanged(R.id.toggleButton)
    public void toggleBaseURL(boolean isChecked){
        if (isChecked) {
            URL.changeBaseUrl(BASE_URLs.REMOTE_URL);
        } else {
            URL.changeBaseUrl(BASE_URLs.LOCAL_URL);
        }

        Toast.makeText(getContext(), URL.getBase_url().name(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.login)
    public void login() {
        Map<String, String> formData = new HashMap<>();
        formData.put("username", username.getText().toString());
        formData.put("password", password.getText().toString());

        apiRequestHandler.request(URL.LOGIN, Optional.empty(), Optional.of(formData), Login.class, (statusCode, resp) -> {

            if (resp.status.equals("success")) {
                Toast.makeText(getContext(), String.format("Logged in with ID: %s", resp.userId), Toast.LENGTH_SHORT).show();
                sharedPreferencesManager.put(SharedPreferenceKey.USER_ID, resp.userId);
            } else {
                Toast.makeText(getContext(), "Log failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void fragmentSelected() {

    }

    @Override
    public void fragmentUnselected() {

    }
}
