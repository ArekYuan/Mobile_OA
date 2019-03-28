package com.sewz.mobile_oa.ui.splash;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.sewz.mobile_oa.Global.SharePref;
import com.sewz.mobile_oa.OA_Application;
import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.BaseActivity;
import com.sewz.mobile_oa.ui.home.HomeActivity;
import com.sewz.mobile_oa.ui.login.LoginActivity;
import com.sewz.mobile_oa.utils.NetUtil;
import com.sewz.mobile_oa.utils.YLog;
import com.sewz.mobile_oa.utils.syspermission.SysPermissionManager;
import com.sewz.mobile_oa.utils.syspermission.SysPermissionResultCallback;


public class SplashActivity extends BaseActivity {

    @Override
    protected int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showToolBar(View.GONE);
        showVisi(View.VISIBLE, View.GONE);
        initEvents();
    }

    /**
     * 初始化权限检查
     */
    private void initEvents() {
        if (!NetUtil.isNetworkConnected(OA_Application.mContext)) {
            showShortToast("请检查网络设置");
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {//检查当前手机版本的SDK
            checkStart();//走流程(登录，直接进入首页)
        } else {
            if (new SharePref(mContext).getBooleanValue("firstCheck", true)) {
                checkPermission();
            } else {
                checkStart();
            }
        }
    }

    /**
     * 检查权限
     */
    private void checkPermission() {
        SysPermissionManager.getInstance()
                .requestAllManifestPermissionsIfNecessary(this, new SysPermissionResultCallback() {
                    @Override
                    public void onGranted() {
                        YLog.d("granted");
                        checkStart();
                    }

                    @Override
                    public void onDenied(String permission) {
                        YLog.d("denied:" + permission);
                    }
                });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (new SharePref(mContext).getBooleanValue("firstCheck", true)) {
            String rePermissions = "";
            for (int i = 0; i < grantResults.length; i++) {
                if (grantResults[i] == -1) {
                    rePermissions += permissions[i] + ",";
                }
            }
            if (rePermissions.equals("")) {
                checkStart();
            } else {
                permissions = rePermissions.split(",");
                ActivityCompat.requestPermissions(SplashActivity.this, permissions, 1);
            }
            new SharePref(mContext).setBooleanValue("firstCheck", false);
        } else {
            checkStart();
        }
    }

    private void checkStart() {

        boolean isFirstLogin = new SharePref(mContext).getBooleanValue("isFirst_login", true);
        if (isFirstLogin) {
            jumpToLogin();
        } else {
            jumpToHome();
        }
    }

    Intent intent;

    private void jumpToLogin() {
        intent = new Intent(mContext, LoginActivity.class);
        mContext.startActivity(intent);
        finish();
    }

    private void jumpToHome() {
        intent = new Intent(mContext, HomeActivity.class);
        mContext.startActivity(intent);
        finish();
    }

}

