package com.sewz.mobile_oa.ui.shenqing.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sewz.mobile_oa.Global.Global;
import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.BaseActivity;
import com.sewz.mobile_oa.ui.photoSelect.ChoosePicUtil;
import com.sewz.mobile_oa.ui.photoSelect.ImageUtil;
import com.sewz.mobile_oa.ui.photoSelect.PhotoAdapter;
import com.sewz.mobile_oa.ui.shenqing.resp.ShenQingResp;
import com.sewz.mobile_oa.utils.UrlUtils;
import com.sewz.mobile_oa.utils.YLog;
import com.sewz.mobile_oa.utils.datepicker.CustomDatePicker;
import com.sewz.mobile_oa.utils.datepicker.DateFormatUtils;
import com.sewz.mobile_oa.utils.dialog.CommSelectDialog;
import com.sewz.mobile_oa.utils.dialog.LoadDialog;
import com.sewz.mobile_oa.utils.network.Mobile_Api;

import org.apache.commons.lang3.StringUtils;

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

public class ApplySqActivity extends BaseActivity {

    @BindView(R.id.workNameTxt)
    TextView workNameTxt;

    @BindView(R.id.applyPersonTxt)
    TextView applyPersonTxt;

    @BindView(R.id.Apply_GoodsNameEdit)
    TextView Apply_GoodsNameTxt;

    @BindView(R.id.Apply_numEdit)
    EditText Apply_numEdit;

    @BindView(R.id.Apply_DateEdit)
    TextView Apply_DateTxt;

    @BindView(R.id.Apply_UseEdit)
    EditText Apply_UseEdit;

    @BindView(R.id.Apply_RemarkEdit)
    EditText Apply_RemarkEdit;

    @BindView(R.id.sizeTxt)
    TextView sizeTxt;

    @BindView(R.id.showPhotoView)
    RecyclerView showPhotoView;
    private PhotoAdapter adapter;

    @BindView(R.id.shenPiUserTxt)
    TextView shenPiUserTxt;

    @BindView(R.id.ChaoSongUserTxt)
    TextView ChaoSongUserTxt;

    @BindView(R.id.commitBtn)
    Button commitBtn;

    @BindView(R.id.shenp_layout)
    LinearLayout shenp_layout;

    private String dealType = "0";
    private String fuJianStr = "";
    private CustomDatePicker mTimerPicker;
    private int flag = -1;
    private String dealType_ = "0";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_qpply_sq;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitleTxt("物品领用申请");
        setLeftImgBg(R.mipmap.back_white);

        flag = getIntent().getIntExtra("flag", -1);
        dealType_ = getIntent().getStringExtra("dealType");

        workNameTxt.setText(Global.getInstance().getUserName() + "-物品领用申请(" + dateToStamp(System.currentTimeMillis()) + ")");
        applyPersonTxt.setText(Global.getInstance().getUserName());
        initView(flag, dealType_);
        initAdapter();
        initTimerPicker();
        initSubmit();
    }

    private void initView(int flag, String dealType_) {
        if (flag != 0) {
            if (flag == 1 && dealType_.equals("0")) {//从审批页面过来 // 已审批/未审批
                commitBtn.setVisibility(View.GONE);
            } else if (flag == 1 && dealType_.equals("1")) {//抄送给我的页面
                shenp_layout.setVisibility(View.VISIBLE);
                commitBtn.setVisibility(View.GONE);
            }

        } else {

        }

    }

    private void initTimerPicker() {

        String beginTime = toDate(System.currentTimeMillis());
        String endTime = "2020-03-26 17:00";
//        Apply_DateTxt.setText(endTime);

        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                Apply_DateTxt.setText(DateFormatUtils.long2Str(timestamp, true));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerPicker.setCancelable(true);
        // 显示时和分
        mTimerPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mTimerPicker.setCanShowAnim(true);
    }

    private void initAdapter() {
        LinearLayoutManager manager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        showPhotoView.setLayoutManager(manager);
        adapter = new PhotoAdapter(mContext, 2);
        showPhotoView.setAdapter(adapter);

        adapter.setClickListener(new PhotoAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                getPhoto();
            }

            @Override
            public void onDeleteClick(int position, List<String> data) {
                if (url != null && url.size() > 0) {
                    url.remove(position);
                    adapter.setData(url);
                    sizeTxt.setText(url.size() + "/2");
                    return;
                }
            }
        });

    }

    private void getPhoto() {
        int chooseNum = 2;
        if (adapter.getData().size() > 0) {
            chooseNum = chooseNum - adapter.getData().size();
        }

        new ChoosePicUtil.Builder(mContext)
                .setChooseNumber(chooseNum)
//                .setCrop(true)
                .setMultiple(true)
                .setResultCallback(picPathList ->
                        showPic(picPathList)).create().show();
    }

    List<String> url = new ArrayList<>();

    private void showPic(List<String> picPathList) {
        url.clear();
        url = picPathList;
        if (picPathList != null && picPathList.size() > 0) {
            sizeTxt.setText(picPathList.size() + "/2");
            adapter.setData(picPathList);
        }

    }

    private void initSubmit() {
        commitBtn.setOnClickListener(view -> {
            doRequest();
        });
    }

    private void doRequest() {
        String time = toDate(System.currentTimeMillis());
        String workName = workNameTxt.getText().toString();
        String userName = applyPersonTxt.getText().toString();
        String goodName = Apply_GoodsNameTxt.getText().toString();
        String num = Apply_numEdit.getText().toString();
        String date = Apply_DateTxt.getText().toString();
        String useDesc = Apply_UseEdit.getText().toString();
        String remark = Apply_RemarkEdit.getText().toString();
        String chaoSong = ChaoSongUserTxt.getText().toString();
        String shenPi = shenPiUserTxt.getText().toString();


        if (isChecked(goodName, num, chaoSong, shenPi)) {
            doCommit(time, workName, userName,
                    goodName, num, date,
                    useDesc, remark, chaoSong,
                    shenPi, dealType, fuJianStr);
        }
    }

    private void doCommit(String time, String workName,
                          String userName, String goodName,
                          String num, String date,
                          String useDesc, String remark,
                          String chaoSong, String shenPi,
                          String dealType, String fuJianStr) {
        // 网络操作...
        try {
            LoadDialog.show(mContext);
            Map<String, String> reqParams = new HashMap<>();
            List<String> str = new ArrayList<>();
            if (adapter.getData() != null && adapter.getData().size() > 0) {
                for (String path : adapter.getData()) {
                    str.add(ImageUtil.getImageStr(path));
                }
            }
            if (str != null && str.size() > 0) {
                fuJianStr = StringUtils.join(str, ",");
            }
            reqParams.put("FuJianList", fuJianStr);
            reqParams.put("TimeStr", time);
            reqParams.put("WorkName", workName);
            reqParams.put("UserName", userName);
            reqParams.put("ShenPiUserList", shenPi);
            reqParams.put("ChaoSongUserList", chaoSong);
            reqParams.put("DealState", dealType);
            reqParams.put("Apply_GoodsName", goodName);
            reqParams.put("Apply_num", num);
            reqParams.put("Apply_Date", date);
            reqParams.put("Apply_Use", useDesc);
            reqParams.put("Apply_Remark", remark);

            Mobile_Api.getInstance().postData(UrlUtils.SAVE_APPLY_GOODS, reqParams, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    showShortToast("网络连接失败，请重试！");
                    LoadDialog.dismiss(mContext);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        String jsonStr = response.body().string();
                        YLog.d("---login_resp-->" + jsonStr);
                        ShenQingResp resp = gson.fromJson(jsonStr, ShenQingResp.class);
                        LoadDialog.dismiss(mContext);
                        if (resp.getCode().equals("200")) {
                            toShenQing(resp.getMsg());
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

    private void toShenQing(String msg) {
        showShortToast(msg);
        finish();
    }

    @OnClick(R.id.Apply_DateEdit)
    public void onSelectDate() {
        //选择申请时间
        mTimerPicker.show(Apply_DateTxt.getText().toString());
    }

    @OnClick(R.id.Apply_GoodsNameEdit)
    public void onSelectGoodsName() {
//        Apply_GoodsNameTxt.setText("");
        CommSelectDialog dialog = new CommSelectDialog(mContext, R.style.MyDialogStyle, 1);
        dialog.setItemClickListener(date -> {
            Apply_GoodsNameTxt.setText(date);
            dialog.dismiss();
        });
        dialog.show();
    }

    @OnClick(R.id.shenPiUserTxt)
    public void onSelectShenP() {
        CommSelectDialog dialog = new CommSelectDialog(mContext, R.style.MyDialogStyle, 0);
        dialog.setItemClickListener(date -> {
            shenPiUserTxt.setText(date);
            dialog.dismiss();
        });
        dialog.show();
    }

    @OnClick(R.id.ChaoSongUserTxt)
    public void onSelectChaoSong() {
        CommSelectDialog dialog = new CommSelectDialog(mContext, R.style.MyDialogStyle, 0);
        dialog.setItemClickListener(date -> {
            ChaoSongUserTxt.setText(date);
            dialog.dismiss();
        });
        dialog.show();
    }

    private boolean isChecked(String goodName, String num, String chaoSong, String shenPi) {
        boolean b;
        if (TextUtils.isEmpty(goodName)) {
            showShortToast("请填写物品名称");
            b = false;
        } else if (TextUtils.isEmpty(num)) {
            showShortToast("请填写申领数量");
            b = false;
        } else if (TextUtils.isEmpty(chaoSong)) {
            showShortToast("请选择抄送人");
            b = false;
        } else if (TextUtils.isEmpty(shenPi)) {
            showShortToast("请选择审批人");
            b = false;
        } else {
            b = true;
        }

        return b;
    }

    @Override
    protected void onLeftClick() {
        super.onLeftClick();
        finish();
    }
}
