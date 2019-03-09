package com.jy.theplayandroid.playandroid.playandroid.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.playandroid.main.activity.BannerDetailsActivity;
import com.jy.theplayandroid.playandroid.bean.ArticleBannerBean;
import com.jy.theplayandroid.playandroid.bean.ArticleListBean;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 段傅华 on 2019/2/28.
 */

public class MainRlvAdapter extends RecyclerView.Adapter{
    public   ArrayList<ArticleBannerBean.DataBean> mBanner;
    public   List<ArticleListBean.DataBean.DatasBean> mList;
    private  Context mContext;
    private  int TYPE_BANNER=0;
    private  int TYPE_LIST=1;
    private OnItemClickListener mListener;
    public ArrayList<String> mIntegers;

    public MainRlvAdapter(List<ArticleListBean.DataBean.DatasBean> datas, ArrayList<ArticleBannerBean.DataBean> banner, Context context) {
        this.mList=datas;
        this.mContext=context;
        this.mBanner=banner;
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
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int viewType = getItemViewType(position);
        if (viewType==TYPE_BANNER){
            final MyViewHolderB holderB= (MyViewHolderB) holder;
            mIntegers = new ArrayList<>();
            if (mIntegers.size()==0) {
                for (int i = 0; i < mBanner.size(); i++) {
                    mIntegers.add(mBanner.get(i).getImagePath());
                }
            }else {
//                mIntegers.clear();
                for (int i = 0; i < mBanner.size(); i++) {
                    mIntegers.add(mBanner.get(i).getImagePath());
                }
            }

            holderB.mBanner.setImages(mIntegers).setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                    Glide.with(context).load(path).into(imageView);
                }
            }).start();
            holderB.mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {

                       holderB.mTitle.setText(mBanner.get(position).getTitle());
                       holderB.mPosition.setText((position+1)+"/"+ mIntegers.size());

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
            holderB.mBanner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    Intent intent = new Intent(mContext, BannerDetailsActivity.class);
                  intent.putExtra("titles",mBanner.get(position).getTitle());
                  intent.putExtra("urls",mBanner.get(position).getUrl());
                  mContext.startActivity(intent);
                }
            });
        }else {
            MyViewHolder holder1= (MyViewHolder) holder;
            if (mList.get(position).getAuthor()!=null){
                holder1.mName.setText(mList.get(position).getAuthor()+"");
                holder1.mTitle.setText(mList.get(position).getTitle());
                holder1.mType.setText(mList.get(position).getSuperChapterName()+"/"+mList.get(position).getChapterName());
                holder1.mTime.setText(mList.get(position).getNiceDate());
            }

            //判断是否为新
            boolean fresh = mList.get(position).isFresh();
            if (fresh){
                holder1.mNews.setVisibility(View.VISIBLE);
            }else {
                holder1.mNews.setVisibility(View.GONE);
            }


            //判断是否为项目
            List<ArticleListBean.DataBean.DatasBean.TagsBean> tags = mList.get(position).getTags();

            if(tags!=null&&tags.size()!=0){
                if(tags.get(0).getName().equals("项目")){
                    holder1.mItem.setVisibility(View.VISIBLE);
                }else{
                    holder1.mItem.setVisibility(View.GONE);
                }
            }
            holder1.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener!=null){
                        mListener.OnItemClick(position,v);
                    }
                }
            });
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

    public void addList(List<ArticleBannerBean.DataBean> data) {
        mBanner.clear();
        mBanner.addAll(data);
        notifyDataSetChanged();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        final TextView mName;
        final TextView mType;
        final TextView mTitle;
        final TextView mTime;
        final TextView mItem;
        final TextView mNews;

        public MyViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.home_page_itemlist_name);
            mType = itemView.findViewById(R.id.home_page_itemlist_type);
            mTitle = itemView.findViewById(R.id.home_page_itemlist_title);
            mTime = itemView.findViewById(R.id.home_page_itemlist_time);
            mItem = itemView.findViewById(R.id.home_page_itemlist_item);
            mNews = itemView.findViewById(R.id.home_page_itemlist_new);

        }
    }
    class MyViewHolderB extends RecyclerView.ViewHolder{

        final Banner mBanner;
        final TextView mTitle;
        final TextView mPosition;

        public MyViewHolderB(View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.home_page_itembanner_banner);
            mTitle = itemView.findViewById(R.id.banner_title);
            mPosition = itemView.findViewById(R.id.banner_position);
        }
    }
    //点击事件回调
    public interface OnItemClickListener{
        void OnItemClick(int position, View view);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener=listener;
    }
}
