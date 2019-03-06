package com.jy.theplayandroid.playandroid.playandroid.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.theplayandroid.playandroid.PlayStartActivity;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.BaseFragment;
import com.jy.theplayandroid.playandroid.concat.AtricleList;
import com.jy.theplayandroid.playandroid.global.MyApp;
import com.jy.theplayandroid.playandroid.playandroid.PlayFragment;
import com.jy.theplayandroid.playandroid.playandroid.main.activity.HomePageDetailActivity;
import com.jy.theplayandroid.playandroid.playandroid.main.adapter.MainRlvAdapter;
import com.jy.theplayandroid.playandroid.playandroid.main.bean.ArticleBannerBean;
import com.jy.theplayandroid.playandroid.playandroid.main.bean.ArticleListBean;
import com.jy.theplayandroid.playandroid.playandroid.main.moudle.AtricleListMoudle;
import com.jy.theplayandroid.playandroid.playandroid.main.presenter.AtricleListPresenter;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends BaseFragment<AtricleList.AtricleListView, AtricleListPresenter<AtricleList.AtricleListView>> implements AtricleList.AtricleListView, View.OnClickListener {

    @BindView(R.id.main_refresh)
    PhoenixHeader mMainRefresh;
    @BindView(R.id.main_rlv)
    RecyclerView mMainRlv;
    @BindView(R.id.main_loadmore)
    TaurusHeader mMainLoadmore;
    @BindView(R.id.main_refresh_layout)
    SmartRefreshLayout mMainRefreshLayout;
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
        showLoading();

    }

    @Override
    public void load() {
        super.load();
        getList();
        getbanner();
        PlayStartActivity.fab.setOnClickListener(v -> mMainRlv.scrollToPosition(0));
    }

    public void getList(){
        mPresenter.getAtricList(mPage);
    }
    public void getbanner(){
        mPresenter.getAtricBanner();
    }

    @Override
    public void initView() {
        super.initView();
        ArrayList<ArticleBannerBean.DataBean> banner = new ArrayList<>();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        mMainRlv.setLayoutManager(layoutManager);
         final List<ArticleListBean.DataBean.DatasBean> list = new ArrayList<>();
        mMainRlvAdapter = new MainRlvAdapter(list,banner,getContext());
        mMainRlv.setAdapter(mMainRlvAdapter);

        mMainRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mMainRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list.clear();
                        mPage=0;
                         getList();
                        mMainRefreshLayout.finishRefresh();
                    }
                },1000);
            }
        });
        mMainRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mMainRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPage++;
                        getList();
                        mMainRefreshLayout.finishLoadMore();
                    }
                },1000);
            }
        });
        mMainRlvAdapter.setOnItemClickListener(new MainRlvAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(getContext(), HomePageDetailActivity.class);
                intent.putExtra("url",mMainRlvAdapter.mList.get(position).getLink());
                intent.putExtra("title",mMainRlvAdapter.mList.get(position).getTitle());
                startActivity(intent);
            }
        });
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected AtricleListPresenter<AtricleList.AtricleListView> createPresenter() {
        return new AtricleListPresenter<>();
    }

    @Override
    public void showSuccess(ArticleListBean listBean) {
        hideLoding();
        List<ArticleListBean.DataBean.DatasBean> datas = listBean.getData().getDatas();
        mMainRlvAdapter.addData(datas);
    }

    @Override
    public void shoeSuccess(ArticleBannerBean bannerBean) {
        hideLoding();
        List<ArticleBannerBean.DataBean> data = bannerBean.getData();
        mMainRlvAdapter.addList(data);
    }

    @Override
    public void onClick(View v) {
        mMainRlv.scrollToPosition(0);
    }
}
