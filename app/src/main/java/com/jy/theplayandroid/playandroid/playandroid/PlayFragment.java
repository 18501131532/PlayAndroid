package com.jy.theplayandroid.playandroid.playandroid;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.SimpleFragment;
import com.jy.theplayandroid.playandroid.playandroid.daohang.DaohangFragment;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.GongzhonghaoFragment;
import com.jy.theplayandroid.playandroid.playandroid.main.MainFragment;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.XiangmuFragment;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.ZhishitixiFragment;
import com.jy.theplayandroid.playandroid.util.BottomNavigationViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends SimpleFragment {


    TextView tool;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;

    public PlayFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public PlayFragment(TextView toolbar) {
        this.tool=toolbar;
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_play;
    }

    @Override
    protected void initData() {
        initClick();
    }

    private void initClick() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        final MainFragment mainFragment = new MainFragment();
        final ZhishitixiFragment zhishitixiFragment = new ZhishitixiFragment();
        final GongzhonghaoFragment gongzhonghaoFragment = new GongzhonghaoFragment();
        final DaohangFragment daohangFragment = new DaohangFragment();
        final XiangmuFragment xiangmuFragment = new XiangmuFragment();

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.tab_main_pager:
                        getFragmentManager().beginTransaction().replace(R.id.framelayout_play,mainFragment).commit();
                        tool.setText(item.getTitle());
                        break;
                    case R.id.tab_knowledge_hierarchy:
                        getFragmentManager().beginTransaction().replace(R.id.framelayout_play,zhishitixiFragment).commit();
                        tool.setText(item.getTitle());
                        break;
                    case R.id.tab_wx_article:
                        getFragmentManager().beginTransaction().replace(R.id.framelayout_play,gongzhonghaoFragment).commit();
                        tool.setText(item.getTitle());
                        break;
                    case R.id.tab_navigation:
                        getFragmentManager().beginTransaction().replace(R.id.framelayout_play,daohangFragment).commit();
                        tool.setText(item.getTitle());
                        break;
                    case R.id.tab_project:
                        getFragmentManager().beginTransaction().replace(R.id.framelayout_play,xiangmuFragment).commit();
                        tool.setText(item.getTitle());
                        break;
                }
                return true;
            }
        });
    }
}
