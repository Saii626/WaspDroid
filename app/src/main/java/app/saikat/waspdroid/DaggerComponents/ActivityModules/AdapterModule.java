package app.saikat.waspdroid.DaggerComponents.ActivityModules;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.fragment.app.FragmentManager;
import app.saikat.waspdroid.Adapters.FragmentPageAdapter;
import app.saikat.waspdroid.DaggerComponents.Scopes.ActivityScope;
import app.saikat.waspdroid.Fragments.BaseFragment;
import app.saikat.waspdroid.Fragments.FilesTab;
import app.saikat.waspdroid.Fragments.GPIOTab;
import app.saikat.waspdroid.Fragments.LoginTab;
import app.saikat.waspdroid.Fragments.MiscTab;
import app.saikat.waspdroid.Fragments.NotifyTab;
import dagger.Module;
import dagger.Provides;

@Module(includes = {FragmentManagerModule.class})
public class AdapterModule {

    @Provides
    @ActivityScope
    FragmentPageAdapter getAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        return new FragmentPageAdapter(fm, fragments);
    }

    @Provides
    @ActivityScope
    List<BaseFragment> getFragments() {
        return new ArrayList<>(Arrays.asList(new LoginTab(), new NotifyTab(), new GPIOTab(), new FilesTab(), new MiscTab()));
    }
}
