package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.presenter;

import android.view.View;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.OneBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.interfaces.ZhishiOne;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.moudel.ZhishiMoudel;

public class Zhishipresenter<V extends ZhishiOne.oneView> extends BasePresenter<V> implements ZhishiOne.oneMoudel {


    ZhishiMoudel moudel=new ZhishiMoudel();
    public void getones(){
        moudel.getZhishi(this);
    }

    @Override
    public void setOne(OneBean oneBean) {
        mView.show(oneBean);
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
}
