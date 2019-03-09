package com.jy.theplayandroid.playandroid.Dialogfragment;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.DialogFragment;
import com.jy.theplayandroid.playandroid.concat.ChangYong;
import com.jy.theplayandroid.playandroid.global.MyApp;
import com.jy.theplayandroid.playandroid.playandroid.daohang.utils.FlowLayout;
import com.jy.theplayandroid.playandroid.bean.ChuangYongBean;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.presenter.ChuangyongPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class Changyong extends DialogFragment<ChangYong.ChangYongV, ChuangyongPresenter<ChangYong.ChangYongV>> implements ChangYong.ChangYongV {


    FlowLayout flowlayout;
    List<ChuangYongBean.DataBean> list = new ArrayList<>();
    @BindView(R.id.changyong_fanhui)
    ImageView changyongFanhui;



    @Override
    public ChuangyongPresenter<ChangYong.ChangYongV> createPresenter() {
        return new ChuangyongPresenter<>();
    }

    @Override
    protected void init() {
        flowlayout = getView().findViewById(R.id.flowlayout);


        changyongFanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });
        Mpresenter.getChangYongP();
    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }

    private void initDialog() {
        Window window = getDialog().getWindow();
//        生成popuwindows的宽高
        WindowManager manager = (WindowManager) MyApp.sMyApp.getSystemService(MyApp.sMyApp.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = manager.getDefaultDisplay().getHeight();
        window.setLayout(width, height);

        //取消过渡动画 , 使DialogSearch的出现更加平滑
        window.setWindowAnimations(R.style.DialogEmptyAnimation);

    }

    @Override
    public int createLayoutId() {
        return R.layout.fragment_search;
    }

    @Override
    public void showChangYong(ChuangYongBean dataBeans) {
        Log.i("=============", "showChangYong: " + dataBeans.getData().size() + "     " + flowlayout);
        list.addAll(dataBeans.getData());

        for (int i = 0; i < list.size(); i++) {
            final int j=i;
            Random myRandom = new Random();
            int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
            TextView tv = (TextView) LayoutInflater.from(MyApp.sMyApp).inflate(R.layout.layout, flowlayout, false);
                tv.setText(list.get(i).getName());
            tv.setBackgroundColor(ranColor);
            flowlayout.addView(tv);



            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(context, ""+j, Toast.LENGTH_SHORT).show();
                    Intent in=new Intent(MyApp.sMyApp,ChuangyongInfo.class);
                    in.putExtra("title",list.get(j).getName());
                    in.putExtra("url",list.get(j).getLink());
                    startActivity(in);
                }
            });
        }

    }



}
