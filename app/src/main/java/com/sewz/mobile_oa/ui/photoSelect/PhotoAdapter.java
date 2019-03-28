package com.sewz.mobile_oa.ui.photoSelect;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sewz.mobile_oa.R;

import java.util.ArrayList;
import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.ViewHolder> {

    private static int TYPE_ADD = 0;//添加图片
    private static int TYPE_COMMON = 1;//普通图片展示

    private int mMaxAlbum;//最大选择图片的数量
    private Context mContext;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    private List<String> data = new ArrayList<>();
    private LayoutInflater mInflater;

    public PhotoAdapter(Context mContext, int mMaxAlbum) {
        this.mContext = mContext;
        this.mMaxAlbum = mMaxAlbum;
        mInflater = LayoutInflater.from(mContext);
    }

    public void setClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void setShowListener(OnItemPhotoShowListener showListener) {
        this.showListener = showListener;
    }

    private OnItemClickListener clickListener;


    private OnItemPhotoShowListener showListener;

    public interface OnItemClickListener {
        void onClick(int position);

        void onDeleteClick(int position, List<String> data);
    }

    public interface OnItemPhotoShowListener {
        void onClick(int position, List<String> picList);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.photo_select_item_layout, parent, false);
        ViewHolder holder = new ViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (data != null && position < data.size()) {
            String url = data.get(position);
            if (url.contains("/")) {
                Glide.with(mContext).load(url).into(holder.addImg);
            } else {
                Glide.with(mContext).load(url)
                        .centerCrop()
                        .skipMemoryCache(false)
                        .placeholder(R.mipmap.ic_launcher).dontAnimate().into(holder.addImg);
            }

            holder.deleteImg.setVisibility(View.VISIBLE);
            holder.deleteImg.setOnClickListener(view -> {
                clickListener.onDeleteClick(position, data);
            });
            holder.addImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (showListener != null) {
                        showListener.onClick(position, data);
                    }
                }
            });

        } else {
            holder.deleteImg.setVisibility(View.GONE);
            holder.addImg.setImageResource(R.mipmap.icon_add_photo_1);
            holder.addImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickListener != null) {
                        clickListener.onClick(position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int count = data == null ? 1 : data.size() + 1;
        if (count > mMaxAlbum) {
            return data.size();
        } else {
            return count;
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView addImg;
        private ImageView deleteImg;

        public ViewHolder(View itemView) {
            super(itemView);
            addImg = itemView.findViewById(R.id.addPhotoImg);
            deleteImg = itemView.findViewById(R.id.deleteImg);
        }
    }


}
