package com.jy.theplayandroid.playandroid.favroite;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jy.theplayandroid.playandroid.LoadingActivity;
import com.jy.theplayandroid.playandroid.PlayStartActivity;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.adapter.CollectLiskeAdapter;
import com.jy.theplayandroid.playandroid.base.basefragment.BaseFragment;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.playandroid.daohang.activity.DaoHangInfoActivity;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.DateBase;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavroiteAddBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.Favruite;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.HttpResult;
import com.jy.theplayandroid.playandroid.playandroid.daohang.manager.LikeDataBaseMannger;
import com.jy.theplayandroid.playandroid.playandroid.daohang.presenter.FavruiteWebPresenter;
import com.scwang.smartrefresh.header.StoreHouseHeader;
import com.scwang.smartrefresh.header.TaurusHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavroiteFragment extends BaseFragment<TalkClassify.FavruiteWebView, FavruiteWebPresenter<TalkClassify.FavruiteWebView>> implements TalkClassify.FavruiteWebView {
    int mPage = 0;
    @BindView(R.id.collect_refresh_head)
    StoreHouseHeader mCollectRefreshHead;
    @BindView(R.id.collect_rlv)
    RecyclerView mCollectRlv;
    @BindView(R.id.collect_loadmore)
    TaurusHeader mCollectLoadmore;
    @BindView(R.id.collect_refresh_layout)
    SmartRefreshLayout mCollectRefreshLayout;
    Unbinder unbinder;
    private CollectLiskeAdapter mAdapter;
    private int mSize;
    private LinearLayoutManager mManager;

    public FavroiteFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_favroite;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getDate(String string) {
        if (string.contains("loging")){
            Log.e("eventbus", "getDate: "+string);
            initClick();
        }
    }

    @Override
    protected void initData() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        PlayStartActivity.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCollectRlv.smoothScrollToPosition(0);
            }
        });

        SharedPreferences loging = mContext.getSharedPreferences("loging", 0);
        boolean loging1 = loging.getBoolean("loging", false);
        if (loging1) {
            initClick();
        } else {
            startActivity(new Intent(mContext, LoadingActivity.class));
        }
    }

    private void initClick() {
        mPresenter.getFavruite(mPage);
        mManager = new LinearLayoutManager(getContext());
        mCollectRlv.setLayoutManager(mManager);
        List<Favruite.DataBean.DatasBean> datas = new ArrayList<>();
        mAdapter = new CollectLiskeAdapter(datas, getContext());
        mCollectRlv.setAdapter(mAdapter);

        mCollectRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mCollectRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        datas.clear();
                        mPage = 0;
                        initData();
                        mCollectRefreshLayout.finishRefresh();
                    }
                }, 1000);
            }
        });

        mCollectRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mCollectRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mSize > 0 && mSize >= 20) {
                            mPage++;
                            initData();
                            mCollectRefreshLayout.finishLoadMore();
                        } else {
                            Toast.makeText(mContext, "没有多余的干货了", Toast.LENGTH_SHORT).show();
                            mCollectRefreshLayout.finishLoadMore();
                        }
                    }
                }, 1000);
            }
        });

        mAdapter.setOnItemClickListener(new CollectLiskeAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent intent = new Intent(getContext(), DaoHangInfoActivity.class);
                intent.putExtra("title", mAdapter.mList.get(position).getTitle());
                intent.putExtra("id", mAdapter.mList.get(position).getId());
                intent.putExtra("link", mAdapter.mList.get(position).getLink());
                intent.putExtra("auther", mAdapter.mList.get(position).getAuthor());
                startActivity(intent);
            }
        });

        mAdapter.setOnItemClickListener(new CollectLiskeAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                HashMap<String, Object> map = new HashMap<>();
                map.put("id",mAdapter.mList.get(position).getId());
                mPresenter.getFavruiteWebDelete(map);

                mAdapter.mList.remove(position);
                mAdapter.notifyDataSetChanged();

                List<DateBase> dateBases = LikeDataBaseMannger.getInstrance().selectId(mAdapter.mList.get(position).getId() + "");
                if (dateBases.size()>0){
                    LikeDataBaseMannger.getInstrance().delete(dateBases.get(0));
                }
            }
        });
    }

    @Override
    public void initView() {
        super.initView();

    }

    @Override
    protected FavruiteWebPresenter<TalkClassify.FavruiteWebView> createPresenter() {
        return new FavruiteWebPresenter<>();
    }

    @Override
    public void showFavruiteWeb(FavroiteAddBean favruiteBean) {

    }

    @Override
    public void showFavruiteWebDelete(HttpResult favruiteBean) {
        if (favruiteBean.getErrorCode()==0){
            Toast.makeText(mContext, "删除收藏成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showFavruite(Favruite favruite) {
        if (favruite.getData() != null) {
            List<DateBase> dateBases = LikeDataBaseMannger.getInstrance().selectAll();
            ArrayList<DateBase> dateBases1 = new ArrayList<>();
            if (dateBases.size()>0){
                for (int i = 0; i < dateBases.size(); i++) {
                    if (dateBases.get(i).getMId().contains(favruite.getData().getDatas().get(i).getId()+"")){

                    }else {
                        dateBases1.add(new DateBase(null,true,favruite.getData().getDatas().get(i).getId()+""));
                    }
                }
            }
            LikeDataBaseMannger.getInstrance().insert(dateBases1);
            List<Favruite.DataBean.DatasBean> datas = favruite.getData().getDatas();
            Log.e("favroite", "showFavruite: " + datas.size());
            mAdapter.addData(datas);
            mSize = favruite.getData().getSize();
        }
    }

    @Override
    public void showError(String error) {

    }

}
