package com.jy.theplayandroid.playandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;




import com.google.gson.Gson;
import com.jy.theplayandroid.playandroid.base.baseactivity.BaseActivity;
import com.jy.theplayandroid.playandroid.bean.LoadingBean;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;

import com.jy.theplayandroid.playandroid.presenter.LoadingPresenter;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;
import okhttp3.Request;

public class LoadingActivity extends BaseActivity<TalkClassify.LoadingView, LoadingPresenter<TalkClassify.LoadingView>> implements TalkClassify.LoadingView {

    @BindView(R.id.login_toolbar)
    Toolbar loginToolbar;
    @BindView(R.id.login_tv)
    TextView loginTv;
    @BindView(R.id.login_account_edit)
    EditText loginAccountEdit;
    @BindView(R.id.login_account_group)
    LinearLayout loginAccountGroup;
    @BindView(R.id.login_divider)
    View loginDivider;
    @BindView(R.id.login_password_edit)
    EditText loginPasswordEdit;
    @BindView(R.id.login_password_group)
    LinearLayout loginPasswordGroup;
    @BindView(R.id.register_divider)
    View registerDivider;
    @BindView(R.id.login_btn)
    Button loginBtn;
    @BindView(R.id.login_or_tv)
    TextView loginOrTv;
    @BindView(R.id.login_register_btn)
    Button loginRegisterBtn;
    @BindView(R.id.login_group)
    RelativeLayout loginGroup;
    public static SharedPreferences mSharedPreferences;
    public static SharedPreferences.Editor mEdit;
    private String mEmail;
    private MenuItem mItem;

    @Override
    protected int creatLoyoutId() {
        return R.layout.activity_loading;
    }

    @Override
    protected void initData() {
        loginToolbar.setTitle("");
        setSupportActionBar(loginToolbar);
        loginToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected LoadingPresenter<TalkClassify.LoadingView> creatPresenter() {
        return new LoadingPresenter<>();
    }

    @Override
    public void showLoading(LoadingBean loadingBean) {
        if (loadingBean.getData().getUsername() != null) {
            if (loadingBean.getData().getUsername().toString().contains(loginAccountEdit.getText().toString())) {
                Toast.makeText(mActivity, "登陆成功", Toast.LENGTH_SHORT).show();
                mSharedPreferences = getSharedPreferences("loging", 0);
                mEdit = mSharedPreferences.edit();
                mEdit.putBoolean("loging", true);
                mEdit.putString("name",loadingBean.getData().getEmail().toString());
                mEdit.commit();

                mItem = PlayStartActivity.mNavigationView.getMenu().getItem(4);
                PlayStartActivity.mHead.setText(loadingBean.getData().getUsername().toString());
                mItem.setVisible(true);
                finish();
            } else {
                Toast.makeText(mActivity, "登陆失败", Toast.LENGTH_SHORT).show();
                mItem.setVisible(false);
            }
        } else {
            Toast.makeText(mActivity, loadingBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showError(String error) {

    }

    @OnClick({R.id.login_btn, R.id.login_register_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                mEmail = loginAccountEdit.getText().toString();
                String pass1 = loginPasswordEdit.getText().toString();

                if (!TextUtils.isEmpty(mEmail)) {
                    if (isEmail(mEmail) || mEmail.matches("1[3|4|5|7|8][0-9]{9}")) {
                        if (!TextUtils.isEmpty(pass1)) {
                            HashMap<String, Object> map = new HashMap<>();
                            map.put("username",loginAccountEdit.getText().toString());
                            map.put("password",loginPasswordEdit.getText().toString());
                            mPresenter.getLoading(map);
                        } else {
                            Toast.makeText(LoadingActivity.this, "密码不能为空", Toast.LENGTH_LONG);
                        }
                    } else {
                        Toast.makeText(LoadingActivity.this, "账号不为邮箱,手机号或格式不正确", Toast.LENGTH_LONG);
                    }
                } else {
                    Toast.makeText(LoadingActivity.this, "账号不为邮箱,手机号或格式不正确", Toast.LENGTH_LONG);
                }
                break;
            case R.id.login_register_btn:
                startActivity(new Intent(LoadingActivity.this, RegisterActivity.class));
                break;
        }
    }

    public boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)"
                + "|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
