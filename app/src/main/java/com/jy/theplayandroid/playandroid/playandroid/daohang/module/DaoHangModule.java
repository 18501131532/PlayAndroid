package com.jy.theplayandroid.playandroid.playandroid.daohang.module;

import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.bean.LoadingBean;
import com.jy.theplayandroid.playandroid.bean.RegisterBean;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.bean.FavroiteAddBean;
import com.jy.theplayandroid.playandroid.bean.Favruite;
import com.jy.theplayandroid.playandroid.bean.HttpResult;
import com.jy.theplayandroid.playandroid.bean.JsonBean;
import com.jy.theplayandroid.playandroid.utils.RxUtils;

import java.util.ArrayList;
import java.util.Map;

import okhttp3.RequestBody;

public class DaoHangModule {
    public void getDaoHangList(final TalkClassify.DaoHangCallBack daoHangCallBack, String json) {
        daoHangCallBack.setShowLoading();
        HttpManager.getInstance().getServer(Global.BASE_URL).getDaoHangList(json)
                .compose(RxUtils.<JsonBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JsonBean>(daoHangCallBack) {
                    @Override
                    public void onNext(JsonBean value) {
                        daoHangCallBack.setDaoHangList((ArrayList<JsonBean.DataBean>) value.getData());
                        daoHangCallBack.setHideLoading();
                    }
                });
    }

    public void getLoading(Map<String,Object> map, final TalkClassify.LoadingCallBack daoHangCallBack) {
        HttpManager.getInstance().getServer(Global.BASE_URL).getLoading(map)
                .compose(RxUtils.<LoadingBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoadingBean>(daoHangCallBack) {
                    @Override
                    public void onNext(LoadingBean value) {
                        daoHangCallBack.setLoading(value);
                    }
                });
    }

    public void getRegister(RequestBody map, final TalkClassify.RegisterCallBack daoHangCallBack) {
        HttpManager.getInstance().getServer(Global.BASE_URL).getRegister(map)
                .compose(RxUtils.<RegisterBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<RegisterBean>(daoHangCallBack) {
                    @Override
                    public void onNext(RegisterBean value) {
                        daoHangCallBack.setRegister(value);
                    }
                });
    }

    public void getFavruiteWeb(Map<String,Object> map, final TalkClassify.FavruiteWebCallBack daoHangCallBack) {
        HttpManager.getInstance().getServer(Global.BASE_URL).getFavruiteWebAdd(map)
                .compose(RxUtils.<FavroiteAddBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<FavroiteAddBean>(daoHangCallBack) {
                    @Override
                    public void onNext(FavroiteAddBean value) {
                        daoHangCallBack.setFavruiteWeb(value);
                    }
                });
    }

    public void getFavruiteWebDelete(int id,int orid, final TalkClassify.FavruiteWebCallBack daoHangCallBack) {
        HttpManager.getInstance().getServer(Global.BASE_URL).getFavruiteWebDelete(id,orid)
                .compose(RxUtils.<HttpResult>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<HttpResult>(daoHangCallBack) {
                    @Override
                    public void onNext(HttpResult value) {
                        daoHangCallBack.setFavruiteWebDelete(value);
                    }
                });
    }

    public void getFavruite(int page, final TalkClassify.FavruiteWebCallBack daoHangCallBack) {
        HttpManager.getInstance().getServer(Global.BASE_URL).getFavruite(page)
                .compose(RxUtils.<Favruite>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Favruite>(daoHangCallBack) {
                    @Override
                    public void onNext(Favruite value) {
                        daoHangCallBack.setFavruite(value);
                    }
                });
    }
}
