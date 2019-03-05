package com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.bean.FeedArticleListData;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.concat.FeedArticleListConcat;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.module.FeedArticleModule;

public class FeedArticlePresenter<V extends FeedArticleListConcat.FeedArticleView> extends BasePresenter<V>
implements FeedArticleListConcat.FeedArticlePresenter, FeedArticleListConcat.FeedArticleCallBack {
    private FeedArticleModule module=new FeedArticleModule();
    @Override
    public void setData(int id, int name) {
        if (module!=null){
            module.getFeedArticleBean(id,name,this);
        }
    }

    @Override
    public void getFeedArticleInfo(FeedArticleListData feedArticleListData) {
        if (mView!=null){
            mView.showFeedArticleInfo(feedArticleListData);
        }
    }

    @Override
    public void setShowLoading() {

    }

    @Override
    public void setHideLoading() {

    }

    @Override
    public void setShowError(String error) {

    }
}