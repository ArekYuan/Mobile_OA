package com.sewz.mobile_oa.ui.login.resp;

/**
 * Created by Yuan on 2019/3/23.
 */

public class LoginResp {

    /**
     * msg : 登录成功
     * code : 200
     * data : {"UserPwd":"B5C32B00080A8967","UserName":"admin","JiaoSe":"超级管理员","Sex":"","BirthDay":null,"EmailStr":"超级管理员","SFZSerils":null,"MingZu":null,"Department":"测试部门请不要删除","IfLogin":"是","ActiveTime":"2018-12-17 14:42:14.52","ZaiGang":"信息教师","BackInfo":"","HunYing":"","Serils":"admin","ID":"32","TrueName":"超级管理员","ZhiWei":"在岗","ZhengZhiMianMao":null}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * UserPwd : B5C32B00080A8967
         * UserName : admin
         * JiaoSe : 超级管理员
         * Sex :
         * BirthDay : null
         * EmailStr : 超级管理员
         * SFZSerils : null
         * MingZu : null
         * Department : 测试部门请不要删除
         * IfLogin : 是
         * ActiveTime : 2018-12-17 14:42:14.52
         * ZaiGang : 信息教师
         * BackInfo :
         * HunYing :
         * Serils : admin
         * ID : 32
         * TrueName : 超级管理员
         * ZhiWei : 在岗
         * ZhengZhiMianMao : null
         */

        private String UserPwd;
        private String UserName;
        private String JiaoSe;
        private String Sex;
        private Object BirthDay;
        private String EmailStr;
        private Object SFZSerils;
        private Object MingZu;
        private String Department;
        private String IfLogin;
        private String ActiveTime;
        private String ZaiGang;
        private String BackInfo;
        private String HunYing;
        private String Serils;
        private String ID;
        private String TrueName;
        private String ZhiWei;
        private Object ZhengZhiMianMao;

        public String getUserPwd() {
            return UserPwd;
        }

        public void setUserPwd(String UserPwd) {
            this.UserPwd = UserPwd;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getJiaoSe() {
            return JiaoSe;
        }

        public void setJiaoSe(String JiaoSe) {
            this.JiaoSe = JiaoSe;
        }

        public String getSex() {
            return Sex;
        }

        public void setSex(String Sex) {
            this.Sex = Sex;
        }

        public Object getBirthDay() {
            return BirthDay;
        }

        public void setBirthDay(Object BirthDay) {
            this.BirthDay = BirthDay;
        }

        public String getEmailStr() {
            return EmailStr;
        }

        public void setEmailStr(String EmailStr) {
            this.EmailStr = EmailStr;
        }

        public Object getSFZSerils() {
            return SFZSerils;
        }

        public void setSFZSerils(Object SFZSerils) {
            this.SFZSerils = SFZSerils;
        }

        public Object getMingZu() {
            return MingZu;
        }

        public void setMingZu(Object MingZu) {
            this.MingZu = MingZu;
        }

        public String getDepartment() {
            return Department;
        }

        public void setDepartment(String Department) {
            this.Department = Department;
        }

        public String getIfLogin() {
            return IfLogin;
        }

        public void setIfLogin(String IfLogin) {
            this.IfLogin = IfLogin;
        }

        public String getActiveTime() {
            return ActiveTime;
        }

        public void setActiveTime(String ActiveTime) {
            this.ActiveTime = ActiveTime;
        }

        public String getZaiGang() {
            return ZaiGang;
        }

        public void setZaiGang(String ZaiGang) {
            this.ZaiGang = ZaiGang;
        }

        public String getBackInfo() {
            return BackInfo;
        }

        public void setBackInfo(String BackInfo) {
            this.BackInfo = BackInfo;
        }

        public String getHunYing() {
            return HunYing;
        }

        public void setHunYing(String HunYing) {
            this.HunYing = HunYing;
        }

        public String getSerils() {
            return Serils;
        }

        public void setSerils(String Serils) {
            this.Serils = Serils;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getTrueName() {
            return TrueName;
        }

        public void setTrueName(String TrueName) {
            this.TrueName = TrueName;
        }

        public String getZhiWei() {
            return ZhiWei;
        }

        public void setZhiWei(String ZhiWei) {
            this.ZhiWei = ZhiWei;
        }

        public Object getZhengZhiMianMao() {
            return ZhengZhiMianMao;
        }

        public void setZhengZhiMianMao(Object ZhengZhiMianMao) {
            this.ZhengZhiMianMao = ZhengZhiMianMao;
        }
    }
}
