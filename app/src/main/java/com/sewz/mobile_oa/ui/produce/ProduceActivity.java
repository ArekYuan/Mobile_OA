package com.sewz.mobile_oa.ui.produce;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.just.agentweb.AgentWebView;
import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.BaseActivity;
import com.sewz.mobile_oa.utils.dialog.LoadDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 公司简介
 */
public class ProduceActivity extends BaseActivity {

    @BindView(R.id.webView)
    AgentWebView webview;


    @Override
    protected int getContentViewId() {
        return R.layout.activity_produce;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitleTxt("公司简介");
        setLeftImgBg(R.mipmap.back_white);
        initEvent();
    }

    private void initEvent() {
        LoadDialog.show(mContext);
        webview.loadUrl("http://pd.suneinfo.com/aspx/moa/GongGao/XueXiaoJJ.aspx?Type=%B9%AB%CB%BE%BC%F2%BD%E9");
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                LoadDialog.dismiss(mContext);
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

    @Override
    protected void onLeftClick() {
        super.onLeftClick();
        finish();
    }
}
