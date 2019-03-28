package com.sewz.mobile_oa.ui.shenpi.resp;

import java.util.List;

/**
 * Created by Yuan on 2019/3/24.
 */

public class BorrowResp {


    /**
     * msg : 获取物品借用申请列表成功
     * code : 200
     * data : [{"FuJianList":null,"TimeStr":"2019-03-22 22:35:00.0","UserName":"叶明珍","ChaoSongUserList":"叶明珍，黄宏亮","Borrow_EndTime":"2019-03-26 17:30:00.0","LateTime":"2019-03-22 23:00:00.0","DealLable":"拒绝","DealState":"1","Borrow_Num":"5","WorkName":"叶明珍-物品借用申请","ShenPiUserList":"叶明珍，黄宏亮","Borrow_GoodsName":"扫把","Borrow_Remark":"大扫除","ID":"13","Borrow_StartTime":"2019-03-23 09:00:00.0"}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * FuJianList : null
         * TimeStr : 2019-03-22 22:35:00.0
         * UserName : 叶明珍
         * ChaoSongUserList : 叶明珍，黄宏亮
         * Borrow_EndTime : 2019-03-26 17:30:00.0
         * LateTime : 2019-03-22 23:00:00.0
         * DealLable : 拒绝
         * DealState : 1
         * Borrow_Num : 5
         * WorkName : 叶明珍-物品借用申请
         * ShenPiUserList : 叶明珍，黄宏亮
         * Borrow_GoodsName : 扫把
         * Borrow_Remark : 大扫除
         * ID : 13
         * Borrow_StartTime : 2019-03-23 09:00:00.0
         */

        private Object FuJianList;
        private String TimeStr;
        private String UserName;
        private String ChaoSongUserList;
        private String Borrow_EndTime;
        private String LateTime;
        private String DealLable;
        private String DealState;
        private String Borrow_Num;
        private String WorkName;
        private String ShenPiUserList;
        private String Borrow_GoodsName;
        private String Borrow_Remark;
        private String ID;
        private String Borrow_StartTime;

        public Object getFuJianList() {
            return FuJianList;
        }

        public void setFuJianList(Object FuJianList) {
            this.FuJianList = FuJianList;
        }

        public String getTimeStr() {
            return TimeStr;
        }

        public void setTimeStr(String TimeStr) {
            this.TimeStr = TimeStr;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getChaoSongUserList() {
            return ChaoSongUserList;
        }

        public void setChaoSongUserList(String ChaoSongUserList) {
            this.ChaoSongUserList = ChaoSongUserList;
        }

        public String getBorrow_EndTime() {
            return Borrow_EndTime;
        }

        public void setBorrow_EndTime(String Borrow_EndTime) {
            this.Borrow_EndTime = Borrow_EndTime;
        }

        public String getLateTime() {
            return LateTime;
        }

        public void setLateTime(String LateTime) {
            this.LateTime = LateTime;
        }

        public String getDealLable() {
            return DealLable;
        }

        public void setDealLable(String DealLable) {
            this.DealLable = DealLable;
        }

        public String getDealState() {
            return DealState;
        }

        public void setDealState(String DealState) {
            this.DealState = DealState;
        }

        public String getBorrow_Num() {
            return Borrow_Num;
        }

        public void setBorrow_Num(String Borrow_Num) {
            this.Borrow_Num = Borrow_Num;
        }

        public String getWorkName() {
            return WorkName;
        }

        public void setWorkName(String WorkName) {
            this.WorkName = WorkName;
        }

        public String getShenPiUserList() {
            return ShenPiUserList;
        }

        public void setShenPiUserList(String ShenPiUserList) {
            this.ShenPiUserList = ShenPiUserList;
        }

        public String getBorrow_GoodsName() {
            return Borrow_GoodsName;
        }

        public void setBorrow_GoodsName(String Borrow_GoodsName) {
            this.Borrow_GoodsName = Borrow_GoodsName;
        }

        public String getBorrow_Remark() {
            return Borrow_Remark;
        }

        public void setBorrow_Remark(String Borrow_Remark) {
            this.Borrow_Remark = Borrow_Remark;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getBorrow_StartTime() {
            return Borrow_StartTime;
        }

        public void setBorrow_StartTime(String Borrow_StartTime) {
            this.Borrow_StartTime = Borrow_StartTime;
        }
    }
}
