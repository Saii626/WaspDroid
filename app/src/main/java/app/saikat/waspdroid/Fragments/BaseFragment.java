package app.saikat.waspdroid.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import app.saikat.waspdroid.Application.WaspdroidApplication;
import app.saikat.waspdroid.DaggerComponents.Components.DaggerFragmentComponent;
import app.saikat.waspdroid.DaggerComponents.Components.FragmentComponent;
import app.saikat.waspdroid.NetworkLayer.APIRequestHandler;
import app.saikat.waspdroid.SharedPreferenceLayer.SharedPreferencesManager;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {

    @Inject
    SharedPreferencesManager sharedPreferencesManager;

    @Inject
    APIRequestHandler apiRequestHandler;

    @Inject
    WaspdroidApplication waspdroidApplication;

    FragmentComponent fragmentComponent;

    Unbinder unbinder;

    String TAG = this.getClass().getSimpleName();

    public BaseFragment(){
        fragmentComponent = DaggerFragmentComponent.builder()
                .appComponent(WaspdroidApplication.getInstance().getAppComponent())
                .build();
    }

    View inflateAndInjectDependencies(LayoutInflater inflater, ViewGroup root, Bundle savedInstance, @LayoutRes int layoutResource) {
        View v = inflater.inflate(layoutResource, root, false);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fragmentComponent.inject(this);
    }

    public abstract void fragmentSelected();

    public abstract void fragmentUnselected();
}
