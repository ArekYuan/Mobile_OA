package com.sewz.mobile_oa.ui.shenqing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.BaseActivity;
import com.sewz.mobile_oa.ui.shenqing.activity.ApplySqActivity;
import com.sewz.mobile_oa.ui.shenqing.activity.BaoXaoSqActivity;
import com.sewz.mobile_oa.ui.shenqing.activity.BorrowSqActivity;
import com.sewz.mobile_oa.ui.shenqing.activity.PuchaseSqActivity;
import com.sewz.mobile_oa.ui.shenqing.activity.RepairSqActivity;
import com.sewz.mobile_oa.ui.shenqing.adapter.CommAdapter;
import com.sewz.mobile_oa.ui.shenqing.resp.CommResp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommApplyActivity extends BaseActivity {

    @BindView(R.id.commView)
    RecyclerView commView;
    private CommAdapter adapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_comm_apply;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitleTxt("日常申请");
        setLeftImgBg(R.mipmap.back_white);

        initAdapter();
    }

    private void initAdapter() {
        List<CommResp> data = new ArrayList<>();
        data.add(new CommResp(R.mipmap.icon_bao, "费用报销申请", "费用报销申请", 1001));
        data.add(new CommResp(R.mipmap.icon_purchase, "物品采购申请", "物品采购申请", 1002));
        data.add(new CommResp(R.mipmap.icon_apply, "物品领用申请", "物品领用申请", 1003));
        data.add(new CommResp(R.mipmap.icon_repair, "物品维修申请", "物品维修申请", 1004));
        data.add(new CommResp(R.mipmap.icon_borrow, "物品借用申请", "物品借用申请", 1005));


        adapter = new CommAdapter(mContext);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        commView.setLayoutManager(manager);
        commView.setAdapter(adapter);
        adapter.setData(data);

        adapter.setItemClickListener(new CommAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                int commType = adapter.getData().get(position).getCommType();
                switch (commType) {
                    case 1001://费用报销申请
                        startActivity(new Intent(mContext, BaoXaoSqActivity.class));
                        break;
                    case 1002://物品采购申请
                        startActivity(new Intent(mContext, PuchaseSqActivity.class));
                        break;
                    case 1003://物品领用申请
                        startActivity(new Intent(mContext, ApplySqActivity.class));
                        break;
                    case 1004://物品维修申请
                        startActivity(new Intent(mContext, RepairSqActivity.class));
                        break;
                    case 1005://物品借用申请
                        startActivity(new Intent(mContext, BorrowSqActivity.class));
                        break;
                }
            }
        });
    }

    @Override
    protected void onLeftClick() {
        super.onLeftClick();
        finish();
    }
}
