package com.sewz.mobile_oa.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;


/**
 * Created by ygy on 2018\3\19 0019.
 */

public abstract class BaseFragment extends Fragment {

    /**
     * 布局view
     */
    protected View rootView;

    /**
     * 上下文
     */
    protected Context mContext;

    protected Gson gson;

    /**
     * 加载对话框
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        gson = new Gson();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(getContentViewId(), container, false);
    }




    /**
     * 获取 布局内容id
     *
     * @return int
     */
    protected abstract int getContentViewId();

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        onViewInitialized(rootView, savedInstanceState);
    }

    /**
     * <初始化一些数据>
     * <功能详细描述>
     *
     * @param savedInstanceState 返回类型:void
     */
    public abstract void onViewInitialized(View view, Bundle savedInstanceState);

    @SuppressLint("WrongConstant")
    protected void showShortToast(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

}
