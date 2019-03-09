package com.jy.theplayandroid.playandroid.playandroid.daohang.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.bean.Bean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleritemAdapter extends RecyclerView.Adapter {
    public ArrayList<Bean> mStrings;
    Context mContext;
    private int mPosi;

    public RecycleritemAdapter(ArrayList<Bean> strings, Context context) {
        mStrings = strings;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lv, parent, false);
        return new ViewHolder(inflate);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.setIsRecyclable(false);
        mStrings.get(position).setSelect(false);
        viewHolder.tvNodeTitle.setText(mStrings.get(position).getTitle());

        viewHolder.tvNodeTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onClick(v, position);
                }
            }
        });

        //6、判断改变属性
        if(mPosi==position){
            viewHolder.tvNodeTitle.setTextColor(Color.parseColor("#FF36BC9B"));
            viewHolder.tvNodeTitle.setBackgroundColor(Color.parseColor("#ffffff"));
        }else{
            viewHolder.tvNodeTitle.setBackgroundColor(Color.parseColor("#f0f0f0"));
            viewHolder.tvNodeTitle.setTextColor(Color.parseColor("#FF757575"));
        }
    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    public void setColor(int position) {
        mPosi = position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_itemlv)
        TextView tvNodeTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onClick(View v, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
