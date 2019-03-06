package com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.module;

import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.bean.FeedArticleListData;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.concat.FeedArticleListConcat;
import com.jy.theplayandroid.playandroid.util.RxUtils;

import java.util.HashMap;

public class FeedArticleModule {
    public void getFeedArticleBean(int id, int page, final FeedArticleListConcat.FeedArticleCallBack callBack){
        HttpManager.getInstance().getServer(Global.BASE_URL).getWxSumData(id,page)
                .compose(RxUtils.<FeedArticleListData>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<FeedArticleListData>(callBack) {
                    @Override
                    public void onNext(FeedArticleListData value) {
                        callBack.getFeedArticleInfo(value);
                    }
                });
    }

    public void getWxSearchSumData(int id, int page, String k, final FeedArticleListConcat.FeedArticleCallBack callBack) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k",k);
        HttpManager.getInstance().getServer(Global.BASE_URL).getWxSearchSumData(id, page, map)
                .compose(RxUtils.<FeedArticleListData>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<FeedArticleListData>(callBack) {
                    @Override
                    public void onNext(FeedArticleListData value) {
                        callBack.getFeedArticleInfo(value);
                    }
                });
    }
}
