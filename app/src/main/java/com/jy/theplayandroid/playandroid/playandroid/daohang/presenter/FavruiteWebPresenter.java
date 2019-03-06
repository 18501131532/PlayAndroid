package com.jy.theplayandroid.playandroid.playandroid.daohang.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavroiteAddBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.Favruite;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.HttpResult;
import com.jy.theplayandroid.playandroid.playandroid.daohang.module.DaoHangModule;

import java.util.List;
import java.util.Map;

import okhttp3.FormBody;

public class FavruiteWebPresenter<V extends TalkClassify.FavruiteWebView> extends BasePresenter<V> implements TalkClassify.FavruiteWebPresenter, TalkClassify.FavruiteWebCallBack {

    DaoHangModule mDaoHangModule = new DaoHangModule();

    @Override
    public void setShowLoading() {

    }

    @Override
    public void setHideLoading() {

    }

    @Override
    public void setShowError(String error) {
        if (mView != null) {
            mView.showError(error);
        }
    }

    @Override
    public void getFavruiteWeb(Map<String, Object> formBody) {
        if (mDaoHangModule != null) {
            mDaoHangModule.getFavruiteWeb(formBody, this);
        }
    }

    @Override
    public void getFavruiteWebDelete(Map<String, Object> formBody) {
        if (mDaoHangModule != null) {
            mDaoHangModule.getFavruiteWebDelete(formBody, this);
        }
    }

    @Override
    public void setFavruiteWeb(FavroiteAddBean favruiteWeb) {
        if (mView != null) {
            mView.showFavruiteWeb(favruiteWeb);
        }
    }

    @Override
    public void setFavruite(Favruite favruiteWeb) {
        if (mView != null) {
            mView.showFavruite(favruiteWeb);
        }
    }

    @Override
    public void setFavruiteWebDelete(HttpResult favruiteWeb) {
        if (mView != null) {
            mView.showFavruiteWebDelete(favruiteWeb);
        }
    }

    public void getFavruite(int page) {
        if (mDaoHangModule != null) {
            mDaoHangModule.getFavruite(page, this);
        }
    }
}
