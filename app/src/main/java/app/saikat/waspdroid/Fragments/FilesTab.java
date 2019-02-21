package app.saikat.waspdroid.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.saikat.waspdroid.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FilesTab.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FilesTab#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FilesTab extends Fragment {

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle savedInstance) {
        return layoutInflater.inflate(R.layout.files_tab, viewGroup, false);
    }

}
