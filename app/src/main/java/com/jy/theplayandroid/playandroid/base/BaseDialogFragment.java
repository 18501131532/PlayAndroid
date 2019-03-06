package com.jy.theplayandroid.playandroid.base;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseDialogFragment extends DialogFragment{
    public Context context;
    public Activity mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context=context;
        this.mActivity= (Activity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(createLayoutId(), null,false);
        return view;
    }


    public abstract int createLayoutId();
}
