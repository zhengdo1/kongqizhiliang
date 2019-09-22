package com.gz.xhb.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gz.xhb.MVP.Model.Entity.User;
import com.gz.xhb.MVP.Presenter.UserLoginPresenter;
import com.gz.xhb.MVP.View.LoginView;
import com.gz.xhb.R;
import com.gz.xhb.util.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends XHBBaseActivity implements LoginView {

    @BindView(R.id.et_login_id)
    EditText et_user;
    @BindView(R.id.et_login_password)
    EditText et_password;
    @BindView(R.id.bt_login_login)
    Button button;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    //    @Inject
    UserLoginPresenter userLoginPresenter = new UserLoginPresenter(this);
    @BindView(R.id.yhmm)
    CheckBox yhmm;
    @BindView(R.id.tv_login_icon)
    TextView tvLoginIcon;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_login1;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/hwkt.ttf");
        tvLoginIcon.setTypeface(typeface);
        progressBar.setVisibility(View.GONE);
        userLoginPresenter.setCheckRember(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginPresenter.login();
            }
        });
    }

    @Override
    public String getUser() {
        return et_user.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return et_password.getText().toString().trim();
    }

    @Override
    public void clearUserInfo() {
        et_user.setText("");
        et_password.setText("");
    }

    @Override
    protected void setStatusColor() {
        fullScreen(this);
    }

    @Override
    protected void setSystemInvadeBlack() {
    }

    @Override
    public void toMainActivity(User user) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
        finish();
    }

    @Override
    public void setChecked() {
        yhmm.setChecked(true);
    }

    @Override
    public void setUnChecked() {
        yhmm.setChecked(false);
    }

    @Override
    public void showRememberedUser() {
        et_user.setText(SharedPreferencesUtils.getParam(this, "name", "").toString());
        et_password.setText(SharedPreferencesUtils.getParam(this, "password", "").toString());
    }

    @Override
    public boolean rememberIsChecked() {
        return yhmm.isChecked();
    }

    @Override
    public void showError() {
        Toast.makeText(this, "登录失败！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess() {
        Toast.makeText(this, "登录成功！", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
