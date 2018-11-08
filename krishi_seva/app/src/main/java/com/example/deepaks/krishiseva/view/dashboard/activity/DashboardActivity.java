package com.example.deepaks.krishiseva.view.dashboard.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.view.BaseActivity;
import com.example.deepaks.krishiseva.view.dashboard.fragment.BanksFragment;
import com.example.deepaks.krishiseva.view.dashboard.fragment.ElectricityFragment;
import com.example.deepaks.krishiseva.view.dashboard.fragment.NewsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends BaseActivity {

    @BindView(R.id.my_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_banks:
                    setTitle(R.string.bank_label);
                    fragment = new BanksFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_electricity:
                    fragment = new ElectricityFragment();
                    setTitle(R.string.electricity_label);
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_news:
                    fragment = new NewsFragment();
                    setTitle(getString(R.string.news_label));
                    loadFragment(fragment);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        setUpActivityComponents();
    }

    @Override
    protected int getResourceLayout() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected void setUpActivityComponents() {
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.inflateMenu(R.menu.navigation);
        loadFragment(new ElectricityFragment());
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
