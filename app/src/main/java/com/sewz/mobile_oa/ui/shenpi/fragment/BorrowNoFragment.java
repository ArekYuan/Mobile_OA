package com.sewz.mobile_oa.ui.shenpi.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.BaseFragment;
import com.sewz.mobile_oa.ui.shenpi.adapter.BorrowAdapter;
import com.sewz.mobile_oa.ui.shenpi.resp.BorrowResp;
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

public class BorrowNoFragment extends BaseFragment {
    TextView yearTxt;
    TextView monthTxt;
    RecyclerView gongGaoView;
    private BorrowAdapter gaoAdapter;

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
//        handler.sendEmptyMessage(0x17);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            initData(year, month, dealType);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(null);
    }


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
//                        handler.sendEmptyMessage(0x17);

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
//                        handler.sendEmptyMessage(0x17);
                    }
                });
                dialog.show();
            }
        });
    }

    private void initAdapter() {
        gaoAdapter = new BorrowAdapter(mContext, R.layout.gong_gao_item_layout);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        gongGaoView.setLayoutManager(manager);
        gongGaoView.setHasFixedSize(true);
        gongGaoView.setAdapter(gaoAdapter);
        gaoAdapter.setOnItemClickListener(position -> {
            BorrowResp.DataBean dataBean = gaoAdapter.getData().get(position);
//            startActivity(new Intent(mContext, ApplySqActivity.class)
//                    .putExtra("flag", 1)
//                    .putExtra("dealType", "0"));
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
            Mobile_Api.getInstance().postData(UrlUtils.BORROW_IS_FINISHED, reqParams, new Callback() {
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
                        BorrowResp resp = gson.fromJson(jsonStr, BorrowResp.class);
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

    List<BorrowResp.DataBean> dataBeanList = new ArrayList<>();

    private void toRefreshView(final BorrowResp resp) {
        if (resp.getData() != null && resp.getData().size() > 0) {
            gaoAdapter.setRefreshData(resp.getData());
        } else {
            gaoAdapter.setRefreshData(dataBeanList);
        }
    }
}
