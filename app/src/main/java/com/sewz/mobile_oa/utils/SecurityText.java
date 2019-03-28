package com.sewz.mobile_oa.utils;

import android.os.Handler;
import android.widget.TextView;


public class SecurityText extends Thread {
    /**
     * 要传入的控件
     */
    private TextView textView;

    /**
     * 延时时间
     */
    public int lagTime = 3;


    /**
     *
     */
    Handler handler = new Handler();
//    private RelativeLayout layout;

    public SecurityText(TextView textView, int lagTime) {
//        this.layout = layout;
        this.textView = textView;
        this.lagTime = lagTime;
    }

    @Override
    public void run() {
        try {
            while (lagTime > 0) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
//                        textView.setEnabled(false);
                        String smsTimeString = "重新获取( " + lagTime + " )";
                        textView.setText(smsTimeString);
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
                textView.setText("重新获取");
            }
        });
    }

    private boolean isOk = true;

    public void isOk(boolean isOk) {
        this.isOk = isOk;
    }
}
