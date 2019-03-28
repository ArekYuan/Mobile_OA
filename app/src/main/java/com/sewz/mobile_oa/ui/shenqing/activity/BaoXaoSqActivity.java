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

public class BaoXaoSqActivity extends BaseActivity {

    @BindView(R.id.workNameTxt)
    TextView workNameTxt;

    @BindView(R.id.applyPersonTxt)
    TextView applyPersonTxt;

    @BindView(R.id.applyReasonEdit)
    EditText applyReasonEdit;

    @BindView(R.id.airEdit)
    EditText airEdit;

    @BindView(R.id.TrainFeeEdit)
    EditText TrainFeeEdit;

    @BindView(R.id.CarFeeFeeEdit)
    EditText CarFeeFeeEdit;

    @BindView(R.id.HostelFeeEdit)
    EditText HostelFeeEdit;

    @BindView(R.id.BusinessFeeEdit)
    EditText BusinessFeeEdit;

    @BindView(R.id.OfficeFeeEdit)
    EditText OfficeFeeEdit;

    @BindView(R.id.WelfareFeeEdit)
    EditText WelfareFeeEdit;

    @BindView(R.id.MaterielFeeEdit)
    EditText MaterielFeeEdit;

    @BindView(R.id.TravelFeeEdit)
    EditText TravelFeeEdit;

    @BindView(R.id.ExpressFeeEdit)
    EditText ExpressFeeEdit;

    @BindView(R.id.OtherFeeEdit)
    EditText OtherFeeEdit;

    @BindView(R.id.AdvertFeeEdit)
    EditText AdvertFeeEdit;

    @BindView(R.id.DevelopFeeEdit)
    EditText DevelopFeeEdit;

    @BindView(R.id.ServiceFeeEdit)
    EditText ServiceFeeEdit;

    @BindView(R.id.TrafficFeeEdit)
    EditText TrafficFeeEdit;

    @BindView(R.id.TotalFeeEdit)
    EditText TotalFeeEdit;

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
        return R.layout.activity_bao_xao_sq;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitleTxt("费用报销申请");
        setLeftImgBg(R.mipmap.back_white);
        flag = getIntent().getIntExtra("flag", -1);
        dealType_ = getIntent().getStringExtra("dealType");
        workNameTxt.setText(Global.getInstance().getUserName() + "-费用报销申请(" + dateToStamp(System.currentTimeMillis()) + ")");
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
        String reason = applyReasonEdit.getText().toString();
        String airFee = airEdit.getText().toString();
        String TrainFee = TrainFeeEdit.getText().toString();
        String CarFeeFee = CarFeeFeeEdit.getText().toString();
        String HostelFee = HostelFeeEdit.getText().toString();
        String BusinessFee = BusinessFeeEdit.getText().toString();
        String OfficeFee = OfficeFeeEdit.getText().toString();
        String WelfareFee = WelfareFeeEdit.getText().toString();
        String MaterielFee = MaterielFeeEdit.getText().toString();
        String TravelFee = TravelFeeEdit.getText().toString();
        String ExpressFee = ExpressFeeEdit.getText().toString();
        String OtherFee = OtherFeeEdit.getText().toString();
        String AdvertFee = AdvertFeeEdit.getText().toString();
        String DevelopFee = DevelopFeeEdit.getText().toString();
        String ServiceFee = ServiceFeeEdit.getText().toString();
        String TrafficFee = TrafficFeeEdit.getText().toString();
        String TotalFee = TotalFeeEdit.getText().toString();
        String chaoSong = ChaoSongUserTxt.getText().toString();
        String shenPi = shenPiUserTxt.getText().toString();

        if (isChecked(reason, TotalFee, chaoSong, shenPi)) {
            doCommit(time, workName, userName,
                    reason, airFee, TrainFee,
                    CarFeeFee, HostelFee, BusinessFee,
                    OfficeFee, WelfareFee, MaterielFee,
                    TravelFee, ExpressFee, OtherFee,
                    AdvertFee, DevelopFee, ServiceFee,
                    TrafficFee, TotalFee, chaoSong,
                    shenPi, dealType, fuJianStr);
        }

    }

    private void doCommit(String time, String workName, String userName,
                          String reason, String airFee, String trainFee,
                          String carFeeFee, String hostelFee, String businessFee,
                          String officeFee, String welfareFee, String materielFee,
                          String travelFee, String expressFee, String otherFee,
                          String advertFee, String developFee, String serviceFee,
                          String trafficFee, String totalFee, String chaoSong,
                          String shenPi, String dealType, String fuJianStr) {
        //请求接口 增加申请
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
            reqParams.put("Caurse", reason);
            reqParams.put("AirportFee", airFee);
            reqParams.put("TrainFee", trainFee);
            reqParams.put("CarFee", carFeeFee);
            reqParams.put("HostelFee", hostelFee);
            reqParams.put("BusinessFee", businessFee);
            reqParams.put("OfficeFee", officeFee);
            reqParams.put("WelfareFee", welfareFee);
            reqParams.put("MaterielFee", materielFee);
            reqParams.put("TravelFee", travelFee);
            reqParams.put("ExpressFee", expressFee);
            reqParams.put("OtherFee", otherFee);
            reqParams.put("AdvertFee", advertFee);
            reqParams.put("DevelopFee", developFee);
            reqParams.put("ServiceFee", serviceFee);
            reqParams.put("TrafficFee", trafficFee);
            reqParams.put("TotalFee", totalFee);

            Mobile_Api.getInstance().postData(UrlUtils.SAVE_BAOXAO, reqParams, new Callback() {
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

    @OnClick(R.id.shenPiUserTxt)
    public void onSelectShenP() {
//        shenPiUserTxt.setText("");
        CommSelectDialog dialog = new CommSelectDialog(mContext, R.style.MyDialogStyle, 0);
        dialog.setItemClickListener(date -> {
            shenPiUserTxt.setText(date);
            dialog.dismiss();
        });
        dialog.show();
    }

    @OnClick(R.id.ChaoSongUserTxt)
    public void onSelectChaoSong() {
//        ChaoSongUserTxt.setText("");
        CommSelectDialog dialog = new CommSelectDialog(mContext, R.style.MyDialogStyle, 0);
        dialog.setItemClickListener(date -> {
            ChaoSongUserTxt.setText(date);
            dialog.dismiss();
        });
        dialog.show();
    }

    private boolean isChecked(String reason, String totalFee, String chaoSong, String shenPi) {
        boolean b;
        if (TextUtils.isEmpty(reason)) {
            showShortToast("请输入申请事由");
            b = false;
        } else if (TextUtils.isEmpty(totalFee)) {
            showShortToast("请输入合计费用");
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
