package com.sewz.mobile_oa.ui.home;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.KeyEvent;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;
import com.sewz.mobile_oa.Global.Global;
import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.gonggao.GongGaoActivity;
import com.sewz.mobile_oa.ui.produce.ProduceActivity;
import com.sewz.mobile_oa.ui.shenpi.ShenPiActivity;
import com.sewz.mobile_oa.ui.shenqing.CommApplyActivity;
import com.sewz.mobile_oa.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.nameTxt)
    TextView nameTxt;

    @BindView(R.id.comProduceView)
    CardView comProduceView;//公司简介

    @BindView(R.id.comGongGaoView)
    CardView comGongGaoView;// 公司公告

    @BindView(R.id.comApplyView)
    CardView comApplyView;//日常申请

    @BindView(R.id.comShenPiView)
    CardView comShenPiView;// 日常审批

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            ImmersionBar mImmersionBar = ImmersionBar.with(this)
                    .statusBarColor(android.R.color.transparent);
            mImmersionBar.init();   //所有子类都将继承这些相同的属性
        }

        nameTxt.setText(Global.getInstance().getUserName() + "，");
    }


    @OnClick(R.id.comProduceView)
    public void toProduce() {//公司简介
        startActivity(new Intent(this, ProduceActivity.class));
    }

    @OnClick(R.id.comGongGaoView)
    public void toGongGao() {//公告
        startActivity(new Intent(this, GongGaoActivity.class));
    }

    @OnClick(R.id.comApplyView)
    public void toApply() {// 日常申请
        startActivity(new Intent(this, CommApplyActivity.class));
    }

    @OnClick(R.id.comShenPiView)
    public void toShenPi() {// 审批页面
        startActivity(new Intent(this, ShenPiActivity.class));

    }

    private boolean isBack = false;
    private long downTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - downTime) > 2000) {
                showShortToast("再按一次退出程序");
                downTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);

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
