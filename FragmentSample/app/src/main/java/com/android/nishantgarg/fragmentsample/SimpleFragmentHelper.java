package com.android.nishantgarg.fragmentsample;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.nishantgarg.fragmentsample.Fragments.FridayFragment;
import com.android.nishantgarg.fragmentsample.Fragments.MondayFragment;
import com.android.nishantgarg.fragmentsample.Fragments.SaturdayFragment;
import com.android.nishantgarg.fragmentsample.Fragments.SundayFragment;
import com.android.nishantgarg.fragmentsample.Fragments.ThursdayFragment;
import com.android.nishantgarg.fragmentsample.Fragments.TuesdayFragment;
import com.android.nishantgarg.fragmentsample.Fragments.WednesdayFragment;

/**
 * Created by Nishant Garg on 25-02-2018.
 */
/* The FragmentPagerAdapter is the class which is used with View Pager for different ways in which the
*  Fragments are being handled when the user uses the Swipe Gesture*/
public class SimpleFragmentHelper extends FragmentPagerAdapter {

    //This constructor is necessary to implement when extending the FragmentPageAdapter class
    public SimpleFragmentHelper(FragmentManager fm) {
        super(fm);
    }


    /*This method is used to calculate the number of swipes done by the user. The Initial screen is 0, for every right swipe
    * there is increment of 1 and for every left swipe there is decrement of 1*/
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                /* We are creating new object of the respective fragments here which should be should on corresponding number of swipes.
                * The fragments are then loaded to screen and their corresponding layout files too*/
                return new MondayFragment();
            case 1:
                return new TuesdayFragment();
            case 2:
                return new WednesdayFragment();
            case 3:
                return new ThursdayFragment();
            case 4:
                return new FridayFragment();
            case 5:
                return new SaturdayFragment();
            default:
                return new SundayFragment();
        }
    }
    /* This Method is used for defining the total number of fragments we are going to use in our application. In this case there are 7*/
    @Override
    public int getCount() {
        return 7;
    }
}
