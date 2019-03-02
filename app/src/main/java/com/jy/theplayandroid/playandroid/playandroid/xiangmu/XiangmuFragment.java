package com.jy.theplayandroid.playandroid.playandroid.xiangmu;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.flyco.tablayout.SlidingTabLayout;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.BaseFragment;
import com.jy.theplayandroid.playandroid.concat.ProjectClassify;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.adapters.ProjectClassityAdapter;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectClassifyData;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.fragments.ProjectListFragment;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.presenter.IPProjectClassify;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**

 */
public class XiangmuFragment extends BaseFragment<ProjectClassify.ProjectClassifyV, IPProjectClassify<ProjectClassify.ProjectClassifyV>> implements ProjectClassify.ProjectClassifyV {


    @BindView(R.id.project_tab_layout)
    SlidingTabLayout mProjectTabLayout;
    @BindView(R.id.project_divider)
    View mProjectDivider;
    @BindView(R.id.project_viewpager)
    ViewPager mProjectViewpager;
    @BindView(R.id.normal_view)
    LinearLayout mNormalView;
    Unbinder unbinder;

    public XiangmuFragment() {
        // Required empty public constructor

    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_xiangmu;
    }

    @Override
    protected void initData() {
        showLoading();
        mPresenter.getProjectClassify();
    }

    @Override
    public void load() {
        super.load();

    }

    @Override
    protected IPProjectClassify<ProjectClassify.ProjectClassifyV> createPresenter() {
        return new IPProjectClassify<>();
    }

    @Override
    public void showProjectClassify(List<ProjectClassifyData.DataBean> dataBeans) {
        Log.e("156", dataBeans.toString());
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < dataBeans.size(); i++) {
//            String name = dataBeans.get(i).getName().toString();
            // mProjectTabLayout.addTab(mProjectTabLayout.newTab().setText(name));
            fragments.add(new ProjectListFragment(dataBeans.get(i).getId()));
        }

       /* mProjectTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mProjectViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });*/
        //设置分割线
      /*  LinearLayout linearLayout = (LinearLayout) mProjectTabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerPadding(15); // 设置分割线的pandding
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(mActivity, R.drawable.divider)); //设置分割线的样式*/
        // linearLayout.setDividerPadding(dip2px(20)); //设置分割线间隔
        /* mProjectViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mProjectTabLayout));*/
        ProjectClassityAdapter projectClassityAdapter = new ProjectClassityAdapter(getChildFragmentManager(), fragments, dataBeans);
        mProjectViewpager.setAdapter(projectClassityAdapter);
        mProjectTabLayout.setViewPager(mProjectViewpager);
        mProjectViewpager.setCurrentItem(0);
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
