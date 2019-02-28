package com.jy.theplayandroid.playandroid.base.basefragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;


/**
 * Created by 段傅华 on 2019/1/17.
 */

public abstract class BaseFragment<V,P extends BasePresenter<V>>extends SimpleFragment implements Base_View {
    public P mPresenter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mPresenter=createPresenter();
        if (mPresenter!=null){
            mPresenter.attachView((V) this);
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void load() {
        super.load();
    }

    protected abstract P createPresenter();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
            mPresenter=null;
        }
    }
}
