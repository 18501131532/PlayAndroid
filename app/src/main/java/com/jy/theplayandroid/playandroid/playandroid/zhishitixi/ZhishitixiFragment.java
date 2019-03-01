package com.jy.theplayandroid.playandroid.playandroid.zhishitixi;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.Preference;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.BaseFragment;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.OneBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.interfaces.ZhishiOne;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.presenter.Zhishipresenter;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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

        mPresenter.getones();

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
                    Log.i("========", "handleMessage: " + i);
                    break;
                case 2:         //加载更多
                    int j = (int) msg.obj;
                    Smart.finishLoadMore(true);
                    Log.i("========", "handleMessage: " + j);
                    break;
            }

            return false;
        }
    });

    @SuppressLint("ResourceAsColor")
    private void initView(View view) {
        Smart = view.findViewById(R.id.Smart);
        ZhishiRecyclerView = view.findViewById(R.id.Zhishi_RecyclerView);


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
        Log.i("==========", "show: "+oneBean);
    }

    @Override
    public void showError(String error) {

    }

}
