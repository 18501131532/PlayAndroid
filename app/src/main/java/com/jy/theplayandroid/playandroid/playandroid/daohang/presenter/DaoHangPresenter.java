package com.jy.theplayandroid.playandroid.playandroid.daohang.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.bean.JsonBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.module.DaoHangModule;

import java.util.ArrayList;

public class DaoHangPresenter<V extends TalkClassify.DaoHangView> extends BasePresenter<V> implements TalkClassify.DaoHangPresenter, TalkClassify.DaoHangCallBack {
    DaoHangModule mDaoHangModule=new DaoHangModule();

    @Override
    public void getDaoHang(String json) {
        if (mDaoHangModule!=null){
            mDaoHangModule.getDaoHangList(this,json);
        }
    }

    @Override
    public void setDaoHangList(ArrayList<JsonBean.DataBean> arrayList) {
        if (mView!=null){
            mView.showList(arrayList);
        }
    }

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
}
