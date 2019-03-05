package app.saikat.waspdroid.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import app.saikat.waspdroid.Application.WaspdroidApplication;
import app.saikat.waspdroid.NetworkLayer.APIRequestHandler;
import app.saikat.waspdroid.SharedPreferenceLayer.SharedPreferencesManager;

public abstract class BaseActivity extends AppCompatActivity {

    ActivityComponent activityComponent;

    @Inject
    WaspdroidApplication waspdroidApplication;

    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    @Inject
    APIRequestHandler apiRequestHandler;

    public BaseActivity() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent(WaspdroidApplication.getInstance().getAppComponent())
                .build();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent.inject(this);
    }
}
