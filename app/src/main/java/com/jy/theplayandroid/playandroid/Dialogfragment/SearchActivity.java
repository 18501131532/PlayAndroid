package com.jy.theplayandroid.playandroid.Dialogfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.baseactivity.BaseActivity;
import com.jy.theplayandroid.playandroid.concat.SearchApi;
import com.jy.theplayandroid.playandroid.bean.SearchBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.infoActivity;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.presenter.SearchPersenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends BaseActivity<SearchApi.SearchV, SearchPersenter<SearchApi.SearchV>> implements SearchApi.SearchV {

    @BindView(R.id.search_activity_text)
    TextView searchActivityText;
    @BindView(R.id.search_activity_toolbar)
    Toolbar searchActivityToolbar;
    @BindView(R.id.search_activity_recycler)
    RecyclerView searchActivityRecycler;

    List<SearchBean.DataBean.DatasBean> list = new ArrayList<>();
    String k;
    @BindView(R.id.search_activity_image)
    ImageView searchActivityImage;
    private SearchAdapter shi;

    @Override
    protected int creatLoyoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void initData() {
        Intent in = getIntent();
        this.k = in.getStringExtra("k");

        searchActivityText.setText(k);
        setSupportActionBar(searchActivityToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        searchActivityImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        shi = new SearchAdapter(this, list);
        searchActivityRecycler.setAdapter(shi);
        searchActivityRecycler.setLayoutManager(new LinearLayoutManager(this));

        shi.setItemonclicks(new SearchAdapter.itemonclicklist() {
            @Override
            public void itemonclick(int i) {
                Intent in = new Intent(SearchActivity.this, infoActivity.class);
                in.putExtra("like", list.get(i).getLink());
                in.putExtra("name", list.get(i).getSuperChapterName());
                startActivity(in);
            }
        });
        shi.setOnclicklist(new SearchAdapter.OnClickListener() {
            @Override
            public void onclicks(int i) {

                Toast.makeText(mActivity, "" + i, Toast.LENGTH_SHORT).show();
            }
        });

        mPresenter.getSearch(k);
    }


    @Override
    public void showSearch(SearchBean searchBean) {
        list.addAll(searchBean.getData().getDatas());
        shi.notifyDataSetChanged();
    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected SearchPersenter<SearchApi.SearchV> creatPresenter() {
        return new SearchPersenter<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
