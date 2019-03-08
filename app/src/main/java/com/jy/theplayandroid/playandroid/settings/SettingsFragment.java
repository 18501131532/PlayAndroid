package com.jy.theplayandroid.playandroid.settings;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.MainActivity;
import com.jy.theplayandroid.playandroid.PlayStartActivity;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.SimpleFragment;
import com.jy.theplayandroid.playandroid.util.CleanDataUtils;
import com.jy.theplayandroid.playandroid.http.HttpGreendao;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ImageList;
//import com.jy.theplayandroid.playandroid.util.ACache;
import com.jy.theplayandroid.playandroid.util.ShareUtil;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends SimpleFragment {


    @BindView(R.id.setting_usage_tv)
    TextView mSettingUsageTv;
    @BindView(R.id.cb_setting_cache)
    AppCompatCheckBox mCbSettingCache;
    @BindView(R.id.setting_auto_cache_group)
    LinearLayout mSettingAutoCacheGroup;
    @BindView(R.id.cb_setting_image)
    AppCompatCheckBox mCbSettingImage;
    @BindView(R.id.cb_setting_night)
    AppCompatCheckBox mCbSettingNight;
    @BindView(R.id.setting_usage_card)
    CardView mSettingUsageCard;
    @BindView(R.id.setting_other_tv)
    TextView mSettingOtherTv;
    @BindView(R.id.ll_setting_feedback)
    TextView mLlSettingFeedback;
    @BindView(R.id.tv_setting_clear)
    TextView mTvSettingClear;
    @BindView(R.id.ll_setting_clear)
    LinearLayout mLlSettingClear;
    @BindView(R.id.setting_other_group)
    CardView mSettingOtherGroup;

    private boolean isone=false;
    private boolean istwo=false;

    private File cacheFile;
    private SharedPreferences sh;
    private SharedPreferences.Editor ed;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void initData() {


        sh = getActivity().getSharedPreferences("night", Context.MODE_PRIVATE);
        ed = sh.edit();
//
        if (sh.getBoolean("che",false)==false){
            mCbSettingNight.setChecked(false);
        }else{
            mCbSettingNight.setChecked(true);
//            ((PlayStartActivity)getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        try {
            mTvSettingClear.setText(CleanDataUtils.getTotalCacheSize(mContext));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.cb_setting_cache, R.id.cb_setting_image, R.id.cb_setting_night, R.id.ll_setting_clear, R.id.ll_setting_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_setting_cache:
                break;
            case R.id.cb_setting_image:
                if (mCbSettingImage.isChecked()){
                    List<ImageList> imageLists = HttpGreendao.getInstance().selectImg();
                    boolean one = imageLists.get(0).getOne();
                    one=true;
                    HttpGreendao.getInstance().updata(new ImageList((long)1,one,istwo));
                }else {
                    List<ImageList> imageLists = HttpGreendao.getInstance().selectImg();
                    boolean one = imageLists.get(0).getOne();
                    one=false;
                    HttpGreendao.getInstance().updata(new ImageList((long)1,one,istwo));
                }
                break;
            case R.id.cb_setting_night:

                if(mCbSettingNight.isChecked()){
                    ((PlayStartActivity)getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else{
                    ((PlayStartActivity)getActivity()).getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                (getActivity()).recreate();
                boolean checked = mCbSettingNight.isChecked();
                ed.putBoolean("che",checked);
                ed.commit();
                break;
            case R.id.ll_setting_feedback:
                //Toast.makeText(mContext, "bdhjskhskjdhfk", Toast.LENGTH_SHORT).show();
                ShareUtil.sendEmail(mActivity,getString(R.string.send_email));
                break;
            case R.id.ll_setting_clear:
                CleanDataUtils.clearAllCache(mContext);
                try {
                    mTvSettingClear.setText(CleanDataUtils.getTotalCacheSize(mContext));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
