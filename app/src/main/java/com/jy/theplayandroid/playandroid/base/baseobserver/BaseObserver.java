package com.jy.theplayandroid.playandroid.base.baseobserver;

import android.util.Log;

import com.jy.theplayandroid.playandroid.base.basemoudle.HttpFinishCallBack;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by 段傅华 on 2019/1/18.
 */

public abstract class BaseObserver<T> implements Observer<T>{
    //处理回调结果
    private HttpFinishCallBack mHttpFinishCallBack;

    public BaseObserver(HttpFinishCallBack httpFinishCallBack) {
        this.mHttpFinishCallBack = httpFinishCallBack;
    }
    //管理内存网络请求
    private CompositeDisposable mCompositeDisposable=new CompositeDisposable();

    @Override
    public void onSubscribe(Disposable d) {
        mCompositeDisposable.add(d);
    }
    @Override
    public void onError(Throwable e) {
        if (mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
        String error=null;
        if (mHttpFinishCallBack!=null){
            mHttpFinishCallBack.setShowError(error);
        }
        mHttpFinishCallBack.setHideLoading();
    }

    @Override
    public void onComplete() {
        if (mCompositeDisposable!=null){
            mCompositeDisposable.clear();
        }
    }
}
