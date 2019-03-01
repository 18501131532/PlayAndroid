package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {
    List<String> title;
    List<Fragment> fragment;

    public TabAdapter(FragmentManager fm, List<String> title, List<Fragment> fragment) {
        super(fm);
        this.title = title;
        this.fragment = fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return fragment.get(position);
    }

    @Override
    public int getCount() {
        return fragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
