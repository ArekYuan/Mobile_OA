package com.sewz.mobile_oa.ui.gonggao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.BaseActivity;
import com.sewz.mobile_oa.ui.gonggao.adapter.GongGaoAdapter;
import com.sewz.mobile_oa.ui.gonggao.resp.GongGaoResp;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class GongGaoActivity extends BaseActivity {

    @BindView(R.id.yearTxt)
    TextView yearTxt;

    @BindView(R.id.monthTxt)
    TextView monthTxt;

    @BindView(R.id.gongGaoView)
    RecyclerView gongGaoView;
    private GongGaoAdapter gaoAdapter;

    private String year = "2019";
    private String month = "所有月份";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_gong_gao;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitleTxt("公司公告");
        setLeftImgBg(R.mipmap.back_white);
        initAdapter();
        initData(year, month);
    }

    private void initAdapter() {
        gaoAdapter = new GongGaoAdapter(mContext, R.layout.gong_gao_item_layout);
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        gongGaoView.setLayoutManager(manager);
        gongGaoView.setAdapter(gaoAdapter);
        gaoAdapter.setOnItemClickListener(position -> {
            GongGaoResp.DataBean bean = gaoAdapter.getData().get(position);
            startActivity(new Intent(mContext, GongGaoDetailActivity.class)
                    .putExtra("gongGaoData", bean));
        });
    }

    private void initData(String year, String month) {
        try {
            LoadDialog.show(mContext);
            Map<String, String> reqParams = new HashMap<>();
            reqParams.put("year", year);
            if (!month.equals("所有月份")) {
                reqParams.put("month", month);
            }
            Mobile_Api.getInstance().postData(UrlUtils.GONG_GAO_POST, reqParams, new Callback() {
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
                        GongGaoResp resp = gson.fromJson(jsonStr, GongGaoResp.class);
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

    List<GongGaoResp.DataBean> dataBeanList = new ArrayList<>();

    private void toRefreshView(final GongGaoResp resp) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (resp.getData() != null && resp.getData().size() > 0) {
                    gaoAdapter.setRefreshData(resp.getData());
                } else {
                    gaoAdapter.setRefreshData(dataBeanList);
                }
            }
        });

    }

    @Override
    protected void onLeftClick() {
        super.onLeftClick();
        finish();
    }


    @OnClick(R.id.yearLayout)
    public void onSelectYear() {
        final SelectDialog dialog = new SelectDialog(mContext, R.style.MyDialogStyle, 0);
        dialog.setItemClickListener(new SelectDialog.OnItemClickListener() {
            @Override
            public void onItemClick(String date) {
                dialog.dismiss();
                year = date;
                yearTxt.setText(date);
                initData(date, month);

            }
        });
        dialog.show();
    }

    @OnClick(R.id.monthLayout)
    public void onSelectMonth() {
        final SelectDialog dialog = new SelectDialog(mContext, R.style.MyDialogStyle, 1);
        dialog.setItemClickListener(new SelectDialog.OnItemClickListener() {
            @Override
            public void onItemClick(String date) {
                dialog.dismiss();
                month = date;
                monthTxt.setText(date);
                initData(year, date);
            }
        });
        dialog.show();
    }
}
