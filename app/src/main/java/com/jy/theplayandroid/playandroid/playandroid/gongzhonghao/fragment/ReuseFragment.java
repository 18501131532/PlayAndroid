package com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.BaseFragment;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.bean.FeedArticleListData;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.concat.FeedArticleListConcat;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.presenter.FeedArticlePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ReuseFragment extends BaseFragment<FeedArticleListConcat.FeedArticleView,FeedArticlePresenter<FeedArticleListConcat.FeedArticleView>> implements FeedArticleListConcat.FeedArticleView{

    private String tab;
    private int id;
    @BindView(R.id.public_edit)
    EditText public_edit;
    @BindView(R.id.public_layout)
    SmartRefreshLayout public_layout;
    @BindView(R.id.rv)
    RecyclerView rv;
    @SuppressLint("ValidFragment")
    public ReuseFragment(int id) {
        this.id=id;
    }

    @SuppressLint("ValidFragment")
    public ReuseFragment(String name) {
        this.tab=name;
    }


    @Override
    protected int createLayoutId() {
        return R.layout.fragment_reuse;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected FeedArticlePresenter<FeedArticleListConcat.FeedArticleView> createPresenter() {
        return new FeedArticlePresenter<>();
    }

    @Override
    public void showFeedArticleInfo(FeedArticleListData feedArticleListData) {

    }

    @Override
    public void showError(String error) {

    }
}
