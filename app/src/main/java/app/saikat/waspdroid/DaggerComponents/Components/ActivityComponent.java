package app.saikat.waspdroid.DaggerComponents.Components;

import app.saikat.waspdroid.Activities.BaseActivity;
import app.saikat.waspdroid.DaggerComponents.ActivityModules.ActivityModule;
import app.saikat.waspdroid.DaggerComponents.ActivityModules.AdapterModule;
import app.saikat.waspdroid.DaggerComponents.Scopes.ActivityScope;
import dagger.Component;

@ActivityScope
@Component(modules = {ActivityModule.class, AdapterModule.class}, dependencies = {AppComponent.class})
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);

}
