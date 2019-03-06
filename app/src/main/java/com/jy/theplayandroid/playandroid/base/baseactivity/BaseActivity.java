package com.jy.theplayandroid.playandroid.base.baseactivity;
import android.graphics.Color;
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

public abstract class BaseActivity<V,P extends BasePresenter<V>> extends SimpleActivity implements Base_View{
    public P mPresenter;
    RelativeLayout mGroup;
    LottieAnimationView mAnimation;

    @Override
    public void ViewCreat(View view) {
        super.ViewCreat(view);
        //找到动画布局
        View inflate = View.inflate(this,R.layout.loading_view, (ViewGroup) view);
        //找到动画中控件
        mGroup = inflate.findViewById(R.id.loading_group);
        mAnimation = inflate.findViewById(R.id.loading_animation);
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
    //显示加载动画
    @Override
    public void showLoading() {
        mGroup.setVisibility(View.VISIBLE);
        mAnimation.setAnimation("loading_bus.json");
        mAnimation.loop(true);
        mAnimation.playAnimation();
    }
    //隐藏加载动画
    @Override
    public void hideLoding() {
        mGroup.setVisibility(View.GONE);
        mAnimation.cancelAnimation();
    }
}
