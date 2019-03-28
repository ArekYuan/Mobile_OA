package com.sewz.mobile_oa.ui.shenpi.resp;

import java.util.List;

/**
 * Created by Yuan on 2019/3/24.
 */

public class PurchaseResp {


    /**
     * msg : 获取物品采购列表成功
     * code : 200
     * data : [{"FuJianList":null,"TimeStr":"2019-03-22 18:31:00.0","UserName":"叶明珍","P_Department":"校长室","P_Num":"2","ChaoSongUserList":"叶明珍，史万钧","LateTime":null,"OKUserList":null,"DealLable":null,"P_Remark":"尽快办理","P_GoodsName":"老板椅","DealState":"0","WorkName":"叶明珍-物品采购申请","ShenPiUserList":"叶明珍，史万钧","P_Budget":null,"ID":8},{"FuJianList":null,"TimeStr":"2019-03-22 18:31:00.0","UserName":"叶明珍","P_Department":"校长室","P_Num":"2","ChaoSongUserList":"叶明珍，史万钧","LateTime":null,"OKUserList":null,"DealLable":null,"P_Remark":"尽快办理","P_GoodsName":"老板椅","DealState":"0","WorkName":"叶明珍-物品采购申请","ShenPiUserList":"叶明珍，史万钧","P_Budget":null,"ID":9}]
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
         * TimeStr : 2019-03-22 18:31:00.0
         * UserName : 叶明珍
         * P_Department : 校长室
         * P_Num : 2
         * ChaoSongUserList : 叶明珍，史万钧
         * LateTime : null
         * OKUserList : null
         * DealLable : null
         * P_Remark : 尽快办理
         * P_GoodsName : 老板椅
         * DealState : 0
         * WorkName : 叶明珍-物品采购申请
         * ShenPiUserList : 叶明珍，史万钧
         * P_Budget : null
         * ID : 8
         */

        private Object FuJianList;
        private String TimeStr;
        private String UserName;
        private String P_Department;
        private String P_Num;
        private String ChaoSongUserList;
        private Object LateTime;
        private Object OKUserList;
        private Object DealLable;
        private String P_Remark;
        private String P_GoodsName;
        private String DealState;
        private String WorkName;
        private String ShenPiUserList;
        private Object P_Budget;
        private int ID;

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

        public String getP_Department() {
            return P_Department;
        }

        public void setP_Department(String P_Department) {
            this.P_Department = P_Department;
        }

        public String getP_Num() {
            return P_Num;
        }

        public void setP_Num(String P_Num) {
            this.P_Num = P_Num;
        }

        public String getChaoSongUserList() {
            return ChaoSongUserList;
        }

        public void setChaoSongUserList(String ChaoSongUserList) {
            this.ChaoSongUserList = ChaoSongUserList;
        }

        public Object getLateTime() {
            return LateTime;
        }

        public void setLateTime(Object LateTime) {
            this.LateTime = LateTime;
        }

        public Object getOKUserList() {
            return OKUserList;
        }

        public void setOKUserList(Object OKUserList) {
            this.OKUserList = OKUserList;
        }

        public Object getDealLable() {
            return DealLable;
        }

        public void setDealLable(Object DealLable) {
            this.DealLable = DealLable;
        }

        public String getP_Remark() {
            return P_Remark;
        }

        public void setP_Remark(String P_Remark) {
            this.P_Remark = P_Remark;
        }

        public String getP_GoodsName() {
            return P_GoodsName;
        }

        public void setP_GoodsName(String P_GoodsName) {
            this.P_GoodsName = P_GoodsName;
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

        public Object getP_Budget() {
            return P_Budget;
        }

        public void setP_Budget(Object P_Budget) {
            this.P_Budget = P_Budget;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }
    }
}
