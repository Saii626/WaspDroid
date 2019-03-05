package app.saikat.waspdroid.Activities;

import android.os.Bundle;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import app.saikat.waspdroid.DaggerComponents.ActivityModules.FragmentManagerModule;
import app.saikat.waspdroid.Adapters.FragmentPageAdapter;
import app.saikat.waspdroid.Application.WaspdroidApplication;
import app.saikat.waspdroid.DaggerComponents.Components.ActivityComponent;
import app.saikat.waspdroid.DaggerComponents.Components.DaggerActivityComponent;
import app.saikat.waspdroid.NetworkLayer.APIRequestHandler;
import app.saikat.waspdroid.SharedPreferenceLayer.SharedPreferencesManager;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    ActivityComponent activityComponent;

    @Inject
    WaspdroidApplication waspdroidApplication;

    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    @Inject
    APIRequestHandler apiRequestHandler;

    @Inject
    FragmentPageAdapter pagerAdapter;

    String TAG = this.getClass().getSimpleName();

    public BaseActivity() {
        activityComponent = DaggerActivityComponent.builder()
                .appComponent(WaspdroidApplication.getInstance().getAppComponent())
                .fragmentManagerModule(new FragmentManagerModule(getSupportFragmentManager()))
                .build();
    }

    public void setLayout(@LayoutRes int res) {
        setLayout(res);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityComponent.inject(this);
    }
}
