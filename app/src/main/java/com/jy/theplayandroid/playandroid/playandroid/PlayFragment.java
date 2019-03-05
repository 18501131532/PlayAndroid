package com.jy.theplayandroid.playandroid.playandroid;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.adapter.FragmentAdapter;
import com.jy.theplayandroid.playandroid.base.basefragment.SimpleFragment;
import com.jy.theplayandroid.playandroid.playandroid.daohang.DaohangFragment;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.GongzhonghaoFragment;
import com.jy.theplayandroid.playandroid.playandroid.main.MainFragment;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.XiangmuFragment;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.ZhishitixiFragment;
import com.jy.theplayandroid.playandroid.util.BottomNavigationViewHelper;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlayFragment extends SimpleFragment {


    TextView tool;
    public static FloatingActionButton fab;
    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView bottomNavigationView;
    @BindView(R.id.framelayout_play)
    ViewPager framelayoutPlay;
    Unbinder unbinder;

    public PlayFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public PlayFragment(TextView toolbar) {
        this.tool = toolbar;
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_play;
    }

    @Override
    protected void initData() {
        fab = getActivity().findViewById(R.id.fab);
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
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(mainFragment);
        fragments.add(zhishitixiFragment);
        fragments.add(gongzhonghaoFragment);
        fragments.add(daohangFragment);
        fragments.add(xiangmuFragment);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getChildFragmentManager(), fragments);
        framelayoutPlay.setAdapter(fragmentAdapter);

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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
