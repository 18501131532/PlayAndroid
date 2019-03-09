package com.jy.theplayandroid.playandroid.playandroid.daohang.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.jy.theplayandroid.playandroid.LoadingActivity;
import com.jy.theplayandroid.playandroid.PlayStartActivity;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.baseactivity.BaseActivity;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.bean.DateBase;
import com.jy.theplayandroid.playandroid.bean.FavroiteAddBean;
import com.jy.theplayandroid.playandroid.bean.Favruite;
import com.jy.theplayandroid.playandroid.bean.HttpResult;
import com.jy.theplayandroid.playandroid.playandroid.daohang.manager.LikeDataBaseMannger;
import com.jy.theplayandroid.playandroid.playandroid.daohang.presenter.FavruiteWebPresenter;
import com.jy.theplayandroid.playandroid.utils.ShareUtil;
import com.jy.theplayandroid.playandroid.utils.StatusBarUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;

public class DaoHangInfoActivity extends BaseActivity<TalkClassify.FavruiteWebView, FavruiteWebPresenter<TalkClassify.FavruiteWebView>> implements TalkClassify.FavruiteWebView {

    @BindView(R.id.toolbar_daohanginfo)
    Toolbar toolbarDaohanginfo;
    @BindView(R.id.wv_daohanginfo)
    WebView wvDaohanginfo;
    private String mWeb;
    private String mAuther;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEdit;
    private int mId;
    private Menu mMenu1;
    boolean isClick = true;
    private String mTitle;
    private int mOrid;

    @Override
    protected int creatLoyoutId() {
        return R.layout.activity_dao_hang_info;
    }

    @Override
    protected void initData() {
        mSharedPreferences = getSharedPreferences("loging", 0);
        mEdit = mSharedPreferences.edit();

        mTitle = getIntent().getStringExtra("title");
        mId = getIntent().getIntExtra("id", 0);
        Log.e("ppppppp", "initData: " + mId);
        mWeb = getIntent().getStringExtra("link");
        mAuther = getIntent().getStringExtra("auther");
        Log.e("web", "initData: " + mWeb);

        initToolbar();

        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);

        initWebView();
    }

    private void initToolbar() {
        toolbarDaohanginfo.setTitle(mTitle);
        setSupportActionBar(toolbarDaohanginfo);
        toolbarDaohanginfo.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initWebView() {
        //百度
        WebSettings settings = wvDaohanginfo.getSettings();
        settings.setJavaScriptEnabled(true);
        wvDaohanginfo.loadUrl(mWeb);
        wvDaohanginfo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
    }

    private void initDateBase() {
        MenuItem like = (MenuItem) findViewById(R.id.iteminfo_like);
        List<DateBase> dateBases = LikeDataBaseMannger.getInstrance().selectAll();
        if (dateBases.size() > 0) {
            for (int i = 0; i < dateBases.size(); i++) {
                if (dateBases.get(i).getMId().contains(mId + "")) {
                    like.setIcon(R.mipmap.ic_toolbar_like_p);
                } else {
                    like.setIcon(R.mipmap.ic_toolbar_like_n);
                }
            }
        } else {
            like.setIcon(R.mipmap.ic_toolbar_like_n);
        }
    }

    private void initWeb() {
        //title，author，link
        HashMap<String, Object> map = new HashMap<>();
        map.put("title", mTitle);
        map.put("author", mAuther);
        map.put("link", mWeb);
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem menuItem = menu.findItem(R.id.iteminfo_like).setChecked(true);
        mTitle = getIntent().getStringExtra("title");
        boolean aBoolean = mSharedPreferences.getBoolean(mTitle, false);
        if (mSharedPreferences.getBoolean("loging", false)) {
            if (aBoolean) {
                menuItem.setIcon(R.mipmap.ic_toolbar_like_p);
                isClick = false;
            } else {
                isClick = true;
                menuItem.setIcon(R.mipmap.ic_toolbar_like_n);
            }
        }
//        else {
//            startActivity(new Intent(DaoHangInfoActivity.this, LoadingActivity.class));
//        }
//        List<DateBase> dateBases = LikeDataBaseMannger.getInstrance().selectAll();
//        Log.e("shoucangksnfd", "onPrepareOptionsMenu: "+dateBases.size());
//        mId = getIntent().getStringExtra("id");
//        if (dateBases.size() > 0) {
//            for (int i = 0; i < dateBases.size(); i++) {
//                if (dateBases.get(i).getMId().contains(mId)) {
//                    Log.e("shoucangksnfd", "onPrepareOptionsMenu: "+mId);
//                    menuItem.setIcon(R.mipmap.ic_toolbar_like_p).setChecked(true);
//                } else {
//                    menuItem.setIcon(R.mipmap.ic_toolbar_like_n).setChecked(true);
//                }
//            }
//        } else {
//            menuItem.setIcon(R.mipmap.ic_toolbar_like_n);
//        }
        return true;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.info_meun, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iteminfo_like:
                boolean loading = mSharedPreferences.getBoolean("loging", false);
                //获取NavigationView上的组件
                MenuItem v = PlayStartActivity.mNavigationView.getMenu().getItem(4);
                if (loading) {
                    if (isClick) {
                        initWeb();
                        item.setIcon(R.mipmap.ic_toolbar_like_p);
                        mEdit.putBoolean(mTitle, true);
                        mEdit.commit();

//                        ArrayList<DateBase> dateBases = new ArrayList<>();
//                        dateBases.add(new DateBase(null, true, mId));
//                        LikeDataBaseMannger.getInstrance().insert(dateBases);
//                        Log.e("datebse_selete", "onOptionsItemSelected: " + LikeDataBaseMannger.getInstrance().selectAll().size());

                        isClick = false;
                    } else {
                        HashMap<String, Object> map = new HashMap<>();
//                        map.put("id", mId);
//                        map.put("originId", mOrid);
                        mPresenter.getFavruiteWebDelete(mId, mOrid);
                        item.setIcon(R.mipmap.ic_toolbar_like_n);
                        mEdit.putBoolean(mTitle, false);
                        mEdit.commit();

//                        List<DateBase> dateBases = LikeDataBaseMannger.getInstrance().selectAll();
//                        Log.e("datebse_selete2", "onOptionsItemSelected: " + dateBases.size());
//                        if (dateBases.size() > 0) {
//                            List<DateBase> dateBases1 = LikeDataBaseMannger.getInstrance().selectId(mId);
//                            Log.e("datebse_seleteid", "onOptionsItemSelected: " + dateBases1.size());
//                            if (dateBases1.size() > 0) {
//                                LikeDataBaseMannger.getInstrance().delete(dateBases1.get(0));
//                                Log.e("datebse_seletedelete", "onOptionsItemSelected: " + LikeDataBaseMannger.getInstrance().selectAll().size());
//                            }
//                        }
                        isClick = true;
                    }
                    v.setVisible(true);
                } else {
                    v.setVisible(false);
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

//    @Override
//    public Intent getIntent() {
//        finish();
//        return super.getIntent();
//    }

    @Override
    protected FavruiteWebPresenter<TalkClassify.FavruiteWebView> creatPresenter() {
        return new FavruiteWebPresenter<>();
    }

    @Override
    public void showFavruiteWeb(FavroiteAddBean favruiteBean) {
        if (favruiteBean != null) {
            Log.e("duanxqooo", "showFavruiteWeb: " + favruiteBean.getData().getOriginId());
            int originId = favruiteBean.getData().getOriginId();
            mOrid = originId;
            if (favruiteBean.getData() != null) {
                if (favruiteBean.getData().getLink().equals(mWeb)) {
                    Toast.makeText(mActivity, "收藏成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mActivity, favruiteBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DaoHangInfoActivity.this, LoadingActivity.class));
                }
            }
        }
    }

    @Override
    public void showFavruite(Favruite favruiteBean) {

    }

    @Override
    public void showFavruiteWebDelete(HttpResult favruiteBean) {
        if (favruiteBean.getErrorCode() == 0) {
            Toast.makeText(mActivity, "删除收藏成功", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showError(String error) {

    }
}
