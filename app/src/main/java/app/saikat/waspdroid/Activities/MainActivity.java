package app.saikat.waspdroid.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import app.saikat.waspdroid.Adapters.TabsAdapter;
import app.saikat.waspdroid.R;
import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.tab) TabLayout tabLayout;
    @BindView(R.id.pager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("Notify"));
        tabLayout.addTab(tabLayout.newTab().setText("Files"));
        tabLayout.addTab(tabLayout.newTab().setText("GPIO"));
        tabLayout.addTab(tabLayout.newTab().setText("Misc"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        TabsAdapter tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsAdapter);

        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               viewPager.setCurrentItem(tab.getPosition());
               tabsAdapter.getItem(tab.getPosition()).fragmentSelected();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tabsAdapter.getItem(tab.getPosition()).fragmentUnselected();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

//        APIRequestHandler.getInstance().request(URL.GET_LOGGED_IN_USER, Optional.empty(), Optional.empty(),
//                LoginStatus.class, (statusCode, resp) -> {
//
//                    if (resp.status.equals("success") && !Objects.isNull(resp.userId)) {
//                        sharedPreferencesManager.put(SharedPreferenceKey.USER_ID, resp.userId.toString());
//                    } else {
//                        sharedPreferencesManager.delete(SharedPreferenceKey.USER_ID);
//                    }
//                });
//
//        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//            @Override
//            public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                if (task.isSuccessful()) {
//                    token.setText(task.getResult().getToken());
//                } else {
//                    token.setText("Error");
//                }
//            }
//        });
    }

}
