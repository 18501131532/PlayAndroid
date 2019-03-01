package com.jy.theplayandroid.playandroid.playandroid.main.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.concat.AtricleList;
import com.jy.theplayandroid.playandroid.playandroid.main.bean.ArticleListBean;
import com.jy.theplayandroid.playandroid.playandroid.main.moudle.AtricleListMoudle;

/**
 * Created by 段傅华 on 2019/2/28.
 */

public class AtricleListPresenter<V extends AtricleList.AtricleListView> extends BasePresenter<V> implements AtricleList.AtricleListIPresenter, AtricleList.AtricleListCallBack {
    private AtricleListMoudle mMoudle=new AtricleListMoudle();
    @Override
    public void getAtricList(int page) {
        if (mView!=null){
            mMoudle.setAtricleList(page,this);
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

    @Override
    public void showSuccess(ArticleListBean listBean) {
            if (mView!=null){
                mView.showSuccess(listBean);
            }
    }
}
