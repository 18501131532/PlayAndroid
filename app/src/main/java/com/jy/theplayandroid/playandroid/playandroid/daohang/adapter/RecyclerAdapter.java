package com.jy.theplayandroid.playandroid.playandroid.daohang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.Bean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.bean.JsonBean;
import com.jy.theplayandroid.playandroid.playandroid.daohang.utils.FlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecyclerAdapter extends RecyclerView.Adapter {
    public ArrayList<Bean> mStrings;
    public ArrayList<JsonBean.DataBean> mDataBeans;
    Context mContext;

    public RecyclerAdapter(ArrayList<Bean> strings, ArrayList<JsonBean.DataBean> dataBeans, Context context) {
        mStrings = strings;
        mDataBeans = dataBeans;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_node, null, false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setIsRecyclable(false);
        viewHolder.tvNodeTitle.setText(mStrings.get(position).getTitle());
        List<JsonBean.DataBean.ArticlesBean> articles = mDataBeans.get(position).getArticles();
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        for (int i = 0; i < articles.size(); i++) {
            articles.get(i).setBool(false);
            Random myRandom = new Random();
            int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);

//            TextView tv = new TextView(mContext);
//            tv.setText(articles.get(i).getTitle());
//            tv.setLayoutParams(layoutParams);
//            tv.setLineSpacing(1.2f,1.2f);//行间距
//            tv.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
//            tv.setTextColor(ranColor);
//            tv.setPadding(10,10,10,10);

            TextView tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.layout, viewHolder.flNodeContent, false);
            tv.setText(articles.get(i).getTitle());
            tv.setTextColor(ranColor);
            viewHolder.flNodeContent.addView(tv);

            final int finalI = i;
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener!=null){
                        mOnItemClickListener.onClick(v,position, finalI);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_node_title)
        TextView tvNodeTitle;
        @BindView(R.id.fl_node_content)
        FlowLayout flNodeContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(View v, int position,int art_position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
