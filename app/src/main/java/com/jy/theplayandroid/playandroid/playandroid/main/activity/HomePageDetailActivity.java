package com.jy.theplayandroid.playandroid.playandroid.main.activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import com.jy.theplayandroid.playandroid.playandroid.daohang.activity.DaoHangInfoActivity;
import com.jy.theplayandroid.playandroid.utils.ShareUtil;

import java.lang.reflect.Method;

import butterknife.BindView;

public class HomePageDetailActivity extends SimpleActivity {
    @BindView(R.id.tv_toolbar)
    TextView mTvToolbar;
    @BindView(R.id.web_tooblar)
    Toolbar mWebTooblar;
    @BindView(R.id.web_web)
    WebView mWebWeb;
    String mUrl;
    public boolean mIsChange = false;
    private String title;
    private String id;
    private String auther;

    @Override
    protected int creatLoyoutId() {
        return R.layout.activity_home_page_detail;
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        title = intent.getStringExtra("title");
        id = getIntent().getStringExtra("id");
        auther = getIntent().getStringExtra("auther");

        setSupportActionBar(mWebTooblar);
        mWebTooblar.setTitle("");
        mTvToolbar.setText(title);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        mWebTooblar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_material);
        upArrow.setColorFilter(getResources().getColor(R.color.cardview_light_background), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        WebSettings settings = mWebWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        //不显示缩放按钮
        settings.setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        settings.setUseWideViewPort(true);
        //缩放至屏幕的大小
        settings.setLoadWithOverviewMode(true);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebWeb.loadUrl(mUrl);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tech_meun, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case android.R.id.home:
                finish();
                break;
               case R.id.item_like:
                   Intent intent1 = new Intent(this, DaoHangInfoActivity.class);
                   intent1.putExtra("title",title);
                   intent1.putExtra("id",id);
                   intent1.putExtra("link",mUrl);
                   intent1.putExtra("auther",auther);
                   startActivity(intent1);
                break;
            case R.id.item_share:
                ShareUtil.shareText(mActivity, mUrl, "分享");
                break;
            case R.id.item_system_browser:
                Uri uri = Uri.parse(mUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

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
