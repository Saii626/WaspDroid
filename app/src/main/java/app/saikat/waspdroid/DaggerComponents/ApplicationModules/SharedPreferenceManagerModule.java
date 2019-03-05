package app.saikat.waspdroid.DaggerComponents.ApplicationModules;

import android.app.Application;

import app.saikat.waspdroid.DaggerComponents.Scopes.ApplicationScope;
import app.saikat.waspdroid.SharedPreferenceLayer.SharedPreferencesManager;
import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferenceManagerModule {

    @Provides
    @ApplicationScope
    SharedPreferencesManager get(Application application) {
        return new SharedPreferencesManager(application);
    }
}
