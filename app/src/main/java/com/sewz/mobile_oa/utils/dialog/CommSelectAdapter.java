package com.sewz.mobile_oa.utils.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.sewz.mobile_oa.R;

/**
 * Created by Yuan on 2019/3/23.
 */

public class CommSelectAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private String[] data;
    private OnSelectListener onSelectListener;

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public String[] getData() {
        return data;
    }

    public CommSelectAdapter(Context mContext, String[] data) {
        this.mContext = mContext;
        this.data = null;
        this.data = data;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int i) {
        return data[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.comm_select_item_layout, null);
            holder = new ViewHolder(view);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.selectTxt.setText(data[i]);
        holder.selectTxt.setOnCheckedChangeListener((compoundButton, b) -> {
            if (onSelectListener != null) {
                onSelectListener.onSelect(data[i], b);
            }
        });
        return view;
    }

    public class ViewHolder {
        CheckBox selectTxt;

        public ViewHolder(View view) {
            selectTxt = view.findViewById(R.id.nameTxt);
            view.setTag(this);
        }
    }

    public interface OnSelectListener {
        void onSelect(String name, boolean b);
    }
}
