package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.concat.SearchApi;
import com.jy.theplayandroid.playandroid.bean.SearchBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.moudel.SearchMoudel;

public class SearchPersenter<V extends SearchApi.SearchV> extends BasePresenter<SearchApi.SearchV>implements SearchApi.SearchM,SearchApi.SearchP {
    SearchMoudel searchMoudel=new SearchMoudel();
    @Override
    public void setSearch(SearchBean search) {
        mView.showSearch(search);
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
    public void getSearch(String k) {
        searchMoudel.setSearchMoudel(this,k);
    }
}
