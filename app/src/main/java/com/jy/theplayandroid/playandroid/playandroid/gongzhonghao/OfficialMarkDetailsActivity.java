package com.jy.theplayandroid.playandroid.playandroid.gongzhonghao;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.bean.SharePopupWindow;

public class OfficialMarkDetailsActivity extends AppCompatActivity {
    private Toolbar mark_toolbar;
    private WebView mark_web;
    private TextView toolbar1;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_official_mark_details);

        initView();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");
        String title = intent.getStringExtra("title");
        mark_toolbar=findViewById(R.id.mark_toolbar);
        mark_web=findViewById(R.id.mark_web);
        toolbar1=findViewById(R.id.toolbar1);
        mark_toolbar.setTitle("");
        toolbar1.setText(title);
        mark_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);

        setSupportActionBar(mark_toolbar);

        mark_web.setWebChromeClient(new WebChromeClient());
        mark_web.loadUrl(link);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tech_meun,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_like:
                break;
            case R.id.item_share://分享
                String shareContent="#神码工作室#博客地址：https://blog.csdn.net/qq15577969";
                SharePopupWindow spw = new SharePopupWindow(this, shareContent);
                // 显示窗口
                spw.showAtLocation(mark_web, Gravity.BOTTOM, 0, 0);

                break;
            case R.id.item_system_browser://在浏览器打开
                break;
        }
        return true;
    }
}
