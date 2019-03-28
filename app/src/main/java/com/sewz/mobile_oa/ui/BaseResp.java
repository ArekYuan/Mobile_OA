package com.sewz.mobile_oa.ui;

/**
 * Created by ygy on 2018\3\19 0019.
 */

public abstract class BaseResp {

    public String status;
    public String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
