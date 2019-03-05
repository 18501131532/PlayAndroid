package com.jy.theplayandroid.playandroid.playandroid.zhishitixi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.theplayandroid.playandroid.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class infoActivity extends AppCompatActivity {

    @BindView(R.id.like_web)
    WebView likeWeb;

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private MenuItem items;
    private SharedPreferences.Editor ed;
    private SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        ButterKnife.bind(this);





        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //以下三行是修改回退按钮为白色的逻辑

        Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_material);

        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        getSupportActionBar().setDisplayShowTitleEnabled(false);




        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        Intent in = getIntent();
        String like = in.getStringExtra("like");

//        toolbar.setTitle(in.getStringExtra("name"));
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



        toolbar.setOnMenuItemClickListener(clickListener);


    }
    Toolbar.OnMenuItemClickListener clickListener=new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()){
                case R.id.like:

                    boolean checked = items.isChecked();
                    if(checked){

                        item.setIcon(R.mipmap.ic_toolbar_like_n);
                        items.setChecked(false);
                    }else{
                        item.setIcon(R.mipmap.ic_toolbar_like_p);
                        items.setChecked(true);

                    }
                    break;
            }
            return false;
        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("==========", "onCreateOptionsMenu: "+"123");
        menu.add(0,1,Menu.NONE,"分享");
        menu.add(0,2,Menu.NONE,"用系统浏览器打开");

        getMenuInflater().inflate(R.menu.toolbar,menu);
        items = menu.getItem(0);



        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       switch (item.getItemId()){
           case 1:
               Toast.makeText(this, "分享", Toast.LENGTH_SHORT).show();
               break;
           case 2:
               Toast.makeText(this, "用浏览器打开", Toast.LENGTH_SHORT).show();
               break;

       }
        return super.onOptionsItemSelected(item);
    }
}
