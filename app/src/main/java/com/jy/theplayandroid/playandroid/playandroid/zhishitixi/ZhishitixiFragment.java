package com.jy.theplayandroid.playandroid.playandroid.zhishitixi;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.preference.Preference;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.BaseFragment;
import com.jy.theplayandroid.playandroid.global.MyApp;
import com.jy.theplayandroid.playandroid.playandroid.PlayFragment;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.adapter.ZhishiAdapter;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.OneBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.interfaces.ZhishiOne;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.presenter.Zhishipresenter;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class ZhishitixiFragment extends BaseFragment<ZhishiOne.oneView, Zhishipresenter<ZhishiOne.oneView>> implements ZhishiOne.oneView {


    @BindView(R.id.Zhishi_RecyclerView)
    RecyclerView ZhishiRecyclerView;
    @BindView(R.id.footer)
    TaurusHeader footer;
    @BindView(R.id.Smart)
    SmartRefreshLayout Smart;
    private List<OneBean.DataBean> list = new ArrayList<>();
    private ZhishiAdapter shi;


    public ZhishitixiFragment() {
        // Required empty public constructor
    }


    @Override
    protected int createLayoutId() {
        return R.layout.fragment_zhishitixi;
    }


    @Override
    protected void initData() {

        initView();


        MyApp.sMyApp.ScrollList(ZhishiRecyclerView);


        PlayFragment.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ZhishiRecyclerView.scrollToPosition(0);
            }
        });
    }

    @Override
    protected Zhishipresenter<ZhishiOne.oneView> createPresenter() {
        return new Zhishipresenter<>();
    }

    Handler han = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:         //刷新加载
                    int i = (int) msg.obj;
                    Smart.finishRefresh(true);
                    break;
                case 2:         //加载更多
                    int j = (int) msg.obj;
                    Smart.finishLoadMore(true);
                    break;
            }

            return false;
        }
    });

    public void initView() {
        shi = new ZhishiAdapter(getActivity(), list);
        ZhishiRecyclerView.setAdapter(shi);
        ZhishiRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mPresenter.getone();

        shi.setOnclicklist(new ZhishiAdapter.OnClickListener() {
            @Override
            public void onclickshow(int i) {
                Intent in = new Intent();
                in.setClass(getActivity(), Zhishiactivity.class);
                in.putExtra("title", list.get(i).getName());
                List<OneBean.DataBean.ChildrenBean> children = list.get(i).getChildren();
                Bundle bun = new Bundle();
                bun.putSerializable("id", (Serializable) children);
                in.putExtras(bun);
                startActivity(in);
            }
        });
        Smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                int data = 1;
                Message message = new Message();
                message.what = 1;
                message.obj = data;
                han.sendMessageDelayed(message, 2000);

            }
        });

        Smart.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                int i = 2;
                Message message = new Message();
                message.what = 2;
                message.obj = i;
                han.sendMessageDelayed(message, 2000);
            }
        });

    }

    @Override
    public void show(OneBean oneBean) {
        list.addAll(oneBean.getData());
        shi.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {

    }

}
