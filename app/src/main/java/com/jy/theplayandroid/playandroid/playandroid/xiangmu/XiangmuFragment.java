package com.jy.theplayandroid.playandroid.playandroid.xiangmu;


import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.BaseFragment;
import com.jy.theplayandroid.playandroid.concat.ProjectClassify;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.adapters.ProjectClassityAdapter;
import com.jy.theplayandroid.playandroid.bean.ProjectClassifyData;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.fragments.ProjectListFragment;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.presenter.IPProjectClassify;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
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

    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_xiangmu;
    }

    @Override
    protected void initData() {
        showLoading();
    }

    @Override
    public void load() {
        super.load();
        mPresenter.getProjectClassify();
    }

    @Override
    protected IPProjectClassify<ProjectClassify.ProjectClassifyV> createPresenter() {
        return new IPProjectClassify<>();
    }

    @Override
    public void showProjectClassify(List<ProjectClassifyData.DataBean> dataBeans) {
      hideLoding();
        Log.e("156", dataBeans.toString());
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < dataBeans.size(); i++) {
            fragments.add(new ProjectListFragment(dataBeans.get(i).getId()));
        }

        ProjectClassityAdapter projectClassityAdapter = new ProjectClassityAdapter(getChildFragmentManager(), fragments, dataBeans);
        mProjectViewpager.setAdapter(projectClassityAdapter);
        mProjectTabLayout.setViewPager(mProjectViewpager);
        mProjectViewpager.setCurrentItem(0);
    }

    @Override
    public void showError(String error) {

    }


}
