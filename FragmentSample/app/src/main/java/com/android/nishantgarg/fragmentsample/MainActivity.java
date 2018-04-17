package com.android.nishantgarg.fragmentsample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button viewPager;

    //The Intent Variable which will be used to navigate to different activities
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (Button) findViewById(R.id.viewPager_Button);
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent= new Intent(getApplicationContext(),viewpager.class);
                startActivity(intent);
            }
        });
    }
}
