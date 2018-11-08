package com.example.deepaks.krishiseva.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.deepaks.krishiseva.R;


/**
 * created by deeapks on 15 oct 2018
 * description base class with utility methods for every activity
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getResourceLayout());

    }

    /**
     * @return layout file id
     * @author deepaks
     * @date 15 oct 2018
     * @description method for returning the resource layout
     */
    protected abstract int getResourceLayout();

    /**
     * @author deepaks
     * @date 15 oct 2018
     * @description method to set uo the activity components
     */
    protected abstract void setUpActivityComponents();


    /**
     * @author deepaks
     * @date 15 oct 2018
     * @description used to show animation.
     **/
    protected void moveHead(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
    }


    /**
     * @author deepaks
     * @date 15 oct 218
     * @description used to show animation
     **/
    protected void moveBack(Activity activity) {
        activity.overridePendingTransition(R.anim.slide_back_in, R.anim.slide_back_out);
    }

}
