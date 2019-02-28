package com.jy.theplayandroid.playandroid.presenter;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.bean.TalkCalssifyBean;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.moudle.TalkClassifyMoudle;

import okhttp3.FormBody;

/**
 * Created by 段傅华 on 2019/2/27.
 */

public class TalkClassiyPresenter<V extends TalkClassify.TalkClassifyView> extends BasePresenter<V> implements TalkClassify.TalkClassifyIPresenter, TalkClassify.TalkClassCallBack {
    private TalkClassifyMoudle mMoudle=new TalkClassifyMoudle();
    @Override
    public void getTalkClassify(FormBody formBody) {
        mMoudle.setTalkClassify(formBody,this);
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

    @Override
    public void setSuccess(TalkCalssifyBean talkCalssifyBean) {
        if (mView!=null){
            mView.showSuccess(talkCalssifyBean);
        }
    }
}
