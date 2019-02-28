package com.jy.theplayandroid.playandroid.moudle;

import com.jy.theplayandroid.playandroid.base.baseobserver.BaseObserver;
import com.jy.theplayandroid.playandroid.bean.TalkCalssifyBean;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.global.Global;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.util.RxUtils;

import okhttp3.FormBody;

/**
 * Created by 段傅华 on 2019/2/27.
 */

public class TalkClassifyMoudle {
    public void setTalkClassify(FormBody formBody, final TalkClassify.TalkClassCallBack callBack){
        HttpManager.getInstance().getServer(Global.BASE_URL).getTalkClassifyBean(formBody)
                .compose(RxUtils.<TalkCalssifyBean>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<TalkCalssifyBean>(callBack) {
                    @Override
                    public void onNext(TalkCalssifyBean value) {
                        callBack.setSuccess(value);
                    }
                });
    }
}
