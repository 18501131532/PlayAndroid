package com.jy.theplayandroid.playandroid.playandroid.daohang.module;

import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.bean.LoadingBean;
import com.jy.theplayandroid.playandroid.bean.RegisterBean;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavroiteAddBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.Favruite;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.JsonBean;
import com.jy.theplayandroid.playandroid.util.RxUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class DaoHangModule {
    public void getDaoHangList(final TalkClassify.DaoHangCallBack daoHangCallBack, String json) {
        HttpManager.getInstance().getServer(Global.BASE_URL).getDaoHangList(json)
                .compose(RxUtils.<JsonBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JsonBean>(daoHangCallBack) {
                    @Override
                    public void onNext(JsonBean value) {
                        daoHangCallBack.setDaoHangList((ArrayList<JsonBean.DataBean>) value.getData());
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

//    public void getFavruiteWebDelete(FormBody map, final TalkClassify.FavruiteWebCallBack daoHangCallBack) {
//        HttpManager.getInstance().getServer(Global.BASE_URL).getFavruiteWebDelete(map)
//                .compose(RxUtils.<FavruiteWebDeleteBean>rxObserableSchedulerHelper())
//                .subscribe(new BaseObserver<FavruiteWebDeleteBean>(daoHangCallBack) {
//                    @Override
//                    public void onNext(FavruiteWebDeleteBean value) {
//                        daoHangCallBack.setFavruiteWebDelete(value);
//                    }
//                });
//    }

    public void getFavruite(int page, final TalkClassify.FavruiteWebCallBack daoHangCallBack) {
        HttpManager.getInstance().getServer(Global.BASE_URL).getFavruite(page)
                .compose(RxUtils.rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Favruite>(daoHangCallBack) {
                    @Override
                    public void onNext(Favruite favruite) {

                        daoHangCallBack.setFavruite(favruite);
                    }
                });
    }
}
