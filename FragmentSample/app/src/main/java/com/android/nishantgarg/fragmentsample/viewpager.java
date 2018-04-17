package com.android.nishantgarg.fragmentsample;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class viewpager extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager);
        
        // The View pager is responsible for detecting the right and left swipes by the user
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        //This class is the Helper class which will help us to switch the fragments
        SimpleFragmentHelper adapter = new SimpleFragmentHelper(getSupportFragmentManager());

        // By setting the adapter we are using the view Pager for detecting the swipes on the screen and the
        // Helper class is being used to set the right fragment
        viewPager.setAdapter(adapter);
    }
}
