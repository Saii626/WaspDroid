package app.saikat.waspdroid.DaggerComponents.ApplicationModules;

import android.app.Application;

import app.saikat.waspdroid.Application.WaspdroidApplication;
import app.saikat.waspdroid.DaggerComponents.Scopes.ApplicationScope;
import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private WaspdroidApplication waspdroidApplication;

    public ApplicationModule(WaspdroidApplication waspdroidApplication) {
        this.waspdroidApplication = waspdroidApplication;
    }

    @Provides
    @ApplicationScope
    WaspdroidApplication getWasp() {
        return waspdroidApplication;
    }

    @Provides
    @ApplicationScope
    Application getApp() {
        return waspdroidApplication;
    }
}
