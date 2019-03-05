package com.jy.theplayandroid.playandroid.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.bean.LoadingBean;
import com.jy.theplayandroid.playandroid.bean.RegisterBean;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.playandroid.daohang.module.DaoHangModule;

import java.util.Map;

import okhttp3.RequestBody;

public class RegisterPresenter<V extends TalkClassify.RegisterView> extends BasePresenter<V> implements TalkClassify.RegisterPresenter, TalkClassify.RegisterCallBack {
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
    public void getRegister(RequestBody map) {
        if (mDaoHangModule!=null){
            mDaoHangModule.getRegister(map,this);
        }
    }

    @Override
    public void setRegister(RegisterBean loadingBean) {
        if (mView!=null){
            mView.showRegister(loadingBean);
        }
    }
}
