package app.saikat.waspdroid.DaggerComponents.Components;

import android.app.Application;

import app.saikat.waspdroid.Activities.BaseActivity;
import app.saikat.waspdroid.Application.WaspdroidApplication;
import app.saikat.waspdroid.DaggerComponents.ApplicationModules.ApplicationModule;
import app.saikat.waspdroid.DaggerComponents.ApplicationModules.RequestHandlerModule;
import app.saikat.waspdroid.DaggerComponents.ApplicationModules.SharedPreferenceManagerModule;
import app.saikat.waspdroid.DaggerComponents.Scopes.ApplicationScope;
import app.saikat.waspdroid.NetworkLayer.APIRequestHandler;
import app.saikat.waspdroid.SharedPreferenceLayer.SharedPreferencesManager;
import dagger.Component;

@ApplicationScope
@Component(modules = {ApplicationModule.class, SharedPreferenceManagerModule.class, RequestHandlerModule.class})
public interface AppComponent {

    SharedPreferencesManager getSharedPreference();

    APIRequestHandler getAPIRequestHandler();

    Application getApp();

    WaspdroidApplication getWaspdroidApplication();

}
