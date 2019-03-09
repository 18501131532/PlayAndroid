package com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.bean.WxAuthor;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.concat.OfficialMarkConcat;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.module.OfficialMarkModule;

public class OfficialMarkPresenter<V extends OfficialMarkConcat.OfficialMarkView> extends BasePresenter<V> implements OfficialMarkConcat.OfficialMarkPresenter, OfficialMarkConcat.OfficialMarkCallBack {
    private OfficialMarkModule markModule=new OfficialMarkModule();
    @Override
    public void setData() {
        markModule.getOfficialMarkBean(this);
    }

    @Override
    public void getOfficialMarkInfo(WxAuthor wxAuthor) {
        if (mView!=null){
            mView.showOfficialMarkInfo(wxAuthor);
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

    }
}
