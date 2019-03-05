package com.jy.theplayandroid.playandroid.playandroid.main.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.baseactivity.SimpleActivity;
import com.jy.theplayandroid.playandroid.util.ShareUtil;

import java.lang.reflect.Method;

import butterknife.BindView;

public class BannerDetailsActivity extends SimpleActivity {
    @BindView(R.id.banner_web_tooblar)
    Toolbar mBannerWebTooblar;
    @BindView(R.id.banner_web_web)
    WebView mBannerWebWeb;
    String mUrls;
    @BindView(R.id.banner_tv_toolbar)
    TextView mBannerTvToolbar;

    @Override
    protected int creatLoyoutId() {
        return R.layout.activity_banner_details;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mUrls = intent.getStringExtra("urls");
        String titles = intent.getStringExtra("titles");

        setSupportActionBar(mBannerWebTooblar);
        mBannerWebTooblar.setTitle("");
        mBannerTvToolbar.setText(titles);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        mBannerWebTooblar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.cardview_light_background), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        WebSettings settings = mBannerWebWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        mBannerWebWeb.loadUrl(mUrls);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.banner_meun,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.banner_item_share:
                ShareUtil.shareText(mActivity,mUrls,"分享");
                break;
            case R.id.banner_item_system_browser:
                Uri uri = Uri.parse(mUrls);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    //在menu中显示图标
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
