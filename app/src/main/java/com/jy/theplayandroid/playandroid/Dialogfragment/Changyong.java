package com.jy.theplayandroid.playandroid.Dialogfragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.DialogFragment;
import com.jy.theplayandroid.playandroid.concat.ChangYong;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.ChuangYongBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.presenter.ChuangyongPresenter;
import com.zhy.view.flowlayout.FlowLayout;

import butterknife.BindView;
import butterknife.Unbinder;

public class Changyong extends DialogFragment<ChangYong.ChangYongV, ChuangyongPresenter<ChangYong.ChangYongV>> implements ChangYong.ChangYongV {



    @BindView(R.id.flowlayout)
    FlowLayout flowlayout;
    Unbinder unbinder;

    @Override
    public ChuangyongPresenter<ChangYong.ChangYongV> createPresenter() {
        return new ChuangyongPresenter<>();
    }

    @Override
    protected void init() {
        Mpresenter.getChangYongP();
    }

    private void initDialog() {
        Window window = getDialog().getWindow();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        //DialogSearch的宽
        int width = (int) (metrics.widthPixels * 0.98);
        assert window != null;
        window.setLayout(width, WindowManager.LayoutParams.MATCH_PARENT);
        window.setGravity(Gravity.TOP);
        //取消过渡动画 , 使DialogSearch的出现更加平滑
        window.setWindowAnimations(R.style.DialogEmptyAnimation);
    }
    @Override
    public void onStart() {
        super.onStart();

//        Window window = getDialog().getWindow();
//        WindowManager.LayoutParams windowParams = window.getAttributes();
//        windowParams.dimAmount = 0.0f;
//        windowParams.y = 100;
//        window.setAttributes(windowParams);
//        Dialog dialog = getDialog();
//        if (dialog != null) {
//            DisplayMetrics dm = new DisplayMetrics();
//            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//            dialog.getWindow().setLayout((int) (dm.widthPixels *1), (int) (dm.heightPixels * 1));
//        }
        initDialog();
    }
    @Override
    public int createLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void showChangYong(ChuangYongBean dataBeans) {
        Log.i("=============", "showChangYong: " + dataBeans);
    }


}
