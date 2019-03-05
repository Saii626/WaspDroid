package app.saikat.waspdroid.Application;

import android.app.Application;

import app.saikat.waspdroid.Activities.MainActivity;
import app.saikat.waspdroid.NetworkLayer.APIRequestHandler;
import app.saikat.waspdroid.NetworkLayer.RequestHandlerModule;
import app.saikat.waspdroid.SharedPreferenceLayer.SharedPreferenceManagerModule;
import app.saikat.waspdroid.SharedPreferenceLayer.SharedPreferencesManager;
import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class, SharedPreferenceManagerModule.class, RequestHandlerModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    SharedPreferencesManager getSharedPreference();

    APIRequestHandler getAPIRequestHandler();

    Application getApp();

    WaspdroidApplication getWaspdroidApplication();
}
