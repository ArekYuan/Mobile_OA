package com.sewz.mobile_oa.ui.shenpi.resp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yuan on 2019/3/24.
 */

public class BaoXaoResp {

    /**
     * msg : 获取报销列表成功
     * code : 200
     * data : [{"AirportFee":"5000","StateNow":null,"ShenPiYiJian":null,"LateTime":null,"TrafficFee":"1500","Caurse":"出差","WelfareFee":null,"CarFee":null,"HostelFee":"800","ShenPiUserList":"叶明珍,山石君","TrainFee":null,"ExpressFee":null,"ID":2,"FuJianList":null,"TimeStr":"2019-03-22 13:52:00.0","UserName":"Yuan","ChaoSongUserList":"叶明珍,山石君","MaterielFee":null,"OfficeFee":null,"OKUserList":null,"DealLable":null,"ServiceFee":null,"TotalFee":"7500","DevelopFee":null,"DealState":"0","BusinessFee":"200","WorkName":"Yuan-报销申请单（2019/03/22）","TravelFee":null,"AdvertFee":null,"OtherFee":null},{"AirportFee":"5000","StateNow":null,"ShenPiYiJian":null,"LateTime":null,"TrafficFee":"1500","Caurse":"出差","WelfareFee":null,"CarFee":null,"HostelFee":"800","ShenPiUserList":"叶明珍,山石君","TrainFee":null,"ExpressFee":null,"ID":4,"FuJianList":null,"TimeStr":"2019-03-22 14:26:00.0","UserName":"李明翰","ChaoSongUserList":"叶明珍,山石君","MaterielFee":null,"OfficeFee":null,"OKUserList":null,"DealLable":null,"ServiceFee":null,"TotalFee":"8000","DevelopFee":null,"DealState":"0","BusinessFee":"200","WorkName":"李明翰-报销申请单（2019/03/22）","TravelFee":null,"AdvertFee":null,"OtherFee":"500"},{"AirportFee":"5000","StateNow":null,"ShenPiYiJian":null,"LateTime":null,"TrafficFee":"1500","Caurse":"出差","WelfareFee":null,"CarFee":null,"HostelFee":"800","ShenPiUserList":"叶明珍,山石君","TrainFee":null,"ExpressFee":null,"ID":5,"FuJianList":null,"TimeStr":"2019-03-22 14:26:00.0","UserName":"李明翰","ChaoSongUserList":"叶明珍,山石君","MaterielFee":null,"OfficeFee":null,"OKUserList":null,"DealLable":null,"ServiceFee":null,"TotalFee":"8000","DevelopFee":null,"DealState":"0","BusinessFee":"200","WorkName":"李明翰-报销申请单（2019/03/22）","TravelFee":null,"AdvertFee":null,"OtherFee":"500"},{"AirportFee":"5000","StateNow":null,"ShenPiYiJian":null,"LateTime":null,"TrafficFee":"1500","Caurse":"出差","WelfareFee":null,"CarFee":null,"HostelFee":"800","ShenPiUserList":"叶明珍,山石君","TrainFee":null,"ExpressFee":null,"ID":6,"FuJianList":null,"TimeStr":"2019-03-22 14:32:00.0","UserName":"李明","ChaoSongUserList":"叶明珍,山石君","MaterielFee":null,"OfficeFee":null,"OKUserList":null,"DealLable":null,"ServiceFee":null,"TotalFee":"8000","DevelopFee":null,"DealState":"0","BusinessFee":"200","WorkName":"李明-报销申请单（2019/03/22）","TravelFee":null,"AdvertFee":null,"OtherFee":"500"},{"AirportFee":"5000","StateNow":null,"ShenPiYiJian":null,"LateTime":null,"TrafficFee":"1500","Caurse":"出差","WelfareFee":null,"CarFee":null,"HostelFee":"800","ShenPiUserList":"叶明珍,山石君","TrainFee":null,"ExpressFee":null,"ID":7,"FuJianList":null,"TimeStr":"2019-03-22 14:32:00.0","UserName":"李明","ChaoSongUserList":"叶明珍,山石君","MaterielFee":null,"OfficeFee":null,"OKUserList":null,"DealLable":null,"ServiceFee":null,"TotalFee":"8000","DevelopFee":null,"DealState":"0","BusinessFee":"200","WorkName":"李明-报销申请单（2019/03/22）","TravelFee":null,"AdvertFee":null,"OtherFee":"500"},{"AirportFee":null,"StateNow":null,"ShenPiYiJian":null,"LateTime":null,"TrafficFee":null,"Caurse":null,"WelfareFee":null,"CarFee":null,"HostelFee":null,"ShenPiUserList":"叶明珍，史万钧","TrainFee":null,"ExpressFee":null,"ID":8,"FuJianList":null,"TimeStr":"2019-03-22 18:31:00.0","UserName":"叶明珍","ChaoSongUserList":"叶明珍，史万钧","MaterielFee":null,"OfficeFee":null,"OKUserList":null,"DealLable":null,"ServiceFee":null,"TotalFee":null,"DevelopFee":null,"DealState":"0","BusinessFee":null,"WorkName":"叶明珍-物品采购申请","TravelFee":null,"AdvertFee":null,"OtherFee":null},{"AirportFee":null,"StateNow":null,"ShenPiYiJian":null,"LateTime":null,"TrafficFee":null,"Caurse":null,"WelfareFee":null,"CarFee":null,"HostelFee":null,"ShenPiUserList":"叶明珍，史万钧","TrainFee":null,"ExpressFee":null,"ID":9,"FuJianList":null,"TimeStr":"2019-03-22 18:31:00.0","UserName":"叶明珍","ChaoSongUserList":"叶明珍，史万钧","MaterielFee":null,"OfficeFee":null,"OKUserList":null,"DealLable":null,"ServiceFee":null,"TotalFee":null,"DevelopFee":null,"DealState":"0","BusinessFee":null,"WorkName":"叶明珍-物品采购申请","TravelFee":null,"AdvertFee":null,"OtherFee":null}]
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

    public static class DataBean implements Serializable {
        /**
         * AirportFee : 5000
         * StateNow : null
         * ShenPiYiJian : null
         * LateTime : null
         * TrafficFee : 1500
         * Caurse : 出差
         * WelfareFee : null
         * CarFee : null
         * HostelFee : 800
         * ShenPiUserList : 叶明珍,山石君
         * TrainFee : null
         * ExpressFee : null
         * ID : 2
         * FuJianList : null
         * TimeStr : 2019-03-22 13:52:00.0
         * UserName : Yuan
         * ChaoSongUserList : 叶明珍,山石君
         * MaterielFee : null
         * OfficeFee : null
         * OKUserList : null
         * DealLable : null
         * ServiceFee : null
         * TotalFee : 7500
         * DevelopFee : null
         * DealState : 0
         * BusinessFee : 200
         * WorkName : Yuan-报销申请单（2019/03/22）
         * TravelFee : null
         * AdvertFee : null
         * OtherFee : null
         */

        private String AirportFee;
        private Object StateNow;
        private Object ShenPiYiJian;
        private Object LateTime;
        private String TrafficFee;
        private String Caurse;
        private Object WelfareFee;
        private Object CarFee;
        private String HostelFee;
        private String ShenPiUserList;
        private Object TrainFee;
        private Object ExpressFee;
        private int ID;
        private Object FuJianList;
        private String TimeStr;
        private String UserName;
        private String ChaoSongUserList;
        private Object MaterielFee;
        private Object OfficeFee;
        private Object OKUserList;
        private Object DealLable;
        private Object ServiceFee;
        private String TotalFee;
        private Object DevelopFee;
        private String DealState;
        private String BusinessFee;
        private String WorkName;
        private Object TravelFee;
        private Object AdvertFee;
        private Object OtherFee;

        public String getAirportFee() {
            return AirportFee;
        }

        public void setAirportFee(String AirportFee) {
            this.AirportFee = AirportFee;
        }

        public Object getStateNow() {
            return StateNow;
        }

        public void setStateNow(Object StateNow) {
            this.StateNow = StateNow;
        }

        public Object getShenPiYiJian() {
            return ShenPiYiJian;
        }

        public void setShenPiYiJian(Object ShenPiYiJian) {
            this.ShenPiYiJian = ShenPiYiJian;
        }

        public Object getLateTime() {
            return LateTime;
        }

        public void setLateTime(Object LateTime) {
            this.LateTime = LateTime;
        }

        public String getTrafficFee() {
            return TrafficFee;
        }

        public void setTrafficFee(String TrafficFee) {
            this.TrafficFee = TrafficFee;
        }

        public String getCaurse() {
            return Caurse;
        }

        public void setCaurse(String Caurse) {
            this.Caurse = Caurse;
        }

        public Object getWelfareFee() {
            return WelfareFee;
        }

        public void setWelfareFee(Object WelfareFee) {
            this.WelfareFee = WelfareFee;
        }

        public Object getCarFee() {
            return CarFee;
        }

        public void setCarFee(Object CarFee) {
            this.CarFee = CarFee;
        }

        public String getHostelFee() {
            return HostelFee;
        }

        public void setHostelFee(String HostelFee) {
            this.HostelFee = HostelFee;
        }

        public String getShenPiUserList() {
            return ShenPiUserList;
        }

        public void setShenPiUserList(String ShenPiUserList) {
            this.ShenPiUserList = ShenPiUserList;
        }

        public Object getTrainFee() {
            return TrainFee;
        }

        public void setTrainFee(Object TrainFee) {
            this.TrainFee = TrainFee;
        }

        public Object getExpressFee() {
            return ExpressFee;
        }

        public void setExpressFee(Object ExpressFee) {
            this.ExpressFee = ExpressFee;
        }

        public int getID() {
            return ID;
        }

        public void setID(int ID) {
            this.ID = ID;
        }

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

        public Object getMaterielFee() {
            return MaterielFee;
        }

        public void setMaterielFee(Object MaterielFee) {
            this.MaterielFee = MaterielFee;
        }

        public Object getOfficeFee() {
            return OfficeFee;
        }

        public void setOfficeFee(Object OfficeFee) {
            this.OfficeFee = OfficeFee;
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

        public Object getServiceFee() {
            return ServiceFee;
        }

        public void setServiceFee(Object ServiceFee) {
            this.ServiceFee = ServiceFee;
        }

        public String getTotalFee() {
            return TotalFee;
        }

        public void setTotalFee(String TotalFee) {
            this.TotalFee = TotalFee;
        }

        public Object getDevelopFee() {
            return DevelopFee;
        }

        public void setDevelopFee(Object DevelopFee) {
            this.DevelopFee = DevelopFee;
        }

        public String getDealState() {
            return DealState;
        }

        public void setDealState(String DealState) {
            this.DealState = DealState;
        }

        public String getBusinessFee() {
            return BusinessFee;
        }

        public void setBusinessFee(String BusinessFee) {
            this.BusinessFee = BusinessFee;
        }

        public String getWorkName() {
            return WorkName;
        }

        public void setWorkName(String WorkName) {
            this.WorkName = WorkName;
        }

        public Object getTravelFee() {
            return TravelFee;
        }

        public void setTravelFee(Object TravelFee) {
            this.TravelFee = TravelFee;
        }

        public Object getAdvertFee() {
            return AdvertFee;
        }

        public void setAdvertFee(Object AdvertFee) {
            this.AdvertFee = AdvertFee;
        }

        public Object getOtherFee() {
            return OtherFee;
        }

        public void setOtherFee(Object OtherFee) {
            this.OtherFee = OtherFee;
        }
    }
}
