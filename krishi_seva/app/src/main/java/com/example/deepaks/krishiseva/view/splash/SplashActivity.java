package com.example.deepaks.krishiseva.view.splash;

import android.content.Intent;
import android.os.Bundle;

import com.example.deepaks.krishiseva.R;
import com.example.deepaks.krishiseva.view.BaseActivity;
import com.example.deepaks.krishiseva.view.login.LoginActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());
        setUpActivityComponents();
    }

    /**
     * @return the layout file for the activity
     * @author deepaks
     * @date 16 oct 2018
     */
    @Override
    protected int getResourceLayout() {
        return R.layout.activity_splash;
    }

    /**
     * @return setup the activity components for the activity
     * @author deepaks
     * @date 16 oct 2018
     */
    @Override
    protected void setUpActivityComponents() {
        startDashBoardActivity();
    }

    /**
     * @author deepaks
     * @date 16 oct 2018
     * @description method to start the dashboard activity
     */
    private void startDashBoardActivity() {
        Intent dashboardIntent = new Intent(this, LoginActivity.class);
        startActivity(dashboardIntent);
        moveHead(this);


    }


}
