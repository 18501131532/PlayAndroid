package com.jy.theplayandroid.playandroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.basepresenter.BasePresenter;
import com.jy.theplayandroid.playandroid.base.baseview.Base_View;

public abstract class DialogFragment<V,P extends BasePresenter<V>> extends BaseDialogFragment implements Base_View {

    public P Mpresenter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Mpresenter=createPresenter();
        if(Mpresenter!=null){
            Mpresenter.attachView((V) this);
        }

        init();

    }

    protected abstract void init();

    public abstract P createPresenter();

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoding() {

    }

    @Override
    public void showError(String error) {

    }
}
