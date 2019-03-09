package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.moudel;

import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.concat.SearchApi;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.bean.SearchBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SearchMoudel {

    public void setSearchMoudel(final SearchApi.SearchM searchMoudel,String k){
        HttpManager.getInstance().getServer(Global.BASE_URL).getsearch(k)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<SearchBean>(searchMoudel) {
                    @Override
                    public void onNext(SearchBean value) {
                        searchMoudel.setSearch(value);
                    }
                });
    }

}
