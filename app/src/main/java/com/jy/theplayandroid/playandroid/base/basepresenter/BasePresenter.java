package com.jy.theplayandroid.playandroid.base.basepresenter;

import java.lang.ref.WeakReference;

/**
 * Created by 段傅华 on 2019/1/18.
 */

public class BasePresenter<V> implements Base_Presenter<V>{
    //定义View
    public V mView;
    //弱引用
    private WeakReference<V> mWeakReference;
    @Override
    public void attachView(V v) {
        mWeakReference=new WeakReference<V>(v);
        mView=mWeakReference.get();
    }

    @Override
    public void detachView() {
        if (mWeakReference!=null&&mWeakReference.get()!=null){
            mWeakReference.clear();
            mWeakReference=null;
        }
    }

}
