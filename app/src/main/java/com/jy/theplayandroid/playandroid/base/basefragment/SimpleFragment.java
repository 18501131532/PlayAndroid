package com.jy.theplayandroid.playandroid.base.basefragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 段傅华 on 2019/1/17.
 */

public abstract class SimpleFragment extends Fragment {
    public Context mContext;
    public Activity mActivity;
    Unbinder mBind;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(createLayoutId(), null,false);
        viewCreate(view);
        return view;
    }

    public void viewCreate(View view) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= (Activity) context;
        this.mContext=context;
    }

    //初始化布局
    protected abstract int createLayoutId();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mBind = ButterKnife.bind(this, view);
        initData();
        initView();
    }

    public void initView() {

    }
    //初始化数据
    protected abstract void initData();
    //懒加载
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()){
            load();
        }
    }
    //懒加载方法，需要重写，不需要不写
    public void load() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBind!=null){
            mBind.unbind();
        }
    }
}
