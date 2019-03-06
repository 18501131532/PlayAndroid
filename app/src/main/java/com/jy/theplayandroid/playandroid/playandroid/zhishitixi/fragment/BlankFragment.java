package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.BaseFragment;
import com.jy.theplayandroid.playandroid.playandroid.daohang.activity.DaoHangInfoActivity;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.adapter.ZhishiActivityAdapter;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.TwoBEAN;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.infoActivity;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.interfaces.ZhishiTwo;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.presenter.ZhishipresenterTwo;
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
public class BlankFragment extends BaseFragment<ZhishiTwo.twoView, ZhishipresenterTwo<ZhishiTwo.twoView>> implements ZhishiTwo.twoView {


    @BindView(R.id.zhishiactivity_recycler)
    RecyclerView zhishiactivityRecycler;

    List<TwoBEAN.DataBean.DatasBean> list = new ArrayList<>();


    int page = 0;
    String id = "";
    @BindView(R.id.smartlayout)
    SmartRefreshLayout smartlayout;
    Unbinder unbinder;
    private ZhishiActivityAdapter shi;

    public BlankFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public BlankFragment(String id) {
        // Required empty public constructor
        this.id = id;
    }


    @Override
    protected int createLayoutId() {
        return R.layout.fragment_blank;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            Log.i("=============", "setUserVisibleHint: " + id);
            mPresenter.gettwo(page + "", id);

        }
    }

    @Override
    protected void initData() {

        zhishiactivityRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        shi = new ZhishiActivityAdapter(mContext, list);
        zhishiactivityRecycler.setAdapter(shi);


        shi.setOnclicklist(new ZhishiActivityAdapter.OnClickListener() {
            @Override
            public void onclickshow(int i) {
                Intent in = new Intent(getActivity(), DaoHangInfoActivity.class);
                in.putExtra("link", list.get(i).getLink());
                in.putExtra("auther",list.get(i).getAuthor());
                in.putExtra("title", list.get(i).getTitle());
                in.putExtra("id",list.get(i).getId()+"");
                startActivity(in);
            }
        });


        smartlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                Message mes = new Message();
                mes.what = 1;
                han.sendMessageDelayed(mes, 2000);
            }
        });

        smartlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                Message mes = new Message();
                mes.what = 2;
                han.sendMessageDelayed(mes, 2000);
            }
        });
    }

    Handler han = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    page--;
                    mPresenter.gettwo(page + "", id);
                    smartlayout.finishRefresh();
                    break;
                case 2:
                    page++;
                    mPresenter.gettwo(page + "", id);
                    smartlayout.finishLoadMore();
                    break;
            }
            return false;
        }
    });

    @Override
    protected ZhishipresenterTwo<ZhishiTwo.twoView> createPresenter() {
        return new ZhishipresenterTwo<>();
    }

    @Override
    public void show(TwoBEAN twoBEAN) {
        if (page < 0) {
            page = 0;
        }
        if (twoBEAN.getData().getPageCount() < (page + 1)) {
            Toast.makeText(getActivity(), "已经没有数据了>.<", Toast.LENGTH_SHORT).show();
        }
        if (page >= 0) {
            list.addAll(twoBEAN.getData().getDatas());
            shi.notifyDataSetChanged();
        }
    }

    @Override
    public void showError(String error) {

    }
}


