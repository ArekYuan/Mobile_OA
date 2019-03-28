package com.sewz.mobile_oa.ui.shenpi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.BaseActivity;
import com.sewz.mobile_oa.ui.shenpi.activity.ApplySpActivity;
import com.sewz.mobile_oa.ui.shenpi.activity.BaoXaoSpActivity;
import com.sewz.mobile_oa.ui.shenpi.activity.BorrowSpActivity;
import com.sewz.mobile_oa.ui.shenpi.activity.PurchaseSpActivity;
import com.sewz.mobile_oa.ui.shenpi.activity.RepairSpActivity;
import com.sewz.mobile_oa.ui.shenqing.adapter.CommAdapter;
import com.sewz.mobile_oa.ui.shenqing.resp.CommResp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShenPiActivity extends BaseActivity {

    @BindView(R.id.commView)
    RecyclerView commView;
    private CommAdapter adapter;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_shen_pi;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitleTxt("日常审批");
        setLeftImgBg(R.mipmap.back_white);
        initAdapter();
    }

    private void initAdapter() {
        List<CommResp> data = new ArrayList<>();
        data.add(new CommResp(R.mipmap.icon_bao, "费用报销审批", "费用报销审批", 1001));
        data.add(new CommResp(R.mipmap.icon_purchase, "物品采购审批", "物品采购审批", 1002));
        data.add(new CommResp(R.mipmap.icon_apply, "物品领用审批", "物品领用审批", 1003));
        data.add(new CommResp(R.mipmap.icon_repair, "物品维修审批", "物品维修审批", 1004));
        data.add(new CommResp(R.mipmap.icon_borrow, "物品借用审批", "物品借用审批", 1005));

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
                    case 1001://费用报销审批
                        startActivity(new Intent(mContext, BaoXaoSpActivity.class));
                        break;
                    case 1002://物品采购审批
                        startActivity(new Intent(mContext, PurchaseSpActivity.class));
                        break;
                    case 1003://物品领用审批
                        startActivity(new Intent(mContext, ApplySpActivity.class));
                        break;
                    case 1004://物品维修审批
                        startActivity(new Intent(mContext, RepairSpActivity.class));
                        break;
                    case 1005://物品借用审批
                        startActivity(new Intent(mContext, BorrowSpActivity.class));
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
