package app.saikat.waspdroid.Activities;

import app.saikat.waspdroid.Application.AppComponent;
import dagger.Component;

@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = {AppComponent.class})
public interface ActivityComponent {

    void inject(BaseActivity baseActivity);
}
