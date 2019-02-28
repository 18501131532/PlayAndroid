package com.jy.theplayandroid.playandroid.base.baseactivity;
import android.graphics.Color;
import android.view.View;

import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;


/**
 * Created by 段傅华 on 2019/1/17.
 */

public abstract class BaseActivity<V,P extends BasePresenter<V>> extends SimpleActivity implements Base_View{
    public P mPresenter;

    @Override
    public void ViewCreat(View view) {
        super.ViewCreat(view);
        mPresenter=creatPresenter();
        if (mPresenter!=null){
            mPresenter.attachView((V) this);
        }
    }
    //创建P层
    protected abstract P creatPresenter();
    //解绑View
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter!=null){
            mPresenter.detachView();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoding() {

    }
}
