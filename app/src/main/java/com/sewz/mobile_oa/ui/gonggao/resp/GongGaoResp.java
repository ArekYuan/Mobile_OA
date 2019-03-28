package com.sewz.mobile_oa.ui.gonggao.resp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Yuan on 2019/3/23.
 */

public class GongGaoResp {


    /**
     * msg : 获取公告列表成功
     * code : 200
     * data : [{"TimeStr":"2017-03-13 14:46:04.893","UserName":"杨琼","TypeStr":"单位","ID":"26","FuJian":"","TitleStr":"试用账号：ccr,yzmffx,zhen密码均是123456 详情请登录后查看","UserBuMen":"所有部门","ContentStr":"<p>PC端地址：oa.suneinfo.com<\/p><p>手机端地址：通过企业号，进入南京一中明发分校移动办公<\/p>"},{"TimeStr":"2017-03-22 05:22:10.797","UserName":"全景明","TypeStr":"单位","ID":"28","FuJian":"","TitleStr":"党建责任考核目标","UserBuMen":"所有部门","ContentStr":"<p>ddd<\/p>"},{"TimeStr":"2017-03-30 13:25:21.793","UserName":"admin","TypeStr":"单位","ID":"30","FuJian":"","TitleStr":"功能及地址URL","UserBuMen":"","ContentStr":"<p>.功能菜单划分<br />一、 日常申请<br />1物品采购申请<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=9&amp;WorkFlowID=10\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkFlowAdd.aspx?FormID=9&amp;WorkFlowID=10<\/a><br />2物品领用申请<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=10&amp;WorkFlowID=11\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkFlowAdd.aspx?FormID=10&amp;WorkFlowID=11<\/a><br />3物品维修申请<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=11&amp;WorkFlowID=12\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkFlowAdd.aspx?FormID=11&amp;WorkFlowID=12<\/a><br />4调课申请<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=13&amp;WorkFlowID=14\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkFlowAdd.aspx?FormID=13&amp;WorkFlowID=14<\/a><br />5活动申请<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=16&amp;WorkFlowID=17\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkFlowAdd.aspx?FormID=16&amp;WorkFlowID=17<\/a><br />6请假申请:http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkFlowAdd.aspx?FormID=8&amp;WorkFlowID=8<br />7培训申请<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=14&amp;WorkFlowID=15\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkFlowAdd.aspx?FormID=14&amp;WorkFlowID=15<\/a><br />8专用教室申请<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=15&amp;WorkFlowID=16\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkFlowAdd.aspx?FormID=15&amp;WorkFlowID=16<\/a><br />9加班申请<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=12&amp;WorkFlowID=13\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkFlowAdd.aspx?FormID=12&amp;WorkFlowID=13<\/a><br />10教研活动申请（校内/校外）<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=17&amp;WorkFlowID=18\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkFlowAdd.aspx?FormID=17&amp;WorkFlowID=18<\/a><br />二、 个人中心<br />1． 我的申请<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDo.aspx\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDo.aspx<\/a><br />2． 我已审批<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/YiBanWork.aspx\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/YiBanWork.aspx<\/a><br />3． 我的荣誉（右上角加新建功能）<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDo.aspx\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDo.aspx<\/a>？workname=&#39;我的荣誉&#39;,新建链接：<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=20&amp;WorkFlowID=21\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/<\/a><a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=12&amp;WorkFlowID=13\">NWorkFlowAdd<\/a>.aspx?FormID=20&amp;WorkFlowID=21<br />4． 我的课题（右上角加新建功能）<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDo.aspx\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDo.aspx<\/a>？workname=&#39;我的课题&#39; ,新建链接：<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=19&amp;WorkFlowID=20\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/<\/a><a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=12&amp;WorkFlowID=13\">NWorkFlowAdd<\/a>.aspx?FormID=19&amp;WorkFlowID=20<br />5． 我的公开课（右上角加新建功能）<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDo.aspx\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDo.aspx<\/a>？workname=&#39;我的公开课&#39;,新建链接：<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=18&amp;WorkFlowID=19\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/<\/a><a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=12&amp;WorkFlowID=13\">NWorkFlowAdd<\/a>.aspx?FormID=18&amp;WorkFlowID=19<br />6． 个人资料(链接到个人文档)http://mf.suneinfo.com/aspx/moa/DocCenter/DocCenter.aspx?Type=个人文件&amp;DirID=0<br />7． 我的计划:WorkPlan/MyWorkPlan.aspx<\/p><p>8． 协同计划:WorkPlan/ManageWorkPlan.aspx<\/p><p>9． 我的汇报:WorkPlan/HuiBao.aspx<\/p><p>10． 协同汇报:WorkPlan/HuiBaoOK.aspx<\/p><p>三、 日常审批<br />1．待我审批 <a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NowWorkFlow.aspx\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NowWorkFlow.aspx<\/a><br />四、 我的邮件<a href=\"http://mf.suneinfo.com/aspx/moa/LanEmail/LanEmailShou.aspx\">http://mf.suneinfo.com/aspx/moa/LanEmail/LanEmailShou.aspx<\/a><br />五、 学校公告<a href=\"http://mf.suneinfo.com/aspx/moa/GongGao/GongGao.aspx?Type\">http://mf.suneinfo.com/aspx/moa/GongGao/GongGao.aspx?Type<\/a>=单位<br />六、 我的课表<a href=\"http://mf.suneinfo.com/aspx/moa/Jwgl/ERPRklsView.aspx\">http://mf.suneinfo.com/aspx/moa/Jwgl/ERPRklsView.aspx<\/a><br />七、 班级课表<a href=\"http://mf.suneinfo.com/aspx/moa/Jwgl/ERPBjkbView.aspx\">http://mf.suneinfo.com/aspx/moa/Jwgl/ERPBjkbView.aspx<\/a><br />八、 每周工作安排（学校每周工作安排）<a href=\"http://mf.suneinfo.com/aspx/moa/GongGao/GongGao.aspx?Type\">http://mf.suneinfo.com/aspx/moa/GongGao/GongGao.aspx?Type<\/a>=工作安排<br />九、 学校简介（基本信息，统计信息）要新做页面<br />十、 荣誉中心 报表中心设置报表后才有<br />十一、 文档中心(链接到单位文档)http://mf.suneinfo.com/aspx/moa/DocCenter/DocCenter.aspx?Type=单位文件&amp;DirID=0<br />十二、 常规检查（综合检查）<a href=\"http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkToDoAdd.aspx?FormID=22&amp;WorkFlowID=22\">http://mf.suneinfo.com/aspx/moa/NWorkFlow/NWorkFlowAdd.aspx?FormID=22&amp;WorkFlowID=22<\/a><br />十三、 统计报表<\/p><p><br /><\/p><p>.功能修改明细<br />一、 日常申请-新建申请,待我审批处理页面(手机端+PC端)<br />1．新建申请手机端日期字段，无法选择日期<br /> &nbsp; 2．下拉选择显示有问题，另外下拉缺省值不能为空<br /> &nbsp; 3．输入框及数字输入框能设定缺省值，用户以区分必填项和非必填项<br /> &nbsp; 4．附件显示栏去除，删除文件，编辑文件，阅读文件按钮去除，附件上传栏不能删除<br />5.弹出选择框的按钮太小，不容易点击，修改成只要点击到文本框即弹出选择对话框来<br />6.审批栏只保留审批人选择，抄送人选择，其它都去除.<br />7.选择部门点确定不返回<\/p><p style=\"margin-left:37px;\">一、<span style=\"font-family:宋体\">日常申请缺省值如下<\/span><\/p><p style=\"text-indent:0;margin-left:24px;\">1<span style=\"font-family:宋体\">物品采购申请<\/span>:<span style=\"font-family:宋体\">物品名称从弹出<\/span>selectProduct.aspx<span style=\"font-family:宋体\">页来选取，<\/span>PC<span style=\"font-family:宋体\">端我已做好了，采购数量缺省为<\/span>1,<span style=\"font-family:宋体\">金额：<\/span>1<\/p><p style=\"text-indent:0;margin-left:24px;\">2<span style=\"font-family:宋体\">物品领用申请：物品名称从弹出<\/span>selectProduct.aspx<span style=\"font-family:宋体\">页来选取，<\/span>PC<span style=\"font-family:宋体\">端我已做好了，数量为<\/span>1<\/p><p style=\"text-indent:0;margin-left:24px;\">3<span style=\"font-family:宋体\">物品维修申请：物品名称从弹出<\/span>selectProduct.aspx<span style=\"font-family:宋体\">页来选取，<\/span>PC<span style=\"font-family:宋体\">端我已做好了，<\/span><\/p><p style=\"text-indent:0;margin-left:24px;\">4<span style=\"font-family:宋体\">调课申请：调课节次缺少为第一节课，被调课节次为第一节课，调课时间，被调课时间都是当前日期<\/span><\/p><p style=\"text-indent:0;margin-left:24px;\">5<span style=\"font-family:宋体\">活动申请：是否用车缺省为：否<\/span><\/p><p style=\"text-indent:0;margin-left:24px;\">6<span style=\"font-family:宋体\">请假申请<\/span>:<span style=\"font-family:宋体\">请假事由：事假，请假天数：半天，请假人职务：任课老师，请假开始日期，缺省为当前日期，销假日期也是当前日期<\/span><\/p><p style=\"text-indent:0;margin-left:24px;\">7<span style=\"font-family:宋体\">培训申请<\/span>:<span style=\"font-family:宋体\">学习时间缺少为半天<\/span><\/p><p style=\"text-indent:0;margin-left:24px;\">8<span style=\"font-family:宋体\">专用教室申请<\/span>: <span style=\"font-family:宋体\">教室类型<\/span>为阶梯教室，预计占用时间：2<span style=\"font-family:宋体\">小时<\/span><\/p><p style=\"text-indent:0;margin-left:24px;\">9<span style=\"font-family:宋体\">加班申请<\/span>:<span style=\"font-family:宋体\">起始时间都是当前日期，加班时间缺省为<\/span>0.5<span style=\"font-family:宋体\">天<\/span><\/p><p style=\"text-indent:0;margin-left:24px;\">10<span style=\"font-family:宋体\">教研活动申请（校内<\/span>/<span style=\"font-family:宋体\">校外）<\/span>:<span style=\"font-family:宋体\">类型：校内，是否用餐<\/span>为否<span style=\"font-family:宋体\">时间显示当前日期<\/span><\/p><p><br /><\/p>"}]
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
         * TimeStr : 2017-03-13 14:46:04.893
         * UserName : 杨琼
         * TypeStr : 单位
         * ID : 26
         * FuJian :
         * TitleStr : 试用账号：ccr,yzmffx,zhen密码均是123456 详情请登录后查看
         * UserBuMen : 所有部门
         * ContentStr : <p>PC端地址：oa.suneinfo.com</p><p>手机端地址：通过企业号，进入南京一中明发分校移动办公</p>
         */

        private String TimeStr;
        private String UserName;
        private String TypeStr;
        private String ID;
        private String FuJian;
        private String TitleStr;
        private String UserBuMen;
        private String ContentStr;

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

        public String getTypeStr() {
            return TypeStr;
        }

        public void setTypeStr(String TypeStr) {
            this.TypeStr = TypeStr;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getFuJian() {
            return FuJian;
        }

        public void setFuJian(String FuJian) {
            this.FuJian = FuJian;
        }

        public String getTitleStr() {
            return TitleStr;
        }

        public void setTitleStr(String TitleStr) {
            this.TitleStr = TitleStr;
        }

        public String getUserBuMen() {
            return UserBuMen;
        }

        public void setUserBuMen(String UserBuMen) {
            this.UserBuMen = UserBuMen;
        }

        public String getContentStr() {
            return ContentStr;
        }

        public void setContentStr(String ContentStr) {
            this.ContentStr = ContentStr;
        }
    }
}
