package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.bean.OneBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.interfaces.ZhishiOne;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.moudel.ZhishiMoudel;

public class Zhishipresenter<V extends ZhishiOne.oneView> extends BasePresenter<V> implements ZhishiOne.oneMoudel,ZhishiOne.onePresenter {


    ZhishiMoudel moudel=new ZhishiMoudel();

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

    @Override
    public void getone() {
        moudel.getZhishi(this);
    }
}
