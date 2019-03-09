package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.concat.ChangYong;

import com.jy.theplayandroid.playandroid.bean.ChuangYongBean;

import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.moudel.ChuangYongMoudel;


public class ChuangyongPresenter<V extends ChangYong.ChangYongV> extends BasePresenter<ChangYong.ChangYongV> implements ChangYong.ChangYongCallBack, ChangYong.ChangYongP {


    ChuangYongMoudel changyong = new ChuangYongMoudel();

    @Override
    public void setChangYong(ChuangYongBean dataBeans) {
        mView.showChangYong(dataBeans);
    }

    @Override
    public void setShowLoading() {

    }

    @Override
    public void setHideLoading() {

    }

    @Override
    public void setShowError(String error) {

    }


    @Override
    public void getChangYongP() {
        changyong.getZhishi(this);
    }
}
