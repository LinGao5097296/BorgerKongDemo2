package com.demo.borgerkongdemo;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.demo.borgerkongdemo.adapter.MainFragmentPagerAdapter;
import com.demo.borgerkongdemo.fragment.OrderFragment;
import com.demo.borgerkongdemo.fragment.MealFragment;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    // Tab
    private FragmentManager mFragmentManager;
    private MainFragmentPagerAdapter mainFragmentPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initEventAndData();
    }

    protected void initEventAndData() {
        viewPager=(ViewPager)findViewById(R.id.main_viewpager);
        tabLayout=(TabLayout) findViewById(R.id.tablayout);
        //  toolbar=(Toolbar) findViewById(R.id.toolbar);

        //初始化ViewPager
        mFragmentManager = getSupportFragmentManager();
        mainFragmentPagerAdapter = new MainFragmentPagerAdapter(mFragmentManager);
        mainFragmentPagerAdapter.addFragment(new MealFragment(), "Food");
        mainFragmentPagerAdapter.addFragment(new OrderFragment(), "Order");
        viewPager.setAdapter(mainFragmentPagerAdapter);
        //初始化TabLayout
        tabLayout.addTab(tabLayout.newTab().setText("Food"));
        tabLayout.addTab(tabLayout.newTab().setText("Order"));
        tabLayout.setupWithViewPager(viewPager);

    }

    /**
     * 监听Drawer
     */
    @Override
    public void onBackPressed() {

    }


}
