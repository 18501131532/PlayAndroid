package com.jy.theplayandroid.playandroid.Dialogfragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.DialogFragment;
import com.jy.theplayandroid.playandroid.concat.SearchHotkey;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.presenter.IPSearchHotkey;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.SearchHotkeyBean;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sousuo extends DialogFragment<SearchHotkey.SearchHotkeyV,IPSearchHotkey<SearchHotkey.SearchHotkeyV>> implements SearchHotkey.SearchHotkeyV {


    public Sousuo() {
        // Required empty public constructor
    }


    @Override
    public int createLayoutId() {
        return R.layout.fragment_sousuo;
    }

    @Override
    protected void init() {

    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }


    private void initDialog() {
        Window window = getDialog().getWindow();
//        生成popuwindows的宽高
        WindowManager manager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = manager.getDefaultDisplay().getHeight();
        window.setLayout(width, height);

        //取消过渡动画 , 使DialogSearch的出现更加平滑
        window.setWindowAnimations(R.style.DialogEmptyAnimation);

    }

    @Override
    public IPSearchHotkey<SearchHotkey.SearchHotkeyV> createPresenter() {
        return new IPSearchHotkey<>();
    }

    @Override
    public void showSearchHotkey(List<SearchHotkeyBean.DataBean> dataBeans) {

    }
}
