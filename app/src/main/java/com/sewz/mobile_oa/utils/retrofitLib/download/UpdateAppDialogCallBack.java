package com.sewz.mobile_oa.utils.retrofitLib.download;

import android.content.Context;


public abstract class UpdateAppDialogCallBack {

    Context context;


    boolean isCancel = false;

    public UpdateAppDialogCallBack(Context context) {
        this.context = context;
    }


    public abstract String dialogVersionText();


    public abstract String dialogAppSizeText();


    public abstract void dialogUpdateBtnClick();


    public abstract void dialogCancelBtnClick();


    public void dialogLeftBtnClick() {

    }


    public boolean isSystenAlert() {
        return false;
    }

    public boolean isCancel() {
        return isCancel;
    }

    public void setCancel(boolean isCancel) {
        this.isCancel = isCancel;
    }


}
