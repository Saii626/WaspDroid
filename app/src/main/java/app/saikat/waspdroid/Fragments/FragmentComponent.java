package app.saikat.waspdroid.Fragments;

import app.saikat.waspdroid.Activities.ActivityComponent;
import app.saikat.waspdroid.Application.AppComponent;
import dagger.Component;

@FragmentScope
@Component(modules = {FragmentModule.class}, dependencies = {AppComponent.class})
public interface FragmentComponent {
    void inject(BaseFragment baseFragment);
}
