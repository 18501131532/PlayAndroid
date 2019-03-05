package com.jy.theplayandroid.playandroid.playandroid.daohang.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.jy.theplayandroid.playandroid.base.Httpresultcallback;
import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.bean.LoadingBean;
import com.jy.theplayandroid.playandroid.bean.RegisterBean;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.global.MyApp;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavroiteAddBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.Favruite;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavruiteWebDeleteBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.HttpResult;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.JsonBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.User;
import com.jy.theplayandroid.playandroid.playandroid.daohang.utils.ExceptionManager;
import com.jy.theplayandroid.playandroid.util.RxUtils;
import com.trello.rxlifecycle2.android.FragmentEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
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

    public void getFavruite(FormBody map, final TalkClassify.FavruiteWebCallBack daoHangCallBack) {
        HttpManager.getInstance().getServer(Global.BASE_URL).getFavruite(map)
                .compose(RxUtils.<Favruite>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<Favruite>(daoHangCallBack) {
                    @Override
                    public void onNext(Favruite value) {
                        daoHangCallBack.setFavruite(value);
                    }
                });
    }
}
