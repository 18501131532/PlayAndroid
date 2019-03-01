package com.jy.theplayandroid.playandroid.playandroid.main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.BaseFragment;
import com.jy.theplayandroid.playandroid.concat.AtricleList;
import com.jy.theplayandroid.playandroid.playandroid.main.adapter.MainRlvAdapter;
import com.jy.theplayandroid.playandroid.playandroid.main.bean.ArticleListBean;
import com.jy.theplayandroid.playandroid.playandroid.main.presenter.AtricleListPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment<AtricleList.AtricleListView, AtricleListPresenter<AtricleList.AtricleListView>> implements AtricleList.AtricleListView {
    @BindView(R.id.main_rlv)
    RecyclerView mMainRlv;
    private int mPage = 0;
    MainRlvAdapter mMainRlvAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {
        mPresenter.getAtricList(mPage);
    }


    @Override
    public void initView() {
        super.initView();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mMainRlv.setLayoutManager(layoutManager);
        List<ArticleListBean.DataBean.DatasBean> datas = new ArrayList<>();
        mMainRlvAdapter = new MainRlvAdapter(datas,getContext());
        mMainRlv.setAdapter(mMainRlvAdapter);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void showSuccess(ArticleListBean listBean) {
        List<ArticleListBean.DataBean.DatasBean> datas = listBean.getData().getDatas();
        mMainRlvAdapter.addData(datas);
        Log.e("duan", "showSuccess: " + datas);
    }
/*
    @Override
    public void shoeSuccess(ArticleBannerBean bannerBean) {

    }*/

    @Override
    protected AtricleListPresenter<AtricleList.AtricleListView> createPresenter() {
        return new AtricleListPresenter<>();
    }
}
