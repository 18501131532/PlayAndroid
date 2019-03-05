package com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.module;

import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.bean.WxAuthor;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.concat.OfficialMarkConcat;
import com.jy.theplayandroid.playandroid.util.RxUtils;

public class OfficialMarkModule {
    public void getOfficialMarkBean(final OfficialMarkConcat.OfficialMarkCallBack callBack){
        HttpManager.getInstance().getServer(Global.BASE_URL).getWxAuthorListData()
                .compose(RxUtils.<WxAuthor>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<WxAuthor>(callBack) {
                    @Override
                    public void onNext(WxAuthor value) {
                        callBack.getOfficialMarkInfo(value);
                    }
                });
    }
}
