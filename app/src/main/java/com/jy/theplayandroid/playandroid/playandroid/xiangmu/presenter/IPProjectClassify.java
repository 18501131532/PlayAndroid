package com.jy.theplayandroid.playandroid.playandroid.xiangmu.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.concat.ProjectClassify;
import com.jy.theplayandroid.playandroid.bean.ProjectClassifyData;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.moudle.IMProjectClassify;

import java.util.List;

public class IPProjectClassify<V extends ProjectClassify.ProjectClassifyV> extends BasePresenter<V> implements ProjectClassify.ProjectClassifyP ,ProjectClassify.ProjectClassifyCallBack {
    private IMProjectClassify mIMProjectClassify=new IMProjectClassify();
    @Override
    public void getProjectClassify() {
        if (mView!=null){
            mIMProjectClassify.getProjectClassify(this);
        }
    }

    @Override
    public void setProjectClassify(List<ProjectClassifyData.DataBean> dataBeans) {
        if (mView!=null){
            mView.showProjectClassify(dataBeans);
        }
    }

    @Override
    public void setShowLoading() {
        if (mView!=null){
            mView.showLoading();
        }
    }

    @Override
    public void setHideLoading() {
        if (mView!=null){
            mView.hideLoding();
        }
    }

    @Override
    public void setShowError(String error) {
        if (mView!=null){
            mView.showError(error);
        }
    }
}
