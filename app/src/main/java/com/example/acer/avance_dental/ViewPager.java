package com.example.acer.avance_dental;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ViewPager extends AppCompatActivity {
    TabLayout tabLayout;
    public static android.support.v4.view.ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        tabLayout = findViewById(R.id.tablayout);
        viewpager = findViewById(R.id.viewpager);

        tabLayout.addTab(tabLayout.newTab().setText("Sign in"));
        tabLayout.addTab(tabLayout.newTab().setText("Sign up"));

        tabLayout.setTabGravity(tabLayout.GRAVITY_FILL);
        ViewPage pager = new ViewPage(getSupportFragmentManager(), tabLayout.getTabCount());
        viewpager.setAdapter(pager);

//        tabLayout.setOnTabSelectedListener((TabLayout.OnTabSelectedListener) this);

        tabLayout.setupWithViewPager(viewpager);
    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//
//        finish();
//    }
}