package com.example.acer.avance_dental;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

/**
 * Created by acer on 01-08-2018.
 */

class ViewPage extends FragmentStatePagerAdapter {
  public static int tabcount;
    public ViewPage(FragmentManager supportFragmentManager, int tabCount) {
        super(supportFragmentManager);
        tabcount=tabCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                SignIn signIn=new SignIn();
                return signIn;
            case 1:
                SignUp signUp=new SignUp();
                return signUp;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return tabcount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Sign In";
            case 1:
                return "Sign Up";
        }
        return null;
    }
}
