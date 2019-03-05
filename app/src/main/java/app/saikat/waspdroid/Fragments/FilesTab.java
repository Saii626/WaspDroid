package app.saikat.waspdroid.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.saikat.waspdroid.R;


public class FilesTab extends BaseFragment{

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstance) {
        return layoutInflater.inflate(R.layout.files_tab, viewGroup, false);
    }

    @Override
    public void fragmentSelected() {

    }

    @Override
    public void fragmentUnselected() {

    }
}
