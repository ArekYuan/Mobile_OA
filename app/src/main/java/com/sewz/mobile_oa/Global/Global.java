package com.sewz.mobile_oa.Global;

import com.sewz.mobile_oa.OA_Application;

import static com.sewz.mobile_oa.utils.syspermission.Constant.LOGINUESRNAME;
import static com.sewz.mobile_oa.utils.syspermission.Constant.USER_ID;

/**
 * Created by Yuan on 2019/3/23.
 */

public class Global {

    private static volatile Global global;

    private Global() {
    }

    public static Global getInstance() {
        if (global == null) {
            synchronized (Global.class) {
                if (global == null) {
                    global = new Global();
                }
            }
        }
        return global;
    }

    /**
     * 获取 用户名
     *
     * @return
     */
    public String getUserName() {
        return new SharePref(OA_Application.mContext).getStringValue(LOGINUESRNAME);
    }

    /**
     * 保存 用户名
     *
     * @param userName
     */
    public void saveUserName(String userName) {
        new SharePref(OA_Application.mContext).setStringValue(LOGINUESRNAME, userName);
    }

    /**
     * 保存用户id
     *
     * @param UserId
     */
    public void saveUserId(String UserId) {
        new SharePref(OA_Application.mContext).setStringValue(USER_ID, UserId);
    }

    public String getUserId() {
        return new SharePref(OA_Application.mContext).getStringValue(USER_ID);
    }
}
