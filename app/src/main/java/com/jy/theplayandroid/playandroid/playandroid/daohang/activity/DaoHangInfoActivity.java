package com.jy.theplayandroid.playandroid.playandroid.daohang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jy.theplayandroid.playandroid.LoadingActivity;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.baseactivity.BaseActivity;
import com.jy.theplayandroid.playandroid.base.baseactivity.SimpleActivity;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.Bean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavroiteAddBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.Favruite;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavruiteBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.FavruiteWebDeleteBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.presenter.FavruiteWebPresenter;
import com.jy.theplayandroid.playandroid.util.ShareUtil;
import com.jy.theplayandroid.playandroid.util.StatusBarUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;

public class DaoHangInfoActivity extends BaseActivity<TalkClassify.FavruiteWebView, FavruiteWebPresenter<TalkClassify.FavruiteWebView>> implements TalkClassify.FavruiteWebView {

    @BindView(R.id.toolbar_daohanginfo)
    Toolbar toolbarDaohanginfo;
    @BindView(R.id.wv_daohanginfo)
    WebView wvDaohanginfo;
    private String mWeb;
    private String mAuther;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEdit;
    private String mId;
    private Menu mMenu1;
    boolean isClick = true;
    private String mTitle;

    @Override
    protected int creatLoyoutId() {
        return R.layout.activity_dao_hang_info;
    }

    @Override
    protected void initData() {
        mSharedPreferences = getSharedPreferences("loging", 0);
        mEdit = mSharedPreferences.edit();

        mTitle = getIntent().getStringExtra("title");
        mId = getIntent().getStringExtra("id");
        Log.e("ppppppp", "initData: " + mId);
        mWeb = getIntent().getStringExtra("link");
        mAuther = getIntent().getStringExtra("auther");
        Log.e("web", "initData: " + mWeb);

        toolbarDaohanginfo.setTitle(mTitle);
        setSupportActionBar(toolbarDaohanginfo);
        toolbarDaohanginfo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);

        //百度
        WebSettings settings = wvDaohanginfo.getSettings();
        settings.setJavaScriptEnabled(true);
//        wvDaohanginfo.setWebChromeClient(new WebChromeClient() {
//            @Override
//            public void onReceivedTitle(WebView view, String title) {
//                toolbarDaohanginfo.setTitle(title);
//            }
//        });
        wvDaohanginfo.loadUrl(mWeb);
        wvDaohanginfo.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    private void initWeb() {
        //title，author，link
        HashMap<String, Object> map = new HashMap<>();
        map.put("title",mTitle);
        map.put("author",mAuther);
        map.put("link",mWeb);
        mPresenter.getFavruiteWeb(map);
    }

    //菜单图标不显示
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_meun, menu);

        mPresenter.getFavruite(null);

        mMenu1 = menu;
        return true;
    }

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iteminfo_like:
                boolean loading = mSharedPreferences.getBoolean("loging", false);
                if (loading) {
                    if (isClick) {
                        initWeb();
                        item.setIcon(R.mipmap.ic_toolbar_like_p);
                        isClick = false;
                    } else {
                        FormBody formBody = new FormBody.Builder()
                                .add("id", mId)
                                .build();
                        mPresenter.getFavruiteWebDelete(formBody);
                        item.setIcon(R.mipmap.ic_toolbar_like_n);
                        isClick = true;
                    }
                } else {
                    startActivity(new Intent(DaoHangInfoActivity.this, LoadingActivity.class));
                }
                break;
            case R.id.iteminfo_share:
                ShareUtil.shareText(mActivity, mWeb, "分享");
                break;
            case R.id.iteminfo_system_browser:
                Uri uri = Uri.parse(mWeb);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected FavruiteWebPresenter<TalkClassify.FavruiteWebView> creatPresenter() {
        return new FavruiteWebPresenter<>();
    }

    @Override
    public void showFavruiteWeb(FavroiteAddBean favruiteBean) {
        if (favruiteBean.getData()!=null){
            if (favruiteBean.getData().getLink().equals(mWeb)) {
                Toast.makeText(mActivity, "收藏成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mActivity, favruiteBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DaoHangInfoActivity.this, LoadingActivity.class));
            }
        }
    }

    @Override
    public void showFavruiteWebDelete(FavruiteWebDeleteBean favruiteBean) {
        if (favruiteBean.getErrorCode() == 0) {
            Toast.makeText(mActivity, "删除收藏成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showFavruite(Favruite favruiteBean) {
//        boolean loading = getSharedPreferences("true",0).getBoolean("loading", false);
        Favruite.DataBean data = favruiteBean.getData();
        if (data != null) {
            List<Favruite.DataBean.DatasBean> datas = data.getDatas();
            for (int i = 0; i < datas.size(); i++) {
                if (datas.get(i).getLink().equals(mWeb)) {
                    mMenu1.findItem(R.id.iteminfo_like).setIcon(R.mipmap.ic_toolbar_like_p);
                } else {
                    mMenu1.findItem(R.id.iteminfo_like).setIcon(R.mipmap.ic_toolbar_like_n);
                }
            }
        } else {
            mMenu1.findItem(R.id.iteminfo_like).setIcon(R.mipmap.ic_toolbar_like_n);
        }
    }

    @Override
    public void showError(String error) {

    }
}
