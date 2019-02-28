package com.jy.theplayandroid.playandroid.playandroid.zhishitixi;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jy.theplayandroid.playandroid.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class ZhishitixiFragment extends Fragment {

    private SmartRefreshLayout Smart;
    private RecyclerView ZhishiRecyclerView;

    public ZhishitixiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_zhishitixi, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

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

    private void initView(View view) {
        Smart = view.findViewById(R.id.Smart);
        ZhishiRecyclerView = view.findViewById(R.id.Zhishi_RecyclerView);
        //设置 Header 为 贝塞尔雷达 样式
        Smart.setRefreshHeader(new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true));
        //设置 Footer 为 球脉冲 样式
        Smart.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale));


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
}
