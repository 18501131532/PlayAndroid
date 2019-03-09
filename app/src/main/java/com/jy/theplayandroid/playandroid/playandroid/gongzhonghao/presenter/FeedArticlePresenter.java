package com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.bean.FeedArticleListData;
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
    public void getWxSearchSumData(int id, int page, String k) {
        if (module!=null){
            module.getWxSearchSumData(id,page,k,this);
        }
    }

    @Override
    public void getFeedArticleInfo(FeedArticleListData feedArticleListData) {
        if (mView!=null){
            mView.showWxSearchView(feedArticleListData);
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
