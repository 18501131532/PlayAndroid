package com.jy.theplayandroid.playandroid;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.theplayandroid.playandroid.about.AboutFragment;
import com.jy.theplayandroid.playandroid.base.DialogFragment;
import com.jy.theplayandroid.playandroid.base.baseactivity.SimpleActivity;
import com.jy.theplayandroid.playandroid.favroite.FavroiteFragment;
import com.jy.theplayandroid.playandroid.logout.LogoutFragment;
import com.jy.theplayandroid.playandroid.playandroid.PlayFragment;
import com.jy.theplayandroid.playandroid.settings.SettingsFragment;
import com.jy.theplayandroid.playandroid.util.GetWindowManagerUtils;
import com.jy.theplayandroid.playandroid.util.StatusBarUtil;

import butterknife.BindView;

public class PlayStartActivity extends SimpleActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.tv_toolbar)
    TextView tvToolbar;
    private PlayFragment mPlayFragment;
    public static AppCompatDelegate mDelegate;
    @Override
    protected void initData() {
        GetWindowManagerUtils.changeStatusBarTextColor(PlayStartActivity.this,true,R.color.colorPrimaryOverlay);

        mDelegate = getDelegate();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        FrameLayout framelayout = (FrameLayout) findViewById(R.id.fragment);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mPlayFragment = new PlayFragment(tvToolbar);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, mPlayFragment).commit();


        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_usage:

                        break;
                    case R.id.action_search:
                        new DialogFragment().show(getFragmentManager(),"123");
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        PlayFragment playFragment = new PlayFragment(tvToolbar);
        FavroiteFragment favroiteFragment = new FavroiteFragment();
        SettingsFragment settingsFragment = new SettingsFragment();
        AboutFragment aboutFragment = new AboutFragment();
        LogoutFragment logoutFragment = new LogoutFragment();
        int id = item.getItemId();
        if (id == R.id.nav_item_wan_android) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, playFragment).commit();
        } else if (id == R.id.nav_item_my_collect) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, favroiteFragment).commit();
        } else if (id == R.id.nav_item_setting) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, settingsFragment).commit();
            tvToolbar.setText(item.getTitle());
        } else if (id == R.id.nav_item_about_us) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, aboutFragment).commit();
        } else if (id == R.id.nav_item_logout) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, logoutFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
