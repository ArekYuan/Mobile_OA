package com.sewz.mobile_oa.utils.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.sewz.mobile_oa.R;

/**
 * Created by Yuan on 2019/3/23.
 */

public class SelectDialog extends Dialog {

    private String[] year = new String[]{"2019", "2018", "2017", "2016", "2015"};
    private String[] month = new String[]{"所有月份", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};

    private int type;//0->年份，1->月份
    private ListView selectView;
    private SelectAdapter selectAdapter;
    private Context mContext;

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String date);
    }


    public SelectDialog(@NonNull Context context) {
        super(context);
    }

    public SelectDialog(@NonNull Context context, int themeResId, int type) {
        super(context, themeResId);
        this.type = type;
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_select_layout);
        selectView = findViewById(R.id.selectView);
        initAdapter();
        setCanceledOnTouchOutside(false);
    }

    private void initAdapter() {
        if (type == 0) {//年份
            selectAdapter = new SelectAdapter(mContext, year);
        } else if (type == 1) {//月份
            selectAdapter = new SelectAdapter(mContext, month);
        }
        selectView.setAdapter(selectAdapter);

        selectView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (itemClickListener != null) {
                    itemClickListener.onItemClick(selectAdapter.getData()[i]);
                }
            }
        });
    }
}
