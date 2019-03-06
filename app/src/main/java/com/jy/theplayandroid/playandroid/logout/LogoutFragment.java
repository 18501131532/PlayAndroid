package com.jy.theplayandroid.playandroid.logout;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basefragment.SimpleFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogoutFragment extends SimpleFragment {


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEdit;

    public LogoutFragment() {
        // Required empty public constructor
    }

    @Override
    protected int createLayoutId() {
        return R.layout.fragment_loaot;
    }

    @Override
    protected void initData() {
        mSharedPreferences = mContext.getSharedPreferences("loging", 0);
        mEdit = mSharedPreferences.edit();
        mEdit.putBoolean("loging", false);
        mEdit.commit();
        getActivity().finish();
    }
}
