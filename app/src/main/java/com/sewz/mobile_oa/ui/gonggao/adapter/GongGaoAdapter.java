package com.sewz.mobile_oa.ui.gonggao.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sewz.mobile_oa.R;
import com.sewz.mobile_oa.ui.gonggao.resp.GongGaoResp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuan on 2019/3/23.
 */

public class GongGaoAdapter extends RecyclerView.Adapter<GongGaoAdapter.ViewHolder> implements View.OnClickListener {

    public List<GongGaoResp.DataBean> getData() {
        return data;
    }

    public void setRefreshData(List<GongGaoResp.DataBean> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void setLoadData(List<GongGaoResp.DataBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    private List<GongGaoResp.DataBean> data = new ArrayList<>();
    private Context mContext;
    private LayoutInflater mInflater;
    private int layoutId;

    public GongGaoAdapter(Context mContext, int layoutId) {
        this.mContext = mContext;
        this.layoutId = layoutId;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(layoutId, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.itemView.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GongGaoResp.DataBean bean = data.get(position);
        holder.titleNameTxt.setText(bean.getUserName());
        holder.titleTxt.setText(bean.getTitleStr());
        holder.gongGaoImgView.setImageResource(R.mipmap.icon_home_produce);
        holder.itemView.setTag(position);
//        holder.contentTxt.setText(bean.getContentStr());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView gongGaoImgView;
        TextView titleTxt;
        TextView titleNameTxt;
//        TextView contentTxt;

        public ViewHolder(View itemView) {
            super(itemView);
            gongGaoImgView = itemView.findViewById(R.id.gongGaoImgView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            titleNameTxt = itemView.findViewById(R.id.titleNameTxt);
//            contentTxt = itemView.findViewById(R.id.contentTxt);


        }
    }

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public void onClick(View view) {
        if (onItemClickListener != null) {
            onItemClickListener.onItemClick((Integer) view.getTag());
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
