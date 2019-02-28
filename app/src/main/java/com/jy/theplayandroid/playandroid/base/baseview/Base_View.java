package com.jy.theplayandroid.playandroid.base.baseview;

/**
 * Created by 段傅华 on 2019/1/18.
 */

public interface Base_View {
    //显示进度条
    void showLoading();
    //隐藏进度条
    void hideLoding();
    //错误
    void showError(String error);
}
