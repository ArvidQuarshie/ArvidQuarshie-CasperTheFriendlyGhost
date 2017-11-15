package Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import Fragments.IntroFragment;
import Fragments.SavedDreamsFragment;
import Fragments.SearchFragment;
import Fragments.SaveDreamsFragment;

/**
 * Created by android on 10/12/17.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private int NUM_ITEMS = 4;
    private String[] titles= new String[]{"Welcome to Casper", "Save Your Dream","Search","My Dreams"};


    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return  NUM_ITEMS ;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new IntroFragment();
            case 1:
                return new SaveDreamsFragment();
            case 2:
                return new SearchFragment();
            case 3:
                return new SavedDreamsFragment();



            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return  titles[position];
    }

}
