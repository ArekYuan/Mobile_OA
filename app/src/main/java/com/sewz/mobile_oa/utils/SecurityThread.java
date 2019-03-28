package com.sewz.mobile_oa.utils;

import android.os.Handler;
import android.widget.TextView;

/**
 * 文 件 名:  SecurityThread.java
 * 版    权:
 * 描    述:  <两次验证码获取时间间隔控制>
 * 版    本： <版本号>
 * 创 建 人:  袁光跃
 * 创建时间:  2015年11月11日
 */
public class SecurityThread extends Thread {
    /**
     * 要传入的控件
     */
    private TextView textView;

    /**
     * 延时时间
     */
    public int lagTime = 60;

    /**
     * 还没点击时的控件字体颜色
     */
    int enalbeColor;

    /**
     * 点击后的控件字体颜色
     */
    int disenalbeColor;

    /**
     *
     */
    Handler handler = new Handler();

    public SecurityThread(TextView textView) {
        this.textView = textView;
//        this.enalbeColor = normalColor;
//        this.disenalbeColor = disenalbeColor;
    }

    @Override
    public void run() {
        try {
            while (lagTime > 0) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setEnabled(false);
//                        String smsTimeString = "重新获取\n( " + lagTime + " )";
                        String smsTimeString = lagTime + "秒";
                        textView.setText(smsTimeString);
//                        textView.setTextColor(disenalbeColor);
                    }
                });
                Thread.sleep(1000);
                lagTime--;

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setEnabled(true);
                textView.setText("重新获取 ");
//                textView.setTextColor(enalbeColor);
            }
        });
    }
}
