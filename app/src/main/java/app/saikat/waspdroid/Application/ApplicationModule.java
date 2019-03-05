package app.saikat.waspdroid.Application;

import android.app.Application;

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
