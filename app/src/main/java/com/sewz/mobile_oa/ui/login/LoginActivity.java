package com.sewz.mobile_oa.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.sewz.mobile_oa.Global.Global;
import com.sewz.mobile_oa.Global.SharePref;
import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.home.HomeActivity;
import com.sewz.mobile_oa.ui.login.resp.LoginResp;
import com.sewz.mobile_oa.utils.DesUtils;
import com.sewz.mobile_oa.utils.ToastUtils;
import com.sewz.mobile_oa.utils.UrlUtils;
import com.sewz.mobile_oa.utils.YLog;
import com.sewz.mobile_oa.utils.dialog.LoadDialog;
import com.sewz.mobile_oa.utils.network.Mobile_Api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 登录页面
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.phoneNumTxt)
    EditText phoneNumTxt;

    @BindView(R.id.pwdEditTxt)
    EditText pwdEditTxt;

    @BindView(R.id.loginBtn)
    Button loginBtn;

    @BindView(R.id.registerBtn)
    Button registerBtn;

    @BindView(R.id.de_img_backgroud)
    ImageView de_img_backgroud;

    private Context mContext;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ImmersionBar mImmersionBar = ImmersionBar.with(this)
                    .statusBarColor(android.R.color.transparent);
            mImmersionBar.init();   //所有子类都将继承这些相同的属性
        }
        mContext = this;
        ButterKnife.bind(this);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

        initAnim();
    }

    private void initAnim() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.translate_anim);
                de_img_backgroud.startAnimation(animation);
            }
        }, 200);
    }


    private boolean isChecked(String name, String pwd) {
        boolean b;
        if (TextUtils.isEmpty(name)) {
            ToastUtils.showToast("请输入用户名");
            b = false;
        } else if (TextUtils.isEmpty(pwd)) {
            ToastUtils.showToast("请输入密码");
            b = false;
        } else {
            b = true;
        }
        return b;
    }

    @Override
    public void onClick(View view) {
        String name = phoneNumTxt.getText().toString();
        String pwd = pwdEditTxt.getText().toString();
        switch (view.getId()) {
            case R.id.loginBtn:
                if (isChecked(name, pwd)) {
                    doLogin(name, pwd);
                }
                break;
            case R.id.registerBtn:
                startActivity(new Intent(mContext, RegisterActivity.class));
                break;
        }
    }

    private void doLogin(String UserName, String UserPwd) {
        try {
            LoadDialog.show(mContext);
            Map<String, String> reqParams = new HashMap<>();
            String pwd = DesUtils.stringToMD5(UserPwd);
            reqParams.put("UserName", UserName);
            reqParams.put("UserPwd", pwd);

            Mobile_Api.getInstance().postData(UrlUtils.LOGIN_POST, reqParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    showShortToast("网络连接失败，请重试！");
                    LoadDialog.dismiss(mContext);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String jsonStr = response.body().string();

                        YLog.d("---login_resp-->" + jsonStr);
                        LoginResp resp = gson.fromJson(jsonStr, LoginResp.class);
                        LoadDialog.dismiss(mContext);
                        if (resp.getCode().equals("200")) {
                            toHome(resp);
                        } else {
                            showShortToast(resp.getMsg());
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LoadDialog.dismiss(mContext);
        }
    }

    private void toHome(LoginResp resp) {
        showShortToast("登录成功");
        Global.getInstance().saveUserName(resp.getData().getUserName());
        Global.getInstance().saveUserId(resp.getData().getID());
        Intent intent = new Intent(mContext, HomeActivity.class);
        startActivity(intent);
        new SharePref(mContext).setBooleanValue("isFirst_login", false);
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus() && event.getAction() == MotionEvent.ACTION_UP) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }


    private void showShortToast(String resId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(resId);
            }
        });
    }

}
