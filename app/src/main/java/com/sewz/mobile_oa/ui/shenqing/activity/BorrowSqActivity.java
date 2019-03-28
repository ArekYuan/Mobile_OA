package com.sewz.mobile_oa.ui.shenqing.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
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

public class BorrowSqActivity extends BaseActivity {


    @BindView(R.id.workNameTxt)
    TextView workNameTxt;

    @BindView(R.id.applyPersonTxt)
    TextView applyPersonTxt;

    @BindView(R.id.Borrow_GoodsNameEdit)
    TextView Borrow_GoodsNameTxt;

    @BindView(R.id.Borrow_NumEdit)
    EditText Borrow_NumEdit;

    @BindView(R.id.Borrow_StartTimeTxt)
    TextView Borrow_StartTimeTxt;

    @BindView(R.id.Borrow_EndTimeTxt)
    TextView Borrow_EndTimeTxt;

    @BindView(R.id.Borrow_RemarkEdit)
    EditText Borrow_RemarkEdit;

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

    private String dealType = "0";
    private String fuJianStr = "";
    private CustomDatePicker mDatePicker, mTimerPicker;
    private int flag = -1;
    private String dealType_ = "0";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_borrow_sq;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitleTxt("物品借用申请");
        setLeftImgBg(R.mipmap.back_white);
        flag = getIntent().getIntExtra("flag", -1);
        dealType_ = getIntent().getStringExtra("dealType");

        workNameTxt.setText(Global.getInstance().getUserName() + "-物品借用申请(" + dateToStamp(System.currentTimeMillis()) + ")");
        applyPersonTxt.setText(Global.getInstance().getUserName());
        initDatePicker();
        initTimerPicker();
        initAdapter();
        initSubmit();
    }

    private void initTimerPicker() {
        String beginTime = toDate(System.currentTimeMillis());
        String endTime = "2020-03-26 17:00";
//        Borrow_EndTimeTxt.setText(endTime);
        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                Borrow_EndTimeTxt.setText(DateFormatUtils.long2Str(timestamp, true));
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

    private void initDatePicker() {
        String beginTime = toDate(System.currentTimeMillis());
        String endTime = "2020-03-26 17:00";

//        Borrow_StartTimeTxt.setText(beginTime);
        // 通过时间戳初始化日期，毫秒级别
        mDatePicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                Borrow_StartTimeTxt.setText(DateFormatUtils.long2Str(timestamp, false));
            }
        }, beginTime, endTime);
        // 不允许点击屏幕或物理返回键关闭
        mDatePicker.setCancelable(false);
        // 不显示时和分
        mDatePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        mDatePicker.setScrollLoop(false);
        // 不允许滚动动画
        mDatePicker.setCanShowAnim(false);
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
        String Borrow_GoodsName = Borrow_GoodsNameTxt.getText().toString();
        String Borrow_Num = Borrow_NumEdit.getText().toString();
        String Borrow_StartTime = Borrow_StartTimeTxt.getText().toString();
        String Borrow_EndTime = Borrow_EndTimeTxt.getText().toString();
        String Borrow_Remark = Borrow_RemarkEdit.getText().toString();
        String shenPiUser = shenPiUserTxt.getText().toString();
        String ChaoSongUser = ChaoSongUserTxt.getText().toString();

        if (isChecked(Borrow_GoodsName, Borrow_Num, shenPiUser, Borrow_StartTime, Borrow_EndTime, ChaoSongUser)) {
            doCommit(time, workName,
                    userName, Borrow_GoodsName,
                    Borrow_Num, shenPiUser,
                    Borrow_StartTime, Borrow_EndTime,
                    ChaoSongUser, Borrow_Remark,
                    dealType, fuJianStr);
        }

    }

    private void doCommit(String time, String workName,
                          String userName, String borrow_goodsName,
                          String borrow_num, String shenPiUser,
                          String borrow_startTime, String borrow_endTime,
                          String chaoSongUser, String borrow_remark,
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
            reqParams.put("DealState", dealType);
            reqParams.put("ShenPiUserList", shenPiUser);
            reqParams.put("ChaoSongUserList", chaoSongUser);
            reqParams.put("Borrow_GoodsName", borrow_goodsName);
            reqParams.put("Borrow_Num", borrow_num);
            reqParams.put("Borrow_StartTime", borrow_startTime);
            reqParams.put("Borrow_EndTime", borrow_endTime);
            reqParams.put("Borrow_Remark", borrow_remark);

            Mobile_Api.getInstance().postData(UrlUtils.SAVE_BORROW, reqParams, new Callback() {
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

    @OnClick(R.id.Borrow_StartTimeTxt)
    public void onSelectStart() {
        //选择开始时间
        mDatePicker.show(Borrow_StartTimeTxt.getText().toString());
    }

    @OnClick(R.id.Borrow_EndTimeTxt)
    public void onSelectEnd() {
        //归还日期
        mTimerPicker.show(Borrow_EndTimeTxt.getText().toString());
    }

    @OnClick(R.id.Borrow_GoodsNameEdit)
    public void onSelectBorrow() {
        CommSelectDialog dialog = new CommSelectDialog(mContext, R.style.MyDialogStyle, 1);
        dialog.setItemClickListener(date -> {
            Borrow_GoodsNameTxt.setText(date);
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

    private boolean isChecked(String borrow_goodsName, String borrow_num,
                              String shenPiUser, String borrow_startTime,
                              String borrow_endTime, String chaoSongUser) {
        boolean b;
        if (TextUtils.isEmpty(borrow_goodsName)) {
            showShortToast("请选择物品名称");
            b = false;
        } else if (TextUtils.isEmpty(borrow_num)) {
            showShortToast("请输入借用数量");
            b = false;
        } else if (TextUtils.isEmpty(borrow_startTime)) {
            showShortToast("请选择借用时间");
            b = false;
        } else if (TextUtils.isEmpty(borrow_endTime)) {
            showShortToast("请选择归还时间");
            b = false;
        } else if (TextUtils.isEmpty(shenPiUser)) {
            showShortToast("请选择审批人");
            b = false;
        } else if (TextUtils.isEmpty(chaoSongUser)) {
            showShortToast("请选择抄送人");
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
