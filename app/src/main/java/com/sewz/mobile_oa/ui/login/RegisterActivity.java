package com.sewz.mobile_oa.ui.login;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.shenqing.resp.ShenQingResp;
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
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.phoneNumTxt)
    EditText phoneNumTxt;

    @BindView(R.id.pwdEditTxt)
    EditText pwdEditTxt;

    @BindView(R.id.rePwdEditTxt)
    EditText rePwdEditTxt;

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
        setContentView(R.layout.activity_register);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ImmersionBar mImmersionBar = ImmersionBar.with(this)
                    .statusBarColor(android.R.color.transparent);
            mImmersionBar.init();   //所有子类都将继承这些相同的属性
        }
        ButterKnife.bind(this);
        mContext = this;
        initAnim();
    }

    private void initAnim() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation = AnimationUtils.loadAnimation(RegisterActivity.this, R.anim.translate_anim);
                de_img_backgroud.startAnimation(animation);
            }
        }, 200);
    }


    @OnClick(R.id.registerBtn)
    public void onLogin() {
        finish();
    }

    @OnClick(R.id.loginBtn)
    public void onRegister() {
        String name = phoneNumTxt.getText().toString();
        String pwd = pwdEditTxt.getText().toString();
        String rePwd = rePwdEditTxt.getText().toString();
        if (isChecked(name, pwd, rePwd)) {
            doRequest(name, pwd);
        }
    }

    private void doRequest(String name, String pwd) {

        try {
            LoadDialog.show(mContext);
            Map<String, String> reqParams = new HashMap<>();
            String p = DesUtils.stringToMD5(pwd);
            reqParams.put("UserName", name);
            reqParams.put("UserPwd", p);

            Mobile_Api.getInstance().postData(UrlUtils.REGISTER, reqParams, new Callback() {
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
                        ShenQingResp resp = gson.fromJson(jsonStr, ShenQingResp.class);
                        LoadDialog.dismiss(mContext);
                        if (resp.getCode().equals("200")) {
                            toHome(resp.getMsg());
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

    private void toHome(String msg) {
        showShortToast(msg);
        finish();
    }

    private boolean isChecked(String name, String pwd, String rePwd) {
        boolean b;
        if (TextUtils.isEmpty(name)) {
            showShortToast("请输入用户名");
            b = false;
        } else if (TextUtils.isEmpty(pwd)) {
            showShortToast("请输入密码");
            b = false;
        } else if (!pwd.equals(rePwd)) {
            showShortToast("密码不一致");
            b = false;
        } else {
            b = true;
        }
        return b;
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
