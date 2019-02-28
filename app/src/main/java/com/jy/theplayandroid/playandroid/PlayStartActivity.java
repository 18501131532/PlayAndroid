package com.jy.theplayandroid.playandroid;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.base.baseactivity.SimpleActivity;
import com.jy.theplayandroid.playandroid.playandroid.PlayFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlayStartActivity extends SimpleActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;

    @SuppressLint({"ResourceAsColor", "NewApi"})
    @Override
    protected void initData() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //注意要清除 FLAG_TRANSLUCENT_STATUS flag
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryOverlay));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        FrameLayout framelayout = (FrameLayout) findViewById(R.id.fragment);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        PlayFragment playFragment = new PlayFragment(tvToolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, playFragment).commit();
    }

    @Override
    protected int creatLoyoutId() {
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.tab_knowledge_hierarchy) {
            // Handle the camera action
        } else if (id == R.id.tab_main_pager) {

        } else if (id == R.id.tab_navigation) {

        } else if (id == R.id.tab_project) {

        } else if (id == R.id.tab_wx_article) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
