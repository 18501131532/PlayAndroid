package com.jy.theplayandroid.playandroid.base.basepresenter;

/**
 * Created by 段傅华 on 2019/1/18.
 */

public interface Base_Presenter<V>{
    //绑定View
    void attachView(V v);
    //解绑View
    void detachView();
}
