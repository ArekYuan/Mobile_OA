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

public class RepairSqActivity extends BaseActivity {

    @BindView(R.id.workNameTxt)
    TextView workNameTxt;

    @BindView(R.id.applyPersonTxt)
    TextView applyPersonTxt;

    @BindView(R.id.Apply_GoodsNameEdit)
    TextView Apply_GoodsNameEdit;

    @BindView(R.id.Apply_DateEdit)
    TextView Apply_DateEdit;

    @BindView(R.id.Apply_PhoneEdit)
    EditText Apply_PhoneEdit;

    @BindView(R.id.Repair_Fault_DescEdit)
    EditText Repair_Fault_DescEdit;//故障描述

    @BindView(R.id.Repair_Fault_LocationEdit)
    EditText Repair_Fault_LocationEdit;//故障地点

    @BindView(R.id.Repair_FeeEdit)
    EditText Repair_FeeEdit;//

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
    private int flag = -1;
    private String dealType_ = "0";


    @Override
    protected int getContentViewId() {
        return R.layout.activity_repair_sq;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitleTxt("物品维修申请");
        setLeftImgBg(R.mipmap.back_white);
        flag = getIntent().getIntExtra("flag", -1);
        dealType_ = getIntent().getStringExtra("dealType");
        workNameTxt.setText(Global.getInstance().getUserName() + "-物品维修申请(" + dateToStamp(System.currentTimeMillis()) + ")");
        applyPersonTxt.setText(Global.getInstance().getUserName());

        initAdapter();
        initSubmit();
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

        String Apply_GoodsName = Apply_GoodsNameEdit.getText().toString();
        String Apply_Date = Apply_DateEdit.getText().toString();
        String Apply_Phone = Apply_PhoneEdit.getText().toString();
        String Repair_Desc = Repair_Fault_DescEdit.getText().toString();
        String Repair_Location = Repair_Fault_LocationEdit.getText().toString();
        String Repair_Fee = Repair_FeeEdit.getText().toString();

        String shenPiUser = shenPiUserTxt.getText().toString();
        String ChaoSongUser = ChaoSongUserTxt.getText().toString();

        if (isChecked(Apply_GoodsName, Apply_Date,
                Apply_Phone, Repair_Desc,
                Repair_Location, Repair_Fee,
                shenPiUser, ChaoSongUser)) {
            doCommit(time, workName, userName,
                    Apply_GoodsName, Apply_Date,
                    Apply_Phone, Repair_Desc,
                    Repair_Location, Repair_Fee,
                    shenPiUser, ChaoSongUser,
                    dealType, fuJianStr);
        }

    }

    private void doCommit(String time, String workName,
                          String userName, String apply_goodsName,
                          String apply_date, String apply_phone,
                          String repair_desc, String repair_location,
                          String repair_fee, String shenPiUser,
                          String chaoSongUser, String dealType,
                          String fuJianStr) {

        //网络请求
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
            reqParams.put("ShenPiUserList", shenPiUser);
            reqParams.put("ChaoSongUserList", chaoSongUser);
            reqParams.put("DealState", dealType);

            reqParams.put("Repair_GoodsName", apply_goodsName);
            reqParams.put("Repair_Department", apply_date);
            reqParams.put("Repair_Phone", apply_phone);
            reqParams.put("Repair_Fault_Desc", repair_desc);
            reqParams.put("Repair_Fault_Location", repair_location);
            reqParams.put("Repair_Fee", repair_fee);

            Mobile_Api.getInstance().postData(UrlUtils.SAVE_REPAIR, reqParams, new Callback() {
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

    @OnClick(R.id.Apply_GoodsNameEdit)
    public void onSelectGoods() {
        CommSelectDialog dialog = new CommSelectDialog(mContext, R.style.MyDialogStyle, 1);
        dialog.setItemClickListener(date -> {
            Apply_GoodsNameEdit.setText(date);
            dialog.dismiss();
        });
        dialog.show();
    }

    @OnClick(R.id.Apply_DateEdit)
    public void onSelectDepart() {
        CommSelectDialog dialog = new CommSelectDialog(mContext, R.style.MyDialogStyle, 2);
        dialog.setItemClickListener(date -> {
            Apply_DateEdit.setText(date);
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

    private boolean isChecked(String apply_goodsName, String apply_date,
                              String apply_phone, String repair_desc,
                              String repair_location, String repair_fee,
                              String shenPiUser, String chaoSongUser) {
        boolean b;
        if (TextUtils.isEmpty(apply_goodsName)) {
            showShortToast("请选择物品名称");
            b = false;
        } else if (TextUtils.isEmpty(apply_date)) {
            showShortToast("请选择保修部门");
            b = false;
        } else if (TextUtils.isEmpty(apply_phone)) {
            showShortToast("请输入报修人电话");
            b = false;
        } else if (TextUtils.isEmpty(repair_desc)) {
            showShortToast("请输入维修描述");
            b = false;
        } else if (TextUtils.isEmpty(repair_location)) {
            showShortToast("请输入维修地点");
            b = false;
        } else if (TextUtils.isEmpty(repair_fee)) {
            showShortToast("请输入维修费用");
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
