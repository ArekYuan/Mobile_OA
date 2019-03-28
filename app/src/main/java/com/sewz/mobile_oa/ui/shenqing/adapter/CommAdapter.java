package com.sewz.mobile_oa.ui.shenqing.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.shenqing.resp.CommResp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuan on 2019/3/24.
 */

public class CommAdapter extends RecyclerView.Adapter<CommAdapter.ViewHolder> implements View.OnClickListener {

    private Context mContext;
    private List<CommResp> data = new ArrayList<>();
    private LayoutInflater inflater;

    public CommAdapter(Context mContext) {
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    public List<CommResp> getData() {
        return data;
    }

    public void setData(List<CommResp> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    private OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.comm_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        CommResp resp = data.get(position);
        holder.iconIv.setImageResource(resp.getResId());
        holder.titleTxt.setText(resp.getTitle());
        holder.subTitleTxt.setText(resp.getSubTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View view) {
        if (itemClickListener != null) {
            itemClickListener.onItemClick((Integer) view.getTag());
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iconIv;
        TextView titleTxt;
        TextView subTitleTxt;

        public ViewHolder(View itemView) {
            super(itemView);
            iconIv = itemView.findViewById(R.id.iconIv);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            subTitleTxt = itemView.findViewById(R.id.subTitleTxt);
        }
    }

}
