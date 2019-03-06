package com.jy.theplayandroid.playandroid.base.baseactivity;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.jy.theplayandroid.playandroid.global.MyApp;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import static android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE;
import static android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;

/**
 * Created by 段傅华 on 2019/1/17.
 */

public abstract class SimpleActivity extends AppCompatActivity {
    public Activity mActivity;
    Unbinder mBind;
    public Observable observable;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = LayoutInflater.from(this).inflate(creatLoyoutId(), null, false);
        setContentView(view);
        mBind = ButterKnife.bind(this);
        ViewCreat(view);
        mActivity = this;

        initData();
        initView();
    }

    public void initView() {

    }

    public void ViewCreat(View view) {

    }

    //初始化布局
    protected abstract int creatLoyoutId();

    //初始化数据
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("duanxq222", "onDestroy: " );
        if (mBind != null) {
            mBind.unbind();
        }
        //initBack();
    }

    public void initBack() {

    }
}
