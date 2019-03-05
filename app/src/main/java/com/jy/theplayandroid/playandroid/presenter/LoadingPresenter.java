package com.jy.theplayandroid.playandroid.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.bean.LoadingBean;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.JsonBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.User;
import com.jy.theplayandroid.playandroid.playandroid.daohang.module.DaoHangModule;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;

public class LoadingPresenter<V extends TalkClassify.LoadingView> extends BasePresenter<V> implements TalkClassify.LoadingPresenter, TalkClassify.LoadingCallBack {
    DaoHangModule mDaoHangModule=new DaoHangModule();

    @Override
    public void setShowLoading() {

    }

    @Override
    public void setHideLoading() {

    }

    @Override
    public void setShowError(String error) {
        if (mView!=null){
            mView.showError(error);
        }
    }

    @Override
    public void getLoading(Map<String,Object> map) {
        if (mDaoHangModule!=null){
            mDaoHangModule.getLoading(map,this);
        }
    }

    @Override
    public void setLoading(LoadingBean loadingBean) {
        if (mView!=null){
            mView.showLoading(loadingBean);
        }
    }
}
