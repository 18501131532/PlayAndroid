package com.jy.theplayandroid.playandroid.playandroid.xiangmu.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.concat.ProjectListData;
import com.jy.theplayandroid.playandroid.bean.ProjectListBean;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.moudle.IMProjectListData;

import java.util.List;

public class IPProjectListData<V extends ProjectListData.ProjectListV> extends BasePresenter<V> implements ProjectListData.ProjectListP ,ProjectListData.ProjectListCallBack{
    private IMProjectListData mIMProjectListData=new IMProjectListData();
    @Override
    public void getProjectList(int page, int cid) {
        if (mView!=null){
            mIMProjectListData.getProjectListData(page,cid,this);
        }
    }

    @Override
    public void setProjectList(List<ProjectListBean.DataBean.DatasBean> dataBeans) {
        if (mView!=null){
            mView.showProjectList(dataBeans);
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
