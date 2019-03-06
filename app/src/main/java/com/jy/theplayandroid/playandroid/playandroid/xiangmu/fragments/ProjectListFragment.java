package com.jy.theplayandroid.playandroid.playandroid.xiangmu.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.BaseFragment;
import com.jy.theplayandroid.playandroid.concat.ProjectListData;
import com.jy.theplayandroid.playandroid.global.MyApp;
import com.jy.theplayandroid.playandroid.playandroid.PlayFragment;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.activitys.ProjectActivity;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.adapters.ProjectListAdapter;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectListBean;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.presenter.IPProjectListData;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectListFragment extends BaseFragment<ProjectListData.ProjectListV, IPProjectListData<ProjectListData.ProjectListV>> implements ProjectListData.ProjectListV {

    @BindView(R.id.project_list_recycler_view)
    RecyclerView mProjectListRecyclerView;
    @BindView(R.id.normal_view)
    SmartRefreshLayout mNormalView;
    Unbinder unbinder;
    private int id;
    private int page;
    private boolean isRefresh = true;
    private List<ProjectListBean.DataBean.DatasBean> mList=new ArrayList<>();
    private ProjectListAdapter mProjectListAdapter;

    @SuppressLint("ValidFragment")
    public ProjectListFragment(int id) {
        this.id = id;
    }

    public ProjectListFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_project_list;
    }

    @Override
    protected void initData() {
        showLoading();
        setRefresh();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        mProjectListRecyclerView.setLayoutManager(linearLayoutManager);
        mProjectListAdapter = new ProjectListAdapter(mActivity,mList);
        mProjectListRecyclerView.setAdapter(mProjectListAdapter);

        MyApp.getMyApp().ScrollList(mProjectListRecyclerView);
    }

    @Override
    public void load() {
        super.load();
        mPresenter.getProjectList(page, id);
        PlayFragment.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProjectListRecyclerView.scrollToPosition(0);
            }
        });

    }

    @Override
    protected IPProjectListData<ProjectListData.ProjectListV> createPresenter() {
        return new IPProjectListData<>();
    }

    @Override
    public void showProjectList(final List<ProjectListBean.DataBean.DatasBean> dataBeans) {
        hideLoding();
            mProjectListAdapter.addData(dataBeans);
            mProjectListAdapter.setOnItmeClick(new ProjectListAdapter.OnItmeClick() {
                @Override
                public void ondianji(int position) {
                    String link = dataBeans.get(position).getLink();
                    String title = dataBeans.get(position).getTitle();
                    Intent intent = new Intent(mActivity, ProjectActivity.class);
                    intent.putExtra("url",link);
                    intent.putExtra("title",title);
                    startActivity(intent);
                }
            });

    }

    @Override
    public void showError(String error) {

    }

    private void setRefresh() {
        page = 1;
        if (mNormalView == null) {
            return;
        }
        mNormalView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {

                page = 1;
                isRefresh = true;
                mPresenter.getProjectList(page, id);
                refreshLayout.finishRefresh(1000);
            }

        });
        mNormalView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                page++;
                isRefresh = false;
                mPresenter.getProjectList(page, id);
                refreshLayout.finishLoadMore(1000);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
