package com.jy.theplayandroid.playandroid.base.basefragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;


/**
 * Created by 段傅华 on 2019/1/17.
 */

public abstract class BaseFragment<V,P extends BasePresenter<V>>extends SimpleFragment implements Base_View {
    public P mPresenter;
    RelativeLayout mGroup;
    LottieAnimationView mAnimation;

    @Override
    public void viewCreate(View view) {
        super.viewCreate(view);
        //找到动画布局
        View inflate = View.inflate(mContext,R.layout.loading_view, (ViewGroup) view);
        //找到动画中控件
        mGroup = inflate.findViewById(R.id.loading_group);
        mAnimation = inflate.findViewById(R.id.loading_animation);
        mPresenter=createPresenter();
        if (mPresenter!=null){
            mPresenter.attachView((V) this);
        }
    }

    @Override
    public void load() {
        super.load();
        if(mPresenter==null){
            mPresenter = createPresenter();
            if (mPresenter != null) {
                mPresenter.attachView((V) this);
            }
        }
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

    @Override
    public void showLoading() {
        if(mGroup!=null&&mAnimation!=null){
            mGroup.setVisibility(View.VISIBLE);
            mAnimation.setAnimation("loading_bus.json");
            mAnimation.loop(true);
            mAnimation.playAnimation();
        }

    }

    @Override
    public void hideLoding() {
        mGroup.setVisibility(View.GONE);
        mAnimation.cancelAnimation();
    }
}
