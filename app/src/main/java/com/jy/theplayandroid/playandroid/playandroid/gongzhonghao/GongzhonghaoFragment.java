package com.jy.theplayandroid.playandroid.playandroid.gongzhonghao;


import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.BaseFragment;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.bean.WxAuthor;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.concat.OfficialMarkConcat;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.fragment.ReuseFragment;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.presenter.OfficialMarkPresenter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class GongzhonghaoFragment extends BaseFragment<OfficialMarkConcat.OfficialMarkView,OfficialMarkPresenter<OfficialMarkConcat.OfficialMarkView>> implements OfficialMarkConcat.OfficialMarkView{
    //王云鹏是傻子
    @BindView(R.id.tab)
    TabLayout tab;
    @BindView(R.id.vp)
    ViewPager vp;
    private ArrayList<WxAuthor.DataBean> title;
    private ArrayList<Fragment> list=new ArrayList<>();
    public GongzhonghaoFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_gongzhonghao;
    }

    @Override
    protected void initData() {

    }

    @Override
    public void load() {
        super.load();
        mPresenter.setData();
    }

    @Override
    protected OfficialMarkPresenter<OfficialMarkConcat.OfficialMarkView> createPresenter() {
        return new OfficialMarkPresenter<>();
    }

    @Override
    public void showOfficialMarkInfo(WxAuthor wxAuthor) {
//        title= (ArrayList<WxAuthor.DataBean>) wxAuthor.getData();
//        for (int i = 0; i < title.size(); i++) {
//            Log.i("===========", "showOfficialMarkInfo: "+title.get(i).getName());
//            tab.addTab(tab.newTab().setText(title.get(i).getName()));
//
//            list.add(new ReuseFragment(title.get(i).getName(),title.get(i).getId()));
//        }
//        if (list!=null&&list.size()!=0){
//            vp.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
//                @Override
//                public Fragment getItem(int position) {
//                    return list.get(position);
//                }
//
//                @Override
//                public int getCount() {
//                    return list.size();
//                }
//
//                @Nullable
//                @Override
//                public CharSequence getPageTitle(int position) {
//                    return title.get(position).getName();
//                }
//
//                @Override
//                public void destroyItem(ViewGroup container, int position, Object object) {
//
//                }
//            });
//        }
//        tab.setupWithViewPager(vp);

    }

    @Override
    public void showError(String error) {

    }
}
