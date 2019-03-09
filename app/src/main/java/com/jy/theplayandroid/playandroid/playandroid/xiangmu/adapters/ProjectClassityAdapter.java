package com.jy.theplayandroid.playandroid.playandroid.xiangmu.adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.jy.theplayandroid.playandroid.bean.ProjectClassifyData;

import java.util.List;

public class ProjectClassityAdapter extends FragmentStatePagerAdapter {
    List<Fragment> mFragments;
    List<ProjectClassifyData.DataBean> mList;

    public ProjectClassityAdapter(FragmentManager fm, List<Fragment> fragments, List<ProjectClassifyData.DataBean> list) {
        super(fm);
        mFragments = fragments;
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }



    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position).getName();
    }
}
