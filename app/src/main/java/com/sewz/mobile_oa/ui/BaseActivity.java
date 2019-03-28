package com.sewz.mobile_oa.ui;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gyf.barlibrary.ImmersionBar;
import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * end
     */
    protected Context mContext = null;

    /**
     * 显示部分
     */
    protected LinearLayout layoutcontainer;

    /**
     * 标题
     */
    protected TextView mTitleTxt;

    /**
     * 右边容器
     */
    protected ImageView mRightIV;

    /**
     * 标题栏
     */
    protected Toolbar mToolbar;

    /**
     * 左边容器
     */
    protected ImageView mLeftIV;


    /**
     * text right onclick
     */
    protected TextView mRightTv;

    /**
     * 所有继承此基类的activity都会实现该方法
     *
     * @return 布局id
     */
    protected abstract int getContentViewId();

    protected LinearLayout titleTxtLayout;

    protected TextView searchTxt;

    private View titleLine;
    private ImmersionBar mImmersionBar;

    protected LinearLayout showWinMoneyLayout;
    protected TextView winMoneyTxt;
    protected ImageView closeIv;

    protected Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mContext = this;
        gson = new Gson();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            mImmersionBar = ImmersionBar.with(this)
                    .statusBarColor(R.color.general_blue);
            mImmersionBar.init();   //所有子类都将继承这些相同的属性
        }
        initToolBar();
        initShowWatch();
        initContentView();
    }


    private void initShowWatch() {
        showWinMoneyLayout = findViewById(R.id.showWinMoneyLayout);
        winMoneyTxt = findViewById(R.id.winMoneyTxt);
        closeIv = findViewById(R.id.closeIv);
        closeIv.setOnClickListener(onClickListener);
    }

    private void initContentView() {
        // 初始化容器部分
        layoutcontainer = (LinearLayout) findViewById(R.id.layout_container);
        if (getContentViewId() != 0) {
            layoutcontainer.addView(
                    getLayoutInflater().inflate(getContentViewId(), null),
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
            );
        } else {
            layoutcontainer.setVisibility(View.GONE);
        }
    }

    /**
     * @param titleVisi  标题
     * @param searchVisi 搜索栏目
     */
    protected void showVisi(int titleVisi, int searchVisi) {
        if (titleTxtLayout != null) {
            titleTxtLayout.setVisibility(titleVisi);
        }
        if (searchTxt != null) {
            searchTxt.setVisibility(searchVisi);
        }
    }

    private void initToolBar() {
        titleLine = findViewById(R.id.view_line);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mTitleTxt = (TextView) findViewById(R.id.titleTxt);
        mRightIV = (ImageView) findViewById(R.id.rightIv);
        mLeftIV = (ImageView) findViewById(R.id.titleLeftIV);
        mRightTv = (TextView) findViewById(R.id.rightTv);
        titleTxtLayout = findViewById(R.id.titleTxtLayout);
        searchTxt = findViewById(R.id.searchTxt);

        //将Toolbar显示到界面
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().
                    setDisplayShowTitleEnabled(false);
        }

        mRightIV.setOnClickListener(onClickListener);
        mLeftIV.setOnClickListener(onClickListener);
        mRightTv.setOnClickListener(onClickListener);
        mTitleTxt.setOnClickListener(onClickListener);
        searchTxt.setOnClickListener(onClickListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            mImmersionBar.destroy();
        }
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rightIv:
                    onRightClick();
                    break;
                case R.id.titleLeftIV:
                    onLeftClick();
                    break;
                case R.id.rightTv:
                    onRightTxtClick();
                    break;
                case R.id.titleTxt:
                    onTitleTxtClick();
                    break;

                case R.id.closeIv:
                    onCloseClick();
                    break;

                case R.id.searchTxt:
                    onSearch();
                    break;
            }
        }
    };

    /**
     * 搜索
     */
    protected void onSearch() {

    }

    /**
     * 关闭提示
     */
    protected void onCloseClick() {

    }

    /**
     * 加载位置
     */
    protected void onTitleTxtClick() {

    }

    /**
     * 点击右边按钮
     */
    protected void onRightTxtClick() {

    }

    /**
     * 设置 标题
     *
     * @param title
     */
    protected void setTitleTxt(String title) {
        if (mTitleTxt != null) {
            mTitleTxt.setText(title);
        }
    }

    /**
     * 设置 标题 资源
     *
     * @param resId
     */
    protected void setTitleTxt(int resId) {
        if (mTitleTxt != null) {
            mTitleTxt.setText(resId);
        }
    }

    /**
     * 设置标题颜色
     *
     * @param resId
     */
    protected void setTitleTxtColor(int resId) {
        if (mTitleTxt != null) {
            mTitleTxt.setTextColor(mContext.getResources().getColor(resId));
        }
    }

    /**
     * 设置右边按钮的文字 string
     *
     * @param str
     */
    protected void setRightTxt(String str) {
        if (mRightIV != null) {
            mRightIV.setVisibility(View.GONE);
        }
        if (mRightTv != null) {
            mRightTv.setText(str);
        }
    }


    /**
     * 设置右边文字的颜色
     *
     * @param resId
     */
    protected void setRightTxtColor(int resId) {
        if (mRightIV != null) {
            mRightIV.setVisibility(View.GONE);
        }
        if (mRightTv != null) {
            mRightTv.setTextColor(mContext.getResources().getColor(resId));
        }
    }

    protected void setRightTxtEnabled(boolean isEnabled) {
        if (mRightTv != null) {
            mRightTv.setEnabled(isEnabled);
        }
    }

    /**
     * 设置右边按钮的文字 resId
     *
     * @param resId
     */
    protected void setRightTxt(int resId) {
        if (mRightIV != null) {
            mRightIV.setVisibility(View.GONE);
        }
        if (mRightTv != null) {
            mRightTv.setText(resId);
        }
    }


    /**
     * 左边按钮点击事件
     */
    protected void onLeftClick() {
    }

    /**
     * 右部点击事件
     */
    protected void onRightClick() {
    }

    /**
     * 设置标题栏右边部分的背景图片
     *
     * @param resId
     */
    protected void setRightImgBg(int resId) {
        if (mRightTv != null) {
            mRightTv.setVisibility(View.GONE);
        }
        if (mRightIV != null) {
            mRightIV.setImageResource(resId);
        }
    }

    /**
     * right img is visiable
     *
     * @param visi
     */
    protected void setRightIvVisi(int visi) {
        if (mRightIV != null) {
            mRightIV.setVisibility(visi);
        }
    }

    /**
     * 设置标题栏右边部分的背景图片
     *
     * @param resId
     */
    protected void setLeftImgBg(int resId) {
        if (mLeftIV != null) {
            mLeftIV.setImageResource(resId);
        }
    }

    /**
     * 设置头部标签的背景颜色
     *
     * @param resId
     */
    protected void setHeadBackGround(int resId) {
        if (mToolbar != null) {
            mToolbar.setBackgroundColor(mContext.getResources().getColor(resId));
        }
    }


    /**
     * 是否展示 返回按钮
     *
     * @param b
     */
    protected void showBackVisi(boolean b) {
        if (b) {
            mToolbar.setNavigationIcon(R.mipmap.back_white);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    /**
     * 是否展示toolbar
     *
     * @param visi
     */
    protected void showToolBar(int visi) {
        if (mToolbar != null) {
            mToolbar.setVisibility(visi);
        }
    }

    /**
     * 是否可见 注意弹框
     *
     * @param visi
     */
    protected void showWatch(int visi) {
        if (showWinMoneyLayout != null) {
            showWinMoneyLayout.setVisibility(visi);
        }
    }

    /**
     * 根据版本设置沉侵式状态栏
     *
     * @param b
     */
    private void setTranslucentStatus(boolean b) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (b) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    /**
     * 批量请求权限
     *
     * @param permission 权限列表
     */
    protected void requestPermission(String[] permission) {
        String[] p = null;
        if (ContextCompat.checkSelfPermission(BaseActivity.this,
                permission[0]) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(BaseActivity.this, permission, 0);
        }
    }

    /**
     * 短暂显示Toast提示
     *
     * @param resId 资源id
     */
    protected void showShortToast(int resId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(getString(resId));
            }
        });
    }

    /**
     * 短暂显示Toast提示
     *
     * @param text 文本
     */
    protected void showShortToast(final String text) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.showToast(text);
            }
        });
    }


    /**
     * @ @param resId 图片资源id
     */
    protected void setTitleTxtDrawable(int resId) {
        if (mTitleTxt != null) {
            Drawable drawable = getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            mTitleTxt.setCompoundDrawables(null, null, drawable, null);
        }
    }

    protected void setLineUnVisible() {
        titleLine.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.right_in, R.anim.scale_gone);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.right_in, R.anim.scale_gone);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
//        Constant.noticeNum = 0;
        super.onResume();
    }

    protected String dateToStamp(long s) {
        Date date = new Date(s);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dateTime = simpleDateFormat.format(date);
        return dateTime;
    }

    protected String toDate(long s) {
        Date date = new Date(s);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateTime = simpleDateFormat.format(date);
        return dateTime;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus() && event.getAction() == MotionEvent.ACTION_UP) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }


    /**
     * @param bodyHTML
     * @return
     */
    protected String getHtmlData(String bodyHTML) {
        String head = "<head>" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, user-scalable=no\"> " +
                "<style>p{margin: 6px 12px;padding:0;} img{max-width: 100%; width:100%; height:auto;} body{margin:0;padding:0} </style>" +
                "</head>";
        return "<html>" + head + "<body>" + bodyHTML + "</body></html>";
    }
}
