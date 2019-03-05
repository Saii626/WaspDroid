package app.saikat.waspdroid.DaggerComponents.Components;

import app.saikat.waspdroid.DaggerComponents.FragmentModules.FragmentModule;
import app.saikat.waspdroid.DaggerComponents.Scopes.FragmentScope;
import app.saikat.waspdroid.Fragments.BaseFragment;
import dagger.Component;

@FragmentScope
@Component(modules = {FragmentModule.class}, dependencies = {AppComponent.class})
public interface FragmentComponent {
    void inject(BaseFragment baseFragment);
}
