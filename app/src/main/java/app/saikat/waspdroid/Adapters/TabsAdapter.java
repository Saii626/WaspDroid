package app.saikat.waspdroid.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import app.saikat.waspdroid.Fragments.BaseFragment;
import app.saikat.waspdroid.Fragments.FilesTab;
import app.saikat.waspdroid.Fragments.GPIOTab;
import app.saikat.waspdroid.Fragments.LoginTab;
import app.saikat.waspdroid.Fragments.MiscTab;
import app.saikat.waspdroid.Fragments.NotifyTab;

public class TabsAdapter extends FragmentStatePagerAdapter {

    private BaseFragment[] fragments;

    public TabsAdapter(FragmentManager fm) {
        super(fm);

        fragments = new BaseFragment[5];
        fragments[0] = new LoginTab();
        fragments[1] = new NotifyTab();
        fragments[2] = new FilesTab();
        fragments[3] = new GPIOTab();
        fragments[4] = new MiscTab();
    }

    @Override
    public BaseFragment getItem(int i) {
       return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }
}
