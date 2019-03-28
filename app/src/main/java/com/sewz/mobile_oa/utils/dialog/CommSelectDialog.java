package com.sewz.mobile_oa.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.widget.GridView;
import android.widget.TextView;

import com.sewz.mobile_oa.R;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuan on 2019/3/23.
 */

public class CommSelectDialog extends Dialog {

    private String[] shenpi = new String[]{"檀时俊", "程根宝", "刘长珍",
            "贺飞", "陈昌荣", "程根宝",
            "admin", "全景明", "小焦",
            "黄维丰", "陈建忠", "黄靖",
            "金璐", "谭晓丽", " 徐总",
            "陈良飞", "朱德利", "赵大文",
            "操彦", "陈云梅", "费雨濛",
            "李海燕", "李云华", "邵芳",
            "檀艳", "熊昕", "许洁",
            "薛瑞雪", "殷海霞", "俞永军",
            "郑研科", "朱立红", "Yuan"};

    private String[] goods = new String[]{"电脑", "A4纸", "小刀",
            "铅笔", "圆珠笔", "胶带",
            "胶棒", "胶水", "橡皮",
            "双面胶", "文件袋", "卷尺",
            "胶带", "笔记本", " 工字钉",
            "拖把", "黑板", "消防栓",
            "单双杠", "除草机", "抽纸",
            "剪刀", "奖状", "证书"};

    private String[] departMent = new String[]{
            "测试", "办公室", "教务处",
            "德育处", "后勤", "团委",
            "公会", "会计室", "图书馆",
            "文印室", "教研组", "年级组",
            "胶带", "笔记本", " 工字钉",
            "拖把", "黑板", "消防栓",
            "单双杠", "除草机", "抽纸",
            "剪刀", "奖状", "证书"
    };

    private int type;//
    private GridView selectView;
    private CommSelectAdapter selectAdapter;
    private Context mContext;
    private TextView cancelTxt;
    private TextView okTxt;
    private String name = "";
    private List<String> shenp;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String date);
    }


    public CommSelectDialog(@NonNull Context context) {
        super(context);
    }

    public CommSelectDialog(@NonNull Context context, int themeResId, int type) {
        super(context, themeResId);
        this.type = type;
        this.mContext = context;
        shenp = new ArrayList<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_comm_select_layout);
        selectView = findViewById(R.id.selectView);
        cancelTxt = findViewById(R.id.cancelTxt);
        okTxt = findViewById(R.id.okTxt);
        initAdapter();
        setCanceledOnTouchOutside(false);

        cancelTxt.setOnClickListener(view -> {
            dismiss();
        });

        okTxt.setOnClickListener(view -> {
            if (itemClickListener != null) {
                name = StringUtils.join(shenp, ",");
                itemClickListener.onItemClick(name);
            }
        });
    }

    private void initAdapter() {
        if (type == 0) {//审批人，抄送人
            selectAdapter = new CommSelectAdapter(mContext, shenpi);
        } else if (type == 1) {//选择物品
            selectAdapter = new CommSelectAdapter(mContext, goods);
        } else if (type == 2) {//选择部门
            selectAdapter = new CommSelectAdapter(mContext, departMent);
        }
        selectView.setAdapter(selectAdapter);
        selectAdapter.setOnSelectListener((name1, b) -> {
            if (b) {
                shenp.add(name1);
            } else {
                shenp.remove(name1);
            }
        });
    }
}
