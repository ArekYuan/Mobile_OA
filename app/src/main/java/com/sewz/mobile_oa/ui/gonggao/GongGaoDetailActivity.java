package com.sewz.mobile_oa.ui.gonggao;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.just.agentweb.AgentWebView;
import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.BaseActivity;
import com.sewz.mobile_oa.ui.gonggao.resp.GongGaoResp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GongGaoDetailActivity extends BaseActivity {

    @BindView(R.id.TitleStrTxt)
    TextView TitleStrTxt;

    @BindView(R.id.TimeStrTxt)
    TextView TimeStrTxt;

    @BindView(R.id.TypeStrTxt)
    TextView TypeStrTxt;

    @BindView(R.id.UserNameTxt)
    TextView UserNameTxt;

    @BindView(R.id.webView)
    AgentWebView webview;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_gong_gao_detail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitleTxt("公告详情");
        setLeftImgBg(R.mipmap.back_white);
        initWebView();
        GongGaoResp.DataBean bean = (GongGaoResp.DataBean) getIntent().getSerializableExtra("gongGaoData");
        if (bean != null) {
            initData(bean);
        }


    }

    private void initWebView() {
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });
        webview.setWebChromeClient(new WebChromeClient());
        webview.getSettings().setJavaScriptEnabled(true);
        webview.requestFocusFromTouch();
        webview.requestFocus();
        webview.getSettings().setDomStorageEnabled(true);
        webview.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        webview.getSettings().setAllowFileAccess(true);
        webview.getSettings().setAppCacheEnabled(true);
        webview.getSettings().setGeolocationEnabled(true);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webview.getSettings().setSupportZoom(true);
        webview.getSettings().setBuiltInZoomControls(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
    }


    private void initData(GongGaoResp.DataBean bean) {
        TitleStrTxt.setText(bean.getTitleStr());
        TimeStrTxt.setText(bean.getTimeStr());
        TypeStrTxt.setText(bean.getUserBuMen());
        UserNameTxt.setText(bean.getUserName());
        webview.loadData(getHtmlData(bean.getContentStr()), "text/html; charset=utf-8", "utf-8");
    }

    @Override
    protected void onLeftClick() {
        super.onLeftClick();
        finish();
    }
}
