package com.jy.theplayandroid.playandroid.playandroid.daohang.module;

import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.JsonBean;
import com.jy.theplayandroid.playandroid.util.RxUtils;

import java.util.ArrayList;

public class DaoHangModule {
    public void getDaoHangList(String json, final TalkClassify.DaoHangCallBack daoHangCallBack) {
        HttpManager.getInstance().getServer(Global.BASE_URL).getDaoHangList(json)
                .compose(RxUtils.<JsonBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<JsonBean>(daoHangCallBack) {
                    @Override
                    public void onNext(JsonBean value) {
                        daoHangCallBack.setDaoHangList((ArrayList<JsonBean.DataBean>) value.getData());
                    }
                });
    }
}
