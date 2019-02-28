package com.jy.theplayandroid.playandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.jy.theplayandroid.playandroid.base.baseactivity.BaseActivity;
import com.jy.theplayandroid.playandroid.bean.TalkCalssifyBean;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.presenter.TalkClassiyPresenter;

import okhttp3.FormBody;

public class MainActivity extends BaseActivity<TalkClassify.TalkClassifyView,TalkClassiyPresenter<TalkClassify.TalkClassifyView>> implements TalkClassify.TalkClassifyView{
    //郭志俊第一次上传=====================

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoding() {

    }


    @Override
    public void showError(String error) {

    }

    @Override
    public void showSuccess(TalkCalssifyBean talkCalssifyBean) {
        Log.e("duan", "showSuccess: "+talkCalssifyBean.getData());
    }

    @Override
    protected TalkClassiyPresenter<TalkClassify.TalkClassifyView> creatPresenter() {
        return new TalkClassiyPresenter<>();
    }

    @Override
    protected int creatLoyoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        FormBody formBody = new FormBody.Builder()
                .add("", "")
                .build();
        mPresenter.getTalkClassify(formBody);
    }
}
