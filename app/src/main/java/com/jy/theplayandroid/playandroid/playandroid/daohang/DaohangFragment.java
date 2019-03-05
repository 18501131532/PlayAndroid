package com.jy.theplayandroid.playandroid.playandroid.daohang;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.BaseFragment;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.global.MyApp;
import com.jy.theplayandroid.playandroid.playandroid.PlayFragment;
import com.jy.theplayandroid.playandroid.playandroid.daohang.activity.DaoHangInfoActivity;
import com.jy.theplayandroid.playandroid.playandroid.daohang.adapter.DaoHangListAdapter;
import com.jy.theplayandroid.playandroid.playandroid.daohang.adapter.RecyclerAdapter;
import com.jy.theplayandroid.playandroid.playandroid.daohang.adapter.RecycleritemAdapter;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.Bean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.JsonBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.presenter.DaoHangPresenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class DaohangFragment extends BaseFragment<TalkClassify.DaoHangView, DaoHangPresenter<TalkClassify.DaoHangView>> implements TalkClassify.DaoHangView {

    @BindView(R.id.rlv1_daohang)
    RecyclerView lvDaohang;
    @BindView(R.id.rlv_daohang)
    RecyclerView rlvDaohang;
    Unbinder unbinder;
    private RecyclerAdapter mRecyclerAdapter;
    private RecycleritemAdapter mRecycleritemAdapter;

    public DaohangFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_daohang;
    }

    @Override
    protected void initData() {
//        mPresenter.getDaoHang("navi/json");
        PlayFragment.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "dvfdvb", Toast.LENGTH_SHORT).show();
                rlvDaohang.scrollToPosition(0);
                lvDaohang.scrollToPosition(0);
            }
        });
    }

    @Override
    protected DaoHangPresenter<TalkClassify.DaoHangView> createPresenter() {
        return new DaoHangPresenter<>();
    }

    @Override
    public void showList(ArrayList<JsonBean.DataBean> arrayList) {
        Log.e("daohang", "showList: " + arrayList.size());
        ArrayList<Bean> strings = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            strings.add(new Bean(arrayList.get(i).getName(), false));
        }

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(mContext);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        lvDaohang.setLayoutManager(linearLayoutManager1);
        mRecycleritemAdapter = new RecycleritemAdapter(strings, mContext);
        lvDaohang.setAdapter(mRecycleritemAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rlvDaohang.setLayoutManager(linearLayoutManager);
        mRecyclerAdapter = new RecyclerAdapter(strings, arrayList, mContext);
        rlvDaohang.setAdapter(mRecyclerAdapter);
        rlvDaohang.setNestedScrollingEnabled(false);

        rlvDaohang.addOnScrollListener(new RecyclerView.OnScrollListener() {//商品列表的滚动监听
            @SuppressLint("ResourceAsColor")
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // 第一个可见位置
                int firstItemPosition = recyclerView.getChildLayoutPosition(recyclerView.getChildAt(0));
                String firstItemPosition_typeId = mRecyclerAdapter.mStrings.get(firstItemPosition).getTitle();//第一个可见商品的分类ID
                if (!TextUtils.isEmpty(firstItemPosition_typeId)) {
                    for (int i = 0; i < mRecycleritemAdapter.mStrings.size(); i++) {
                        if (firstItemPosition_typeId.equals(mRecycleritemAdapter.mStrings.get(i).getTitle())) {
                            for (int k = 0; k < mRecycleritemAdapter.mStrings.size(); k++) {
                                mRecycleritemAdapter.mStrings.get(k).setSelect(false);
                            }
                            mRecycleritemAdapter.mStrings.get(i).setSelect(true);//改变分类选中状态
                            mRecycleritemAdapter.notifyDataSetChanged();
                            mRecycleritemAdapter.setColor(i);
                            lvDaohang.scrollToPosition(i);//分类列表滚动
                            break;
                        }
                    }
                }
            }
        });

        mRecycleritemAdapter.setOnItemClickListener(new RecycleritemAdapter.OnItemClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v, int position) {
                for (int i = 0; i < mRecycleritemAdapter.mStrings.size(); i++) {
                    mRecycleritemAdapter.mStrings.get(i).setSelect(false);
                }
                mRecycleritemAdapter.mStrings.get(position).setSelect(true);//分类选中状态
                mRecycleritemAdapter.notifyDataSetChanged();
                mRecycleritemAdapter.setColor(position);

                for (int i = 0; i < mRecyclerAdapter.mStrings.size(); i++) {
                    if (mRecycleritemAdapter.mStrings.get(position).getTitle().equals(mRecyclerAdapter.mStrings.get(i).getTitle())) {//分类对应的商品列表
                        smoothMoveToPosition(rlvDaohang, i);
                        break;
                    }
                }
            }
        });

        mRecyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onClick(View v, int position, int art_position) {
                Log.e("duanxq", "onClick: " + mRecyclerAdapter.mDataBeans.get(position).getArticles().get(art_position).getTitle());
//                mRecyclerAdapter.mDataBeans.get(position).getArticles().get(art_position).setBool(true);
                Intent intent = new Intent(mContext, DaoHangInfoActivity.class);
                intent.putExtra("title", mRecyclerAdapter.mDataBeans.get(position).getArticles().get(art_position).getTitle());
                intent.putExtra("link", mRecyclerAdapter.mDataBeans.get(position).getArticles().get(art_position).getLink());
                intent.putExtra("auther", mRecyclerAdapter.mDataBeans.get(position).getArticles().get(art_position).getAuthor());
                intent.putExtra("id", mRecyclerAdapter.mDataBeans.get(position).getArticles().get(art_position).getId() + "");
                startActivity(intent);
            }
        });

        MyApp.sMyApp.ScrollList(rlvDaohang);
        MyApp.sMyApp.ScrollList(lvDaohang);
    }

    //目标项是否在最后一个可见项之后
    private boolean mShouldScroll;
    //记录目标项位置
    private int mToPosition;

    /**
     * 使指定的项平滑到顶部
     *
     * @param mRecyclerView
     * @param position      待指定的项
     */
    private void smoothMoveToPosition(final RecyclerView mRecyclerView, final int position) {
        int firstItemPosition = -1;
        int lastItemPosition = -1;

        //todo 获取第一个和最后一个可见位置方式1
        // 第一个可见位置
        firstItemPosition = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(0));
        // 最后一个可见位置
        lastItemPosition = mRecyclerView.getChildLayoutPosition(mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1));
        Log.i("firstItemPosition", firstItemPosition + "");
        Log.i("lastItemPosition", lastItemPosition + "");
        if (position < firstItemPosition) {
            // 第一种可能:跳转位置在第一个可见位置之前
            mRecyclerView.smoothScrollToPosition(position);
        } else if (position <= lastItemPosition) {
            // 第二种可能:跳转位置在第一个可见位置之后,在最后一个可见项之前
            int movePosition = position - firstItemPosition;
            if (movePosition >= 0 && movePosition < mRecyclerView.getChildCount()) {
                int top = mRecyclerView.getChildAt(movePosition).getTop();
                mRecyclerView.smoothScrollBy(0, top);//dx>0===>向左  dy>0====>向上
            }
        } else {
            // 第三种可能:跳转位置在最后可见项之后
            mRecyclerView.smoothScrollToPosition(position);
            mToPosition = position;
            mShouldScroll = true;

            //监听事件的设置，仅仅是为了第三种情况，即：要跳转的位置在可见项之后
            mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (mShouldScroll && RecyclerView.SCROLL_STATE_IDLE == newState) {//
                        mShouldScroll = false;
                        smoothMoveToPosition(mRecyclerView, mToPosition);
                    }
                }
            });
        }
    }


    @Override
    public void showError(String error) {

    }

    @Override
    public void load() {
        super.load();
        mPresenter.getDaoHang("navi/json");
    }
}
