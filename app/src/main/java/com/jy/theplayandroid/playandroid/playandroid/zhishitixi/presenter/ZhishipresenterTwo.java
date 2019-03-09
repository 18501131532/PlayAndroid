package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.bean.TwoBEAN;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.interfaces.ZhishiTwo;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.moudel.ZhishiMoudelTwo;

public class ZhishipresenterTwo<V extends ZhishiTwo.twoView> extends BasePresenter<V> implements ZhishiTwo.twoMoudel,ZhishiTwo.twoPresenter {


    ZhishiMoudelTwo moudel=new ZhishiMoudelTwo();



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
    public void settwo(TwoBEAN twoBEAN) {
        mView.show(twoBEAN);
    }

    @Override
    public void gettwo(String page,String id) {
        moudel.getZhishiTwo(this,page,id);
    }
}
