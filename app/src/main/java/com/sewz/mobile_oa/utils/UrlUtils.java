package com.sewz.mobile_oa.utils;

/**
 * Created by Yuan on 2019/3/23.
 */

public class UrlUtils {

    //服务器地址
//    public static final String SERVER_URL = "http://192.168.1.106:8080";

    public static final String SERVER_URL = "http://121.43.166.54:8080/mobile_oa";
    /**
     * 登录接口 post请求
     */
    public static final String LOGIN_POST = "/api/login";

    /**
     * 获取公告列表 post 请求
     */
    public static final String GONG_GAO_POST = "/api/getGongGaoByDate";

    /**
     * 获取报销审批列表
     */
    public static final String BAO_XAO_IS_FINISHED = "/api/getBXaoIsFinList";

    /**
     * 获取抄送给我的 报销未审批列表
     */
    public static final String BAO_XAO_TO_ME = "/api/getBXaoByMeToDeal";

    /**
     * 获取采购审批列表
     */
    public static final String PURCHASE_IS_FINISHED = "/api/getPurIsFinishedList";

    /**
     * 获取抄送给我的 获取采购审批列表
     */
    public static final String PURCHASE_TO_ME = "/api/getPurByMeToDeal";

    /**
     * 获取物品领用审批列表
     */
    public static final String APPLY_IS_FINISHED = "/api/getApplyIsFinishedList";

    /**
     * 获取抄送给我的 品领用审批列表
     */
    public static final String APPLY_TO_ME = "/api/getApplyByMeToDeal";

    /**
     * 获取物品维修审批列表
     */
    public static final String REPAIR_IS_FINISHED = "/api/getRepairIsFinishedList";

    /**
     * 获取抄送给我的 物品维修审批列表
     */
    public static final String REPAIR_TO_ME = "/api/getRepairByMeToDeal";


    /**
     * 获取物品借用审批列表
     */
    public static final String BORROW_IS_FINISHED = "/api/getBorrowIsFinishedList";

    /**
     * 获取抄送给我的 物品借用审批列表
     */
    public static final String BORROW_TO_ME = "/api/getBorrowByMeToDeal";

    /**
     * 添加一条物品 领用申请记录
     */
    public static final String SAVE_APPLY_GOODS = "/api/saveApplyGoods";

    /**
     * 添加一条报销申请记录
     */
    public static final String SAVE_BAOXAO = "/api/saveBaoXao";

    /**
     * 添加一条 借用申请记录
     */
    public static final String SAVE_BORROW = "/api/saveBorrow";


    /**
     * 添加一条 物品采购申请记录
     */
    public static final String SAVE_PURCHASE = "/api/savePurchase";

    /**
     * 添加一条 物品维修记录
     */
    public static final String SAVE_REPAIR = "/api/saveRepair";


    /**
     * 注册
     */
    public static final String REGISTER = "/api/register";
}
