package com.jy.theplayandroid.playandroid.playandroid.zhishitixi;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.baseactivity.BaseActivity;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.adapter.TabAdapter;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.OneBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.TwoBEAN;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.fragment.BlankFragment;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.interfaces.ZhishiTwo;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.presenter.ZhishipresenterTwo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Zhishiactivity extends BaseActivity<ZhishiTwo.twoView, ZhishipresenterTwo<ZhishiTwo.twoView>> implements ZhishiTwo.twoView {


    @BindView(R.id.zhishiActivity_tab)
    TabLayout zhishiActivityTab;
    @BindView(R.id.zhishiActivity_view)
    ViewPager zhishiActivityView;
    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @Override
    protected int creatLoyoutId() {
        return R.layout.activity_zhishiactivity;
    }

    @Override
    protected void initData() {
       // showLoading();
        Intent in = getIntent();
        List<String> titleList = new ArrayList<>();
        List<Fragment> fragmentList = new ArrayList<>();
        String title2 = in.getStringExtra("title");
        List<OneBean.DataBean.ChildrenBean> children = (List<OneBean.DataBean.ChildrenBean>) in.getSerializableExtra("id");


        for (int i = 0; i < children.size(); i++) {
            titleList.add(children.get(i).getName());
            //titleList.add("123");
            fragmentList.add(new BlankFragment(children.get(i).getId() + ""));
            //fragmentList.add(new BlankFragment());
        }


        TabAdapter tab = new TabAdapter(getSupportFragmentManager(), titleList, fragmentList);
        zhishiActivityView.setAdapter(tab);
        zhishiActivityTab.setupWithViewPager(zhishiActivityView);


        title.setText(title2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //以下三行是修改回退按钮为白色的逻辑
        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @Override
    public void show(TwoBEAN twoBEAN) {

    }

    @Override
    public void showError(String error) {

    }

    @Override
    protected ZhishipresenterTwo<ZhishiTwo.twoView> creatPresenter() {
//        hideLoding();
        return new ZhishipresenterTwo<>();
    }


}
