package app.saikat.waspdroid.Fragments;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import app.saikat.waspdroid.Application.WaspdroidApplication;
import app.saikat.waspdroid.NetworkLayer.APIRequestHandler;
import app.saikat.waspdroid.SharedPreferenceLayer.SharedPreferencesManager;

public abstract class BaseFragment extends Fragment {

    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    @Inject
    APIRequestHandler apiRequestHandler;

    @Inject
    WaspdroidApplication waspdroidApplication;

    FragmentComponent fragmentComponent;
    public BaseFragment(){
        fragmentComponent = DaggerFragmentComponent.builder()
                .appComponent(WaspdroidApplication.getInstance().getAppComponent())
                .build();
    }

//    public void inflateAndInjectDependencies(LayoutInflater inflater, ViewGroup root, Bundle savedInstance, @LayoutRes int layoutResource) {
//        View v = inflater.inflate(layoutResource, root, false);
//
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentComponent.inject(this);
    }

    public abstract void fragmentSelected();

    public abstract void fragmentUnselected();
}
