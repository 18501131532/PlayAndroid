package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.moudel;

import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.bean.OneBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.interfaces.ZhishiOne;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ZhishiMoudel {

    public void getZhishi(final ZhishiOne.oneMoudel oneMoudel){
        HttpManager.getInstance().getServer(Global.BASE_URL).getZhishiOne()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<OneBean>(oneMoudel) {
                    @Override
                    public void onNext(OneBean value) {
                        oneMoudel.setOne(value);
                    }
                });
    }

}
