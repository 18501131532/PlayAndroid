package com.jy.theplayandroid.playandroid.playandroid.daohang.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavroiteAddBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.Favruite;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavruiteBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavruiteWebDeleteBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.JsonBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.module.DaoHangModule;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.FormBody;

public class FavruiteWebPresenter<V extends TalkClassify.FavruiteWebView> extends BasePresenter<V> implements TalkClassify.FavruiteWebPresenter, TalkClassify.FavruiteWebCallBack {

    DaoHangModule mDaoHangModule=new DaoHangModule();

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

    @Override
    public void getFavruiteWeb(Map<String,Object> formBody) {
        if (mDaoHangModule!=null){
            mDaoHangModule.getFavruiteWeb(formBody,this);
        }
    }

    @Override
    public void getFavruiteWebDelete(FormBody formBody) {
        if (mDaoHangModule!=null){
//            mDaoHangModule.getFavruiteWebDelete(formBody,this);
        }
    }

    @Override
    public void getFavruite(FormBody formBody) {
        if (mDaoHangModule!=null){
            mDaoHangModule.getFavruite(formBody,this);
        }
    }

    @Override
    public void setFavruiteWeb(FavroiteAddBean favruiteWeb) {
        if (mView!=null){
            mView.showFavruiteWeb(favruiteWeb);
        }
    }

    @Override
    public void setFavruiteWebDelete(FavruiteWebDeleteBean favruiteWeb) {
        if (mView!=null){
            mView.showFavruiteWebDelete(favruiteWeb);
        }
    }

    @Override
    public void setFavruite(Favruite favruiteWeb) {
        if (mView!=null){
            mView.showFavruite(favruiteWeb);
        }
    }
}
