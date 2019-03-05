package app.saikat.waspdroid.DaggerComponents.ActivityModules;

import androidx.fragment.app.FragmentManager;
import app.saikat.waspdroid.DaggerComponents.Scopes.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class FragmentManagerModule {

    private FragmentManager fragmentManager;

    public FragmentManagerModule(FragmentManager fm) {
        this.fragmentManager = fm;
    }

    @Provides
    @ActivityScope
    FragmentManager getFm() {
        return this.fragmentManager;
    }
}
