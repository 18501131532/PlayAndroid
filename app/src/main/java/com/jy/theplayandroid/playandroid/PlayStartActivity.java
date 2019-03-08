package com.jy.theplayandroid.playandroid;

import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.theplayandroid.playandroid.Dialogfragment.Changyong;
import com.jy.theplayandroid.playandroid.Dialogfragment.Sousuo;
import com.jy.theplayandroid.playandroid.about.AboutFragment;
import com.jy.theplayandroid.playandroid.adapter.FragmentAdapter;
import com.jy.theplayandroid.playandroid.base.DialogFragment;
import com.jy.theplayandroid.playandroid.base.baseactivity.SimpleActivity;
import com.jy.theplayandroid.playandroid.favroite.FavroiteFragment;
import com.jy.theplayandroid.playandroid.http.HttpGreendao;
import com.jy.theplayandroid.playandroid.http.HttpManager;
import com.jy.theplayandroid.playandroid.global.MyApp;
import com.jy.theplayandroid.playandroid.logout.LogoutFragment;
import com.jy.theplayandroid.playandroid.playandroid.PlayFragment;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ImageList;
import com.jy.theplayandroid.playandroid.playandroid.daohang.DaohangFragment;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.GongzhonghaoFragment;
import com.jy.theplayandroid.playandroid.playandroid.main.MainFragment;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.XiangmuFragment;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.ZhishitixiFragment;
import com.jy.theplayandroid.playandroid.settings.SettingsFragment;
import com.jy.theplayandroid.playandroid.util.BottomNavigationViewHelper;
import com.jy.theplayandroid.playandroid.util.GetWindowManagerUtils;
import com.jy.theplayandroid.playandroid.util.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;

public class PlayStartActivity extends SimpleActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    private PlayFragment mPlayFragment;
    public static AppCompatDelegate mDelegate;

    private boolean isone=false;
    private boolean istwo=false;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEdit;
    public static NavigationView mNavigationView;
    private boolean mLoging;
    public static TextView mHead;
    public static FloatingActionButton fab;
    public static BottomNavigationView bottomNavigationView;
    private Toolbar mToolbar;

    @Override
    protected void initData() {
        mSharedPreferences = getSharedPreferences("loging", 0);
        mLoging = mSharedPreferences.getBoolean("loging", true);
        //侧滑 功能
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mHead = (TextView) mNavigationView.getHeaderView(0).findViewById(R.id.nav_header_login_tv);
        mToolbar = (Toolbar) findViewById(R.id.toolbar_play);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        FrameLayout framelayout = (FrameLayout) findViewById(R.id.fragment);
        mNavigationView.setNavigationItemSelectedListener(this);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        fab = findViewById(R.id.fab);
        mPlayFragment = new PlayFragment(tvToolbar,bottomNavigationView);

        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        //获取NavigationView上的组件
        MenuItem item = mNavigationView.getMenu().getItem(4);
        if (mLoging) {
            item.setVisible(true);
            String name = mSharedPreferences.getString("name", "sfsdfsdf");
            mHead.setText(name);
        } else {
            item.setVisible(false);
        }

        initToolBar();

        GetWindowManagerUtils.changeStatusBarTextColor(PlayStartActivity.this, true, R.color.colorPrimaryOverlay);
        mDelegate = getDelegate();

        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        HttpGreendao.getInstance().insert(new ImageList(null,isone,istwo));

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mPlayFragment).commit();
    }

    private void initToolBar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_usage:
                        Changyong changyong = new Changyong();
                        changyong.show(getFragmentManager(), "123");
                        break;
                    case R.id.action_search:
                        new Sousuo().show(getFragmentManager(), "123");
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected int creatLoyoutId() {
//        GetWindowManagerUtils.changeStatusBarTextColor(PlayStartActivity.this,true,R.color.colorPrimaryOverlay);
        return R.layout.activity_play_start;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.play_start, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        tvToolbar.setText("首页");
        PlayFragment playFragment = new PlayFragment(tvToolbar,bottomNavigationView);
        FavroiteFragment favroiteFragment = new FavroiteFragment();
        SettingsFragment settingsFragment = new SettingsFragment();
        LogoutFragment logoutFragment = new LogoutFragment();

        int id = item.getItemId();
        if (id == R.id.nav_item_wan_android) {
            bottomNavigationView.setVisibility(View.VISIBLE);
            fab.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, playFragment).commit();
        } else if (id == R.id.nav_item_my_collect) {
            bottomNavigationView.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, favroiteFragment).commit();
            tvToolbar.setText(item.getTitle());
        } else if (id == R.id.nav_item_setting) {
            bottomNavigationView.setVisibility(View.GONE);
            fab.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, settingsFragment).commit();
            tvToolbar.setText(item.getTitle());
        } else if (id == R.id.nav_item_about_us) {
            startActivity(new Intent(this, AboutUsActivity.class));
        } else if (id == R.id.nav_item_logout) {
            tvToolbar.setText(item.getTitle());
            bottomNavigationView.setVisibility(View.GONE);
            fab.setVisibility(View.GONE);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, logoutFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void initBack() {
        super.initBack();
        Log.e("wangyunpeng", "onDestroy: " + "shabi");
        mSharedPreferences = getSharedPreferences("loging", 0);
        mEdit = mSharedPreferences.edit();
        mEdit.putBoolean("loging", false);
        mEdit.commit();
    }
}
