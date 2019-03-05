package app.saikat.waspdroid.Adapters;

import java.util.List;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import app.saikat.waspdroid.Fragments.BaseFragment;

public class FragmentPageAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> baseFragments;

    public FragmentPageAdapter(FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.baseFragments = fragments;
    }

    @Override
    public BaseFragment getItem(int i) {
       return baseFragments.get(i);
    }

    @Override
    public int getCount() {
        return this.baseFragments.size();
    }
}
