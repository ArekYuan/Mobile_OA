package com.sewz.mobile_oa.Global;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.sewz.mobile_oa.utils.syspermission.Constant;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


/**
 * Created by ygy on 2017/1/11.
 */

public class SharePref {

    /**
     * SharedPreferences对象
     */
    private SharedPreferences pref;

    /**
     * editor对象
     */
    private SharedPreferences.Editor editor;

    @SuppressLint("CommitPrefEdits")
    public SharePref(Context context) {
        pref = context.getSharedPreferences(Constant.LOVE_TALK_INFO, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public SharePref(Context context, String prefName){
        pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    /**
     * 清空数据
     */
    public void clearData() {
        if (null != editor) {
            editor.clear().commit();
        }
    }

    /**
     * 缓存 String类型的数据
     *
     * @param key
     * @param value
     */
    public void setStringValue(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 获取 String类型的数据
     *
     * @param key
     * @return
     */
    public String getStringValue(String key) {
        String value = pref.getString(key, "");
        return value;
    }

    /**
     * 缓存 int 类型的数据
     *
     * @param key
     * @param value
     */
    public void setIntValue(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 获取 int 类型的数据
     *
     * @param key
     * @return
     */
    public int getIntValue(String key) {
        int value = pref.getInt(key, -1);
        return value;
    }

    /**
     * 缓存 int 类型的数据
     *
     * @param key
     * @param value
     */
    public void setFloatValue(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * 获取 int 类型的数据
     *
     * @param key
     * @return
     */
    public float getFloatValue(String key) {
        float value = pref.getFloat(key, 0f);
        return value;
    }

    /**
     * 缓存 int 类型的数据
     *
     * @param key
     * @param value
     */
    public void setBooleanValue(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 获取 int 类型的数据
     *
     * @param key
     * @return
     */
    public boolean getBooleanValue(String key, boolean defValue) {
        boolean value = pref.getBoolean(key, defValue);
        return value;
    }


    /**
     * writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
     * 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
     *
     * @param object 待加密的转换为String的对象
     * @return String   加密后的String
     */
    private String Object2String(Object object) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(object);
            String string = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            objectOutputStream.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 使用Base64解密String，返回Object对象
     *
     * @param objectString 待解密的String
     * @return object      解密后的object
     */
    private Object String2Object(String objectString) {
        byte[] mobileBytes = Base64.decode(objectString.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(mobileBytes);
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(byteArrayInputStream);
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 使用SharedPreference保存对象
     *
     * @param key        储存对象的key
     * @param saveObject 储存的对象
     */
    public  void save(String key, Object saveObject) {
        String string = Object2String(saveObject);
        editor.putString(key, string);
        editor.commit();
    }

    /**
     * 获取SharedPreference保存的对象
     *
     * @param key     储存对象的key
     * @return object 返回根据key得到的对象
     */
    public Object get(String key) {
        String string = pref.getString(key, null);
        if (string != null) {
            Object object = String2Object(string);
            return object;
        } else {
            return null;
        }
    }

}
