package com.jy.theplayandroid.playandroid.playandroid.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.playandroid.main.bean.ArticleListBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 段傅华 on 2019/2/28.
 */

public class MainRlvAdapter extends RecyclerView.Adapter{
    private  List<ArticleListBean.DataBean.DatasBean> mList;
    private  Context mContext;
    private  int TYPE_BANNER=0;
    private  int TYPE_LIST=1;
    public MainRlvAdapter(List<ArticleListBean.DataBean.DatasBean> datas, Context context) {
        this.mList=datas;
        this.mContext=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType==TYPE_BANNER){
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.home_page_itembanner, null, false);
                MyViewHolderB myViewHolder = new MyViewHolderB(inflate);
                return myViewHolder;
            }else {
                View inflate = LayoutInflater.from(mContext).inflate(R.layout.home_page_itemlist, null, false);
                MyViewHolder myViewHolder = new MyViewHolder(inflate);
                return myViewHolder;
            }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType==TYPE_BANNER){
            MyViewHolderB holderB= (MyViewHolderB) holder;
            ArrayList<Integer> integers = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                integers.add(R.mipmap.ic_navigation_bg);
            }
            holderB.mBanner.setImages(integers).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            }).start();
        }else {
            MyViewHolder holder1= (MyViewHolder) holder;
            holder1.mName.setText(mList.get(position).getAuthor());
            holder1.mTitle.setText(mList.get(position).getTitle());
            holder1.mType.setText(mList.get(position).getSuperChapterName()+"/"+mList.get(position).getChapterName());
            holder1.mTime.setText(mList.get(position).getNiceDate());
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<ArticleListBean.DataBean.DatasBean> datas) {
        this.mList.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return TYPE_BANNER;
        }else {
            return TYPE_LIST;
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        final TextView mName;
        final TextView mType;
        final TextView mTitle;
        final TextView mTime;

        public MyViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.home_page_itemlist_name);
            mType = itemView.findViewById(R.id.home_page_itemlist_type);
            mTitle = itemView.findViewById(R.id.home_page_itemlist_title);
            mTime = itemView.findViewById(R.id.home_page_itemlist_time);

        }
    }
    class MyViewHolderB extends RecyclerView.ViewHolder{

        final Banner mBanner;

        public MyViewHolderB(View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.home_page_itembanner_banner);
        }
    }
}
