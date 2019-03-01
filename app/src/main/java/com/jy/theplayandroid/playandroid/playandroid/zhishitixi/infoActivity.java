package com.jy.theplayandroid.playandroid.playandroid.zhishitixi;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class infoActivity extends AppCompatActivity {

    @BindView(R.id.like_web)
    WebView likeWeb;
    @BindView(R.id.fanhui)
    ImageView fanhui;
    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);




        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent in = getIntent();
        String like = in.getStringExtra("like");
        title.setText(in.getStringExtra("name"));
        // 关于 WebView   设置   的 对象
        WebSettings settings = likeWeb.getSettings();

        settings.setJavaScriptEnabled(true);// 支持js 功能
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        settings.setLoadWithOverviewMode(true);// 适应屏幕
        settings.setUseWideViewPort(true);// 图片适应 WebView

        settings.setSupportZoom(true);// 支持缩放
        settings.setBuiltInZoomControls(true);// 设置  view 可 缩放

        //隐藏原有的缩放
        settings.setDisplayZoomControls(true);

        //关闭缓存
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setAllowFileAccess(true);// 支持访问文件
        settings.setJavaScriptCanOpenWindowsAutomatically(true);//  支持打开新窗口
        settings.setLoadsImagesAutomatically(true);// 支持自动加载图片
        settings.setDefaultTextEncodingName("utf-8"); //  设置加载编码

        likeWeb.loadUrl(like);
        likeWeb.setWebViewClient(new WebViewClient());

    }
}
