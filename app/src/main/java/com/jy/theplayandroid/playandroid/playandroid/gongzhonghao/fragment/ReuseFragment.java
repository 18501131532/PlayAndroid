package com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.BaseFragment;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.OfficialMarkDetailsActivity;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.adapter.ListItemAdapter;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.bean.FeedArticleListData;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.concat.FeedArticleListConcat;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.presenter.FeedArticlePresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ReuseFragment extends BaseFragment<FeedArticleListConcat.FeedArticleView,FeedArticlePresenter<FeedArticleListConcat.FeedArticleView>> implements FeedArticleListConcat.FeedArticleView, OnRefreshListener, OnLoadMoreListener, ListItemAdapter.OnclickItemJumpDetails {

    private String tab;
    private int page=1;
    private int id;
    private String tint;
    @BindView(R.id.public_edit)
    EditText public_edit;
    @BindView(R.id.public_layout)
    SmartRefreshLayout public_layout;
    @BindView(R.id.rv)
    RecyclerView rv;
    private ArrayList<FeedArticleListData.DataBean.DatasBean> list=new ArrayList<>();
    private ListItemAdapter adapter;

    public ReuseFragment(String name, int id){
        this.id=id;
        this.tab=name;

    }

    public ReuseFragment() {

    }


    @Override
    protected FeedArticlePresenter<FeedArticleListConcat.FeedArticleView> createPresenter() {
        return new FeedArticlePresenter<>();
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_reuse;
    }

    @Override
    public void load() {
        super.load();
        mPresenter.setData(id,page);
    }

    @Override
    protected void initData() {
        public_edit.setHint(tab+"带你发现更多干货");

    }

    @Override
    public void showFeedArticleInfo(FeedArticleListData feedArticleListData) {
        list.addAll(feedArticleListData.getData().getDatas());
        adapter = new ListItemAdapter(getActivity(),list);
        rv.setAdapter(adapter);
        LinearLayoutManager manager=new LinearLayoutManager(getActivity());
        rv.setLayoutManager(manager);

        adapter.setOnclickItem(this);
    }



    @Override
    public void showError(String error) {

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPresenter.setData(id,page);
        adapter.notifyDataSetChanged();
        public_layout.finishLoadMore();
    }
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        adapter.notifyDataSetChanged();
        public_layout.finishRefresh();
    }

    @Override
    public void onclickItem(int position) {
        FeedArticleListData.DataBean.DatasBean bean = list.get(position);
        Intent intent = new Intent(getActivity(), OfficialMarkDetailsActivity.class);
        intent.putExtra("link",bean.getLink());
        intent.putExtra("title",bean.getTitle());
        startActivity(intent);
    }
}
