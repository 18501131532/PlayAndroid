package com.jy.theplayandroid.playandroid.settings;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.theplayandroid.playandroid.MainActivity;
import com.jy.theplayandroid.playandroid.PlayStartActivity;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.baseactivity.SimpleActivity;
import com.jy.theplayandroid.playandroid.base.basefragment.SimpleFragment;
import com.jy.theplayandroid.playandroid.util.ACache;
import com.jy.theplayandroid.playandroid.util.ShareUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    private File cacheFile;
    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    protected int createLayoutId() {
        return R.layout.fragment_settings;
    }

    @Override
    protected void initData() {
        mTvSettingClear.setText(ACache.getCacheSize(cacheFile));
    }







    @OnClick({R.id.cb_setting_cache, R.id.cb_setting_image, R.id.cb_setting_night, R.id.ll_setting_clear, R.id.ll_setting_feedback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_setting_cache:
                break;
            case R.id.cb_setting_image:
                break;
            case R.id.cb_setting_night:
                if(mCbSettingNight.isChecked()){
                    int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
                    PlayStartActivity.mDelegate.setLocalNightMode(currentNightMode == Configuration.UI_MODE_NIGHT_NO ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
                    // 同样需要调用recreate方法使之生效
                    getActivity().recreate();
                }
                break;
            case R.id.ll_setting_feedback:
                //Toast.makeText(mContext, "bdhjskhskjdhfk", Toast.LENGTH_SHORT).show();
                ShareUtil.sendEmail(mActivity,getString(R.string.send_email));
                break;
            case R.id.ll_setting_clear:
                //clearCache();
                break;
        }

    }
    private void clearCache() {
        ACache.deleteDir(cacheFile);
        mTvSettingClear.setText(ACache.getCacheSize(cacheFile));
    }
}
