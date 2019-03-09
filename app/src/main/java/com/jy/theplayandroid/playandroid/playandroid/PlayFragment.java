package com.jy.theplayandroid.playandroid.playandroid;


import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.PlayStartActivity;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.adapter.FragmentAdapter;
import com.jy.theplayandroid.playandroid.base.basefragment.SimpleFragment;
import com.jy.theplayandroid.playandroid.playandroid.daohang.DaohangFragment;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.GongzhonghaoFragment;
import com.jy.theplayandroid.playandroid.playandroid.main.MainFragment;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.XiangmuFragment;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.ZhishitixiFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends SimpleFragment  {


    TextView tool;
    @BindView(R.id.framelayout_play)
    ViewPager framelayoutPlay;
    BottomNavigationView mBottomNavigationView;

    public PlayFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public PlayFragment(TextView tvToolbar, BottomNavigationView bottomNavigationView) {
        this.tool = tvToolbar;
        this.mBottomNavigationView=bottomNavigationView;
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
        MainFragment mainFragment = new MainFragment();
        ZhishitixiFragment zhishitixiFragment = new ZhishitixiFragment();
        GongzhonghaoFragment gongzhonghaoFragment = new GongzhonghaoFragment();
        DaohangFragment daohangFragment = new DaohangFragment();
        XiangmuFragment xiangmuFragment = new XiangmuFragment();
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(mainFragment);
        fragments.add(zhishitixiFragment);
        fragments.add(gongzhonghaoFragment);
        fragments.add(daohangFragment);
        fragments.add(xiangmuFragment);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments);
        framelayoutPlay.setAdapter(fragmentAdapter);

        PlayStartActivity.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_main_pager:
                        framelayoutPlay.setCurrentItem(0);
                        //getFragmentManager().beginTransaction().replace(R.id.framelayout_play, mainFragment).commit();
                        tool.setText(item.getTitle());
                        break;
                    case R.id.tab_knowledge_hierarchy:
                        framelayoutPlay.setCurrentItem(1);
                        //getFragmentManager().beginTransaction().replace(R.id.framelayout_play, zhishitixiFragment).commit();
                        tool.setText(item.getTitle());
                        break;
                    case R.id.tab_wx_article:
                        framelayoutPlay.setCurrentItem(2);
                        //getFragmentManager().beginTransaction().replace(R.id.framelayout_play, gongzhonghaoFragment).commit();
                        tool.setText(item.getTitle());
                        break;
                    case R.id.tab_navigation:
                        framelayoutPlay.setCurrentItem(3);
                        //getFragmentManager().beginTransaction().replace(R.id.framelayout_play, daohangFragment).commit();
                        tool.setText(item.getTitle());
                        break;
                    case R.id.tab_project:
                        framelayoutPlay.setCurrentItem(4);
                        //getFragmentManager().beginTransaction().replace(R.id.framelayout_play, xiangmuFragment).commit();
                        tool.setText(item.getTitle());
                        break;
                }
                return true;


            }
        });
    }


}
