package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.TwoBEAN;

import java.util.List;

public class ZhishiActivityAdapter extends RecyclerView.Adapter<ZhishiActivityAdapter.Mywang> {

    Context context;
    List<TwoBEAN.DataBean.DatasBean> list;
    OnClickListener onclicklist;
    private TextView zhishiActivityItemTitle;
    private TextView zhishiActivityItemHou;
    private TextView zhishiActivityItemContext;
    private LinearLayout itemSearchTagGroup;
    private ImageView homePageItemlistLike;
    private TextView zhishiActivityItemTim;


    public void setOnclicklist(OnClickListener onclicklist) {
        this.onclicklist = onclicklist;
    }

    public ZhishiActivityAdapter(Context context, List<TwoBEAN.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Mywang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.zhishi_activity_item, parent, false);
        return new Mywang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Mywang holder, final int position) {

        holder.zhishiActivityItemTitle.setText(list.get(position).getAuthor());
        holder.zhishiActivityItemHou.setText(list.get(position).getSuperChapterName() + "/" + list.get(position).getChapterName());
        holder.zhishiActivityItemContext.setText(list.get(position).getTitle());
        holder.zhishiActivityItemTim.setText(list.get(position).getNiceDate());



        holder.itemSearchPagerGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclicklist.onclickshow(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setDate(List<TwoBEAN.DataBean.DatasBean> datas) {
        this.list.addAll(datas);
        notifyDataSetChanged();
    }


    class Mywang extends RecyclerView.ViewHolder {
        private TextView zhishiActivityItemTitle;
        private TextView zhishiActivityItemHou;
        private TextView zhishiActivityItemContext;
        private TextView zhishiActivityItemTim;
        private LinearLayout itemSearchPagerGroup;

        public Mywang(View itemView) {
            super(itemView);
            itemSearchPagerGroup = itemView.findViewById(R.id.item_search_pager_group);
            zhishiActivityItemTitle = itemView.findViewById(R.id.zhishi_activity_item_title);
            zhishiActivityItemHou = itemView.findViewById(R.id.zhishi_activity_item_hou);
            zhishiActivityItemContext = itemView.findViewById(R.id.zhishi_activity_item_context);
            zhishiActivityItemTim = itemView.findViewById(R.id.zhishi_activity_item_tim);
        }
    }


    public interface OnClickListener {
        void onclickshow(int i);
    }
}
