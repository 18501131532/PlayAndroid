package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.moudel;

import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.bean.TwoBEAN;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.interfaces.ZhishiTwo;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ZhishiMoudelTwo {

    public void getZhishiTwo(final ZhishiTwo.twoMoudel oneMoudel, String page, String id){
        HttpManager.getInstance().getServer(Global.BASE_URL).getZhishiTwo(page,id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<TwoBEAN>(oneMoudel) {
                    @Override
                    public void onNext(TwoBEAN value) {


                        value.getData().getDatas().size();
                        oneMoudel.settwo(value);
                    }
                });
    }

}
