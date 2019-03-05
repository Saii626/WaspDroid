package app.saikat.waspdroid.Application;

import android.app.Application;


public class WaspdroidApplication extends Application {

    private AppComponent appComponent;
    private static WaspdroidApplication waspdroidApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        WaspdroidApplication.waspdroidApplication = this;

        appComponent = DaggerAppComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public static WaspdroidApplication getInstance() {
        return WaspdroidApplication.waspdroidApplication;
    }
}
