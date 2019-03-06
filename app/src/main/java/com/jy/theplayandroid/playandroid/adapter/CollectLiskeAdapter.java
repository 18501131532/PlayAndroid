package com.jy.theplayandroid.playandroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.Favruite;

import java.util.List;

public class CollectLiskeAdapter extends RecyclerView.Adapter{
    public   List<Favruite.DataBean.DatasBean> mList;
    private  Context mContext;
    private OnItemClickListener mListener;

    public CollectLiskeAdapter(List<Favruite.DataBean.DatasBean> datas, Context context) {
        this.mList=datas;
        this.mContext=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(mContext).inflate(R.layout.collect_item, null, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MyViewHolder holder1= (MyViewHolder) holder;
        holder1.mName.setText(mList.get(position).getAuthor());
        holder1.mTitle.setText(mList.get(position).getTitle());
        holder1.mTime.setText(mList.get(position).getNiceDate()+"");
        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener!=null){
                    mListener.OnItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<Favruite.DataBean.DatasBean> datas) {
        this.mList.addAll(datas);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        private final TextView mName;
        private final TextView mTitle;
        private final ImageView mLike;
        private final TextView mTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.collect_name);
            mTitle = itemView.findViewById(R.id.collect_title);
            mLike = itemView.findViewById(R.id.collect_like);
            mTime = itemView.findViewById(R.id.collect_time);
        }
    }
    //条目点击事件
    public interface OnItemClickListener{
        void OnItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener=listener;
    }
}
