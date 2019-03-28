package com.sewz.mobile_oa.ui.shenpi.resp;

import java.util.List;

/**
 * Created by Yuan on 2019/3/24.
 */

public class ApplyResp {

    /**
     * msg : 获取物品领用列表表成功
     * code : 200
     * data : [{"FuJianList":null,"TimeStr":"2019-03-22 21:45:00.0","UserName":"叶明珍","ChaoSongUserList":"叶明珍，十万军","Apply_Remark":"下周一归还","Apply_Use":"用来工作","Apply_num":"1","LateTime":"2019-03-22 21:55:00.0","OKUserList":null,"Apply_Date":"2019-03-22 21:45","DealLable":"同意","DealState":"1","WorkName":"叶明珍-物品领用申请","ShenPiUserList":"叶明珍，十万军","ID":11,"Apply_GoodsName":"借用联想笔记本"}]
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
         * TimeStr : 2019-03-22 21:45:00.0
         * UserName : 叶明珍
         * ChaoSongUserList : 叶明珍，十万军
         * Apply_Remark : 下周一归还
         * Apply_Use : 用来工作
         * Apply_num : 1
         * LateTime : 2019-03-22 21:55:00.0
         * OKUserList : null
         * Apply_Date : 2019-03-22 21:45
         * DealLable : 同意
         * DealState : 1
         * WorkName : 叶明珍-物品领用申请
         * ShenPiUserList : 叶明珍，十万军
         * ID : 11
         * Apply_GoodsName : 借用联想笔记本
         */

        private Object FuJianList;
        private String TimeStr;
        private String UserName;
        private String ChaoSongUserList;
        private String Apply_Remark;
        private String Apply_Use;
        private String Apply_num;
        private String LateTime;
        private Object OKUserList;
        private String Apply_Date;
        private String DealLable;
        private String DealState;
        private String WorkName;
        private String ShenPiUserList;
        private int ID;
        private String Apply_GoodsName;

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

        public String getApply_Remark() {
            return Apply_Remark;
        }

        public void setApply_Remark(String Apply_Remark) {
            this.Apply_Remark = Apply_Remark;
        }

        public String getApply_Use() {
            return Apply_Use;
        }

        public void setApply_Use(String Apply_Use) {
            this.Apply_Use = Apply_Use;
        }

        public String getApply_num() {
            return Apply_num;
        }

        public void setApply_num(String Apply_num) {
            this.Apply_num = Apply_num;
        }

        public String getLateTime() {
            return LateTime;
        }

        public void setLateTime(String LateTime) {
            this.LateTime = LateTime;
        }

        public Object getOKUserList() {
            return OKUserList;
        }

        public void setOKUserList(Object OKUserList) {
            this.OKUserList = OKUserList;
        }

        public String getApply_Date() {
            return Apply_Date;
        }

        public void setApply_Date(String Apply_Date) {
            this.Apply_Date = Apply_Date;
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

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

        public String getApply_GoodsName() {
            return Apply_GoodsName;
        }

        public void setApply_GoodsName(String Apply_GoodsName) {
            this.Apply_GoodsName = Apply_GoodsName;
        }
    }
}
