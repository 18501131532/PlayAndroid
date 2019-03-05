package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.moudel;

import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.concat.ChangYong;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.ChuangYongBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ChuangYongMoudel {

    public void getZhishi(final ChangYong.ChangYongCallBack callback){
        HttpManager.getInstance().getServer(Global.BASE_URL).getchangyong()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ChuangYongBean>(callback) {
                    @Override
                    public void onNext(ChuangYongBean value) {
                        callback.setChangYong(value);
                    }
                });
    }
}
