package com.sewz.mobile_oa.ui.shenqing.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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

public class PuchaseSqActivity extends BaseActivity {

    @BindView(R.id.workNameTxt)
    TextView workNameTxt;

    @BindView(R.id.applyPersonTxt)
    TextView applyPersonTxt;

    @BindView(R.id.P_GoodsNameEdit)
    TextView P_GoodsNameEdit;

    @BindView(R.id.P_NumEdit)
    EditText P_NumEdit;

    @BindView(R.id.P_BudgetEdit)
    EditText P_BudgetEdit;//预算

    @BindView(R.id.P_DepartmentEdit)
    TextView P_DepartmentEdit;

    @BindView(R.id.P_RemarkEdit)
    EditText P_RemarkEdit;

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
        return R.layout.activity_puchase_sq;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setTitleTxt("物品采购申请");
        setLeftImgBg(R.mipmap.back_white);
        flag = getIntent().getIntExtra("flag", -1);
        dealType_ = getIntent().getStringExtra("dealType");
        workNameTxt.setText(Global.getInstance().getUserName() + "-物品采购申请(" + dateToStamp(System.currentTimeMillis()) + ")");
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

        String P_GoodsName = P_GoodsNameEdit.getText().toString();
        String P_Num = P_NumEdit.getText().toString();
        String P_Budget = P_BudgetEdit.getText().toString();
        String P_Department = P_DepartmentEdit.getText().toString();
        String P_Remark = P_RemarkEdit.getText().toString();

        String shenPiUser = shenPiUserTxt.getText().toString();
        String ChaoSongUser = ChaoSongUserTxt.getText().toString();

        if (isChecked(P_GoodsName, P_Num, P_Budget, P_Department, shenPiUser, ChaoSongUser)) {
            doCommit(time, workName, userName,
                    P_GoodsName, P_Num, P_Budget,
                    P_Department, shenPiUser, ChaoSongUser,
                    P_Remark, dealType, fuJianStr);
        }
    }

    private void doCommit(String time, String workName,
                          String userName, String p_goodsName,
                          String p_num, String p_budget,
                          String p_department, String shenPiUser,
                          String chaoSongUser, String p_remark,
                          String dealType, String fuJianStr) {
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


            reqParams.put("P_GoodsName", p_goodsName);
            reqParams.put("P_Num", p_num);
            reqParams.put("P_Budget", p_budget);
            reqParams.put("P_Department", p_department);
            reqParams.put("P_Remark", p_remark);

            Mobile_Api.getInstance().postData(UrlUtils.SAVE_PURCHASE, reqParams, new Callback() {
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

    @OnClick(R.id.P_DepartmentEdit)
    public void onSelectDepart() {
//        P_DepartmentEdit.setText("");
        CommSelectDialog dialog = new CommSelectDialog(mContext, R.style.MyDialogStyle, 2);
        dialog.setItemClickListener(date -> {
            P_DepartmentEdit.setText(date);
            dialog.dismiss();
        });
        dialog.show();
    }

    @OnClick(R.id.P_GoodsNameEdit)
    public void onSelectGoods() {
        P_GoodsNameEdit.setText("");
        CommSelectDialog dialog = new CommSelectDialog(mContext, R.style.MyDialogStyle, 1);
        dialog.setItemClickListener(date -> {
            P_GoodsNameEdit.setText(date);
            dialog.dismiss();
        });
        dialog.show();
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

    private boolean isChecked(String p_goodsName, String p_num,
                              String p_budget, String p_department,
                              String shenPiUser, String chaoSongUser) {

        boolean b;
        if (TextUtils.isEmpty(p_goodsName)) {
            showShortToast("请选择物品名称");
            b = false;
        } else if (TextUtils.isEmpty(p_num)) {
            showShortToast("请输入采购数量");
            b = false;
        } else if (TextUtils.isEmpty(p_budget)) {
            showShortToast("请输入采购预算");
            b = false;
        } else if (TextUtils.isEmpty(p_department)) {
            showShortToast("请选择采购部门");
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
