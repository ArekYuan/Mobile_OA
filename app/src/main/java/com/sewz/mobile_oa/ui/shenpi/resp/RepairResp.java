package com.sewz.mobile_oa.ui.shenpi.resp;

import java.util.List;

/**
 * Created by Yuan on 2019/3/24.
 */

public class RepairResp {


    /**
     * msg : 获取物品维修列表成功
     * code : 200
     * data : [{"FuJianList":null,"Repair_Fault_Location":"南京市建邺区嘉陵江东街18号","TimeStr":"2019-03-22 22:15:00.0","UserName":"叶明珍","ChaoSongUserList":"叶明珍，朱志龙","LateTime":"2019-03-22 22:25:00.0","Repair_Fault_Desc":"显示器不显示，需要维修或更换","Repair_Department":"研发部门","Repair_Fee":"120","DealLable":"同意","Repair_GoodsName":"维修电脑","DealState":"1","WorkName":"叶明珍-物品维修申请","ShenPiUserList":"叶明珍，朱志龙","ID":"12","Repair_Phone":"13382825215"}]
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
         * Repair_Fault_Location : 南京市建邺区嘉陵江东街18号
         * TimeStr : 2019-03-22 22:15:00.0
         * UserName : 叶明珍
         * ChaoSongUserList : 叶明珍，朱志龙
         * LateTime : 2019-03-22 22:25:00.0
         * Repair_Fault_Desc : 显示器不显示，需要维修或更换
         * Repair_Department : 研发部门
         * Repair_Fee : 120
         * DealLable : 同意
         * Repair_GoodsName : 维修电脑
         * DealState : 1
         * WorkName : 叶明珍-物品维修申请
         * ShenPiUserList : 叶明珍，朱志龙
         * ID : 12
         * Repair_Phone : 13382825215
         */

        private Object FuJianList;
        private String Repair_Fault_Location;
        private String TimeStr;
        private String UserName;
        private String ChaoSongUserList;
        private String LateTime;
        private String Repair_Fault_Desc;
        private String Repair_Department;
        private String Repair_Fee;
        private String DealLable;
        private String Repair_GoodsName;
        private String DealState;
        private String WorkName;
        private String ShenPiUserList;
        private String ID;
        private String Repair_Phone;

        public Object getFuJianList() {
            return FuJianList;
        }

        public void setFuJianList(Object FuJianList) {
            this.FuJianList = FuJianList;
        }

        public String getRepair_Fault_Location() {
            return Repair_Fault_Location;
        }

        public void setRepair_Fault_Location(String Repair_Fault_Location) {
            this.Repair_Fault_Location = Repair_Fault_Location;
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

        public String getLateTime() {
            return LateTime;
        }

        public void setLateTime(String LateTime) {
            this.LateTime = LateTime;
        }

        public String getRepair_Fault_Desc() {
            return Repair_Fault_Desc;
        }

        public void setRepair_Fault_Desc(String Repair_Fault_Desc) {
            this.Repair_Fault_Desc = Repair_Fault_Desc;
        }

        public String getRepair_Department() {
            return Repair_Department;
        }

        public void setRepair_Department(String Repair_Department) {
            this.Repair_Department = Repair_Department;
        }

        public String getRepair_Fee() {
            return Repair_Fee;
        }

        public void setRepair_Fee(String Repair_Fee) {
            this.Repair_Fee = Repair_Fee;
        }

        public String getDealLable() {
            return DealLable;
        }

        public void setDealLable(String DealLable) {
            this.DealLable = DealLable;
        }

        public String getRepair_GoodsName() {
            return Repair_GoodsName;
        }

        public void setRepair_GoodsName(String Repair_GoodsName) {
            this.Repair_GoodsName = Repair_GoodsName;
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

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getRepair_Phone() {
            return Repair_Phone;
        }

        public void setRepair_Phone(String Repair_Phone) {
            this.Repair_Phone = Repair_Phone;
        }
    }
}
