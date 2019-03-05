package com.jy.theplayandroid.playandroid.playandroid.daohang.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.ChuangYongBean;

import org.w3c.dom.Text;

import java.util.List;

public class ChuangyongAdapter extends RecyclerView.Adapter<ChuangyongAdapter.Mywang> {

    Context context;
    List<ChuangYongBean.DataBean> list;

    public ChuangyongAdapter(Context context, List<ChuangYongBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ChuangyongAdapter.Mywang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.layout,parent,false);
        return new Mywang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChuangyongAdapter.Mywang holder, int position) {
        holder.text.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Mywang extends RecyclerView.ViewHolder{

        TextView text;
        public Mywang(View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.text3);
        }
    }
}
