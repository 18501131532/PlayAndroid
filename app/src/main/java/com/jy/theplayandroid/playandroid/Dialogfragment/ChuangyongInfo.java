package com.jy.theplayandroid.playandroid.Dialogfragment;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.jy.theplayandroid.playandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChuangyongInfo extends AppCompatActivity {

    @BindView(R.id.chuangyong_activity_toolbar)
    Toolbar chuangyongActivityToolbar;
    @BindView(R.id.chuangyong_info_web)
    WebView chuangyongInfoWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuangyong_info);
        ButterKnife.bind(this);

        String title=getIntent().getStringExtra("title");
        String url=getIntent().getStringExtra("url");

        chuangyongActivityToolbar.setTitle(title);
        setSupportActionBar(chuangyongActivityToolbar);
//        getSupportActionBar().setNavigationMode(R.drawable.ic_arrow_back_grey_24dp);

        chuangyongActivityToolbar.setNavigationIcon(R.drawable.ic_arrow_back_grey_24dp);

        chuangyongActivityToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });








        // 关于 WebView   设置   的 对象
                WebSettings settings = chuangyongInfoWeb.getSettings();

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

        chuangyongInfoWeb.loadUrl(url);
        chuangyongInfoWeb.setWebViewClient(new WebViewClient());

    }
}
