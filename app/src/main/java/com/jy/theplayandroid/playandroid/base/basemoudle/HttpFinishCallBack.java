package com.jy.theplayandroid.playandroid.base.basemoudle;

/**
 * Created by 段傅华 on 2019/1/18.
 */

public interface HttpFinishCallBack {
    void setShowLoading();
    void setHideLoading();
    void setShowError(String error);
}
