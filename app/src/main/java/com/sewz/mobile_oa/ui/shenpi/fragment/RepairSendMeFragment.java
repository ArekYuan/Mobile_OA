package com.sewz.mobile_oa.ui.shenpi.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sewz.mobile_oa.Global.Global;
import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.BaseFragment;
import com.sewz.mobile_oa.ui.shenpi.adapter.RepairAdapter;
import com.sewz.mobile_oa.ui.shenpi.resp.RepairResp;
import com.sewz.mobile_oa.utils.UrlUtils;
import com.sewz.mobile_oa.utils.YLog;
import com.sewz.mobile_oa.utils.dialog.LoadDialog;
import com.sewz.mobile_oa.utils.dialog.SelectDialog;
import com.sewz.mobile_oa.utils.network.Mobile_Api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Yuan on 2019/3/24.
 * 未审批
 */

public class RepairSendMeFragment extends BaseFragment {
    TextView yearTxt;
    TextView monthTxt;
    RecyclerView gongGaoView;
    private RepairAdapter gaoAdapter;

    private String year = "2019";
    private String month = "所有月份";
    private int dealType = 0;// 0--> 未审批
    LinearLayout monthLayout, yearLayout;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_no_layout;
    }

    @Override
    public void onViewInitialized(View view, Bundle savedInstanceState) {
        yearTxt = view.findViewById(R.id.yearTxt);
        monthTxt = view.findViewById(R.id.monthTxt);
        gongGaoView = view.findViewById(R.id.gongGaoView);

        monthLayout = view.findViewById(R.id.monthLayout);
        yearLayout = view.findViewById(R.id.yearLayout);

        onClick();
        initAdapter();
        initData(year, month, dealType);
//        handler.sendEmptyMessage(0x25);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            initData(year, month, dealType);
        }
    };

    private void onClick() {
        yearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SelectDialog dialog = new SelectDialog(mContext, R.style.MyDialogStyle, 0);
                dialog.setItemClickListener(new SelectDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(String date) {
                        dialog.dismiss();
                        year = date;
                        yearTxt.setText(date);
                        initData(date, month, dealType);
//                        handler.sendEmptyMessage(0x25);

                    }
                });
                dialog.show();
            }
        });

        monthLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final SelectDialog dialog = new SelectDialog(mContext, R.style.MyDialogStyle, 1);
                dialog.setItemClickListener(new SelectDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(String date) {
                        dialog.dismiss();
                        month = date;
                        monthTxt.setText(date);
                        initData(year, date, dealType);
//                        handler.sendEmptyMessage(0x25);
                    }
                });
                dialog.show();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(null);
    }

    private void initAdapter() {
        gaoAdapter = new RepairAdapter(mContext, R.layout.gong_gao_item_layout);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        gongGaoView.setLayoutManager(manager);
        gongGaoView.setHasFixedSize(true);
        gongGaoView.setAdapter(gaoAdapter);
        gaoAdapter.setOnItemClickListener(position -> {
            RepairResp.DataBean dataBean = gaoAdapter.getData().get(position);
//            startActivity(new Intent(mContext, ApplySqActivity.class)
//                    .putExtra("flag", 1)
//                    .putExtra("dealType", "1"));
        });
    }

    private void initData(String year, String month, int dealType) {
        try {
            LoadDialog.show(mContext);
            Map<String, String> reqParams = new HashMap<>();
            reqParams.put("year", year);
            if (!month.equals("所有月份")) {
                reqParams.put("month", month);
            }
            reqParams.put("dealType", dealType + "");
            reqParams.put("userName", Global.getInstance().getUserName());
            Mobile_Api.getInstance().postData(UrlUtils.REPAIR_TO_ME, reqParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    showShortToast("网络连接失败，请重试！");
                    LoadDialog.dismiss(mContext);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String jsonStr = response.body().string();
                        YLog.d("---gong_gao_resp-->" + jsonStr);
                        RepairResp resp = gson.fromJson(jsonStr, RepairResp.class);
                        LoadDialog.dismiss(mContext);
                        if (resp.getCode().equals("200")) {
                            toRefreshView(resp);
                        } else {
                            showShortToast(resp.getMsg());
                        }
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            LoadDialog.dismiss(mContext);
        }
    }

    List<RepairResp.DataBean> dataBeanList = new ArrayList<>();

    private void toRefreshView(final RepairResp resp) {
        if (resp.getData() != null && resp.getData().size() > 0) {
            gaoAdapter.setRefreshData(resp.getData());
        } else {
            gaoAdapter.setRefreshData(dataBeanList);
        }
    }
}
