package app.saikat.waspdroid.Activities;

import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import androidx.viewpager.widget.ViewPager;
import app.saikat.waspdroid.Adapters.FragmentPageAdapter;
import app.saikat.waspdroid.Models.Response.LoginStatus;
import app.saikat.waspdroid.NetworkLayer.URL;
import app.saikat.waspdroid.R;
import app.saikat.waspdroid.SharedPreferenceLayer.SharedPreferenceKey;
import butterknife.BindView;
import butterknife.BindViews;

public class MainActivity extends BaseActivity {

    @BindView(R.id.tab) TabLayout tabLayout;
    @BindView(R.id.pager) ViewPager viewPager;

    @BindViews({R.id.loginTab, R.id.notifyTab, R.id.gpioTab, R.id.filesTab, R.id.miscTab})
    List<TabItem> tabItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        super.setLayout(R.layout.activity_main);

        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                pagerAdapter.getItem(tab.getPosition()).fragmentSelected();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                pagerAdapter.getItem(tab.getPosition()).fragmentUnselected();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        apiRequestHandler.request(URL.GET_LOGGED_IN_USER, Optional.empty(), Optional.empty(), LoginStatus.class, (statusCode, resp) -> {
            if (resp.status.equals("success") && !Objects.isNull(resp.userId)) {
                sharedPreferencesManager.put(SharedPreferenceKey.USER_ID, resp.userId.toString());
            } else {
                sharedPreferencesManager.put(SharedPreferenceKey.USER_ID, null);
            }
        });
    }

}
