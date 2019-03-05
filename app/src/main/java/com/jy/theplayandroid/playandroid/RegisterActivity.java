package com.jy.theplayandroid.playandroid;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.theplayandroid.playandroid.base.baseactivity.BaseActivity;
import com.jy.theplayandroid.playandroid.bean.RegisterBean;
import com.jy.theplayandroid.playandroid.concat.TalkClassify;
import com.jy.theplayandroid.playandroid.presenter.RegisterPresenter;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.FormBody;

public class RegisterActivity extends BaseActivity<TalkClassify.RegisterView, RegisterPresenter<TalkClassify.RegisterView>> implements TalkClassify.RegisterView {

    @BindView(R.id.common_toolbar_title_tv)
    TextView commonToolbarTitleTv;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.register_password_edit)
    EditText registerPasswordEdit;
    @BindView(R.id.register_account_edit)
    EditText registerAccountEdit;
    @BindView(R.id.register_confirm_password_edit)
    EditText registerConfirmPasswordEdit;
    @BindView(R.id.register_btn)
    Button registerBtn;

    @Override
    protected int creatLoyoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initData() {
        commonToolbarTitleTv.setText("注册");
        commonToolbar.setTitle("");
        setSupportActionBar(commonToolbar);

        commonToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected RegisterPresenter<TalkClassify.RegisterView> creatPresenter() {
        return new RegisterPresenter<>();
    }

    @Override
    public void showRegister(RegisterBean registerBean) {
        if (registerBean.getData() != null) {
            if (registerBean.getErrorCode() == 0) {
                Toast.makeText(mActivity, "注册成功", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(mActivity, registerBean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showError(String error) {

    }

    @OnClick(R.id.register_btn)
    public void onViewClicked() {
        String email = registerAccountEdit.getText().toString();
        String pass1 = registerPasswordEdit.getText().toString();
        String pass2 = registerConfirmPasswordEdit.getText().toString();

        if (!TextUtils.isEmpty(email)) {
            if (isEmail(email) || email.matches("1[3|4|5|7|8][0-9]{9}")) {
                if (!TextUtils.isEmpty(pass1) && !TextUtils.isEmpty(pass1)) {
                    if (pass1.equals(pass2)) {
                        FormBody formBody = new FormBody.Builder()
                                .add("username", email)
                                .add("password", pass1)
                                .add("repassword", pass2)
                                .build();
                        mPresenter.getRegister(formBody);
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "密码不能为空或重复密码不正确", Toast.LENGTH_LONG);
                }
            } else {
                Toast.makeText(RegisterActivity.this, "账号不为邮箱,手机号或格式不正确", Toast.LENGTH_LONG);
            }
        } else {
            Toast.makeText(RegisterActivity.this, "账号不为邮箱,手机号或格式不正确", Toast.LENGTH_LONG);
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
