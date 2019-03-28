package com.sewz.mobile_oa.ui.shenpi.resp;

/**
 * Created by Yuan on 2019/3/24.
 */

public class CommResp {
    private int resId;
    private String title;
    private String subTitle;
    private int commType;

    public CommResp(int resId, String title, String subTitle, int commType) {
        this.resId = resId;
        this.title = title;
        this.subTitle = subTitle;
        this.commType = commType;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getCommType() {
        return commType;
    }

    public void setCommType(int commType) {
        this.commType = commType;
    }
}
