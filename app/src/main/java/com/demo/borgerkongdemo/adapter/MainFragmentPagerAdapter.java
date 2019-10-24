package com.demo.borgerkongdemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments ;//Fragments list
    private List<String> mFragmentsTitles ;//fragments title

    public MainFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments = new ArrayList<>();
        mFragmentsTitles = new ArrayList<>();
    }

    public void addFragment(Fragment fragment, String fragmentTitle) {
        mFragments.add(fragment);
        mFragmentsTitles.add(fragmentTitle);
    }

    /**
     * update
     * @param index
     * @param fragment
     * @param fragmentTitle
     */
    public void updateFragment(int index,Fragment fragment, String fragmentTitle) {
        mFragments.remove(index);
        mFragments.add(index,fragment);
        mFragmentsTitles.add(fragmentTitle);

        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        //得到对应position的Fragment
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        //Fragment size
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        //fragment list
        return mFragmentsTitles.get(position);
    }
}
