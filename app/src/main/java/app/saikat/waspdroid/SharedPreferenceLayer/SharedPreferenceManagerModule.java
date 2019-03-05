package app.saikat.waspdroid.SharedPreferenceLayer;

import android.app.Application;

import app.saikat.waspdroid.Application.ApplicationScope;
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
