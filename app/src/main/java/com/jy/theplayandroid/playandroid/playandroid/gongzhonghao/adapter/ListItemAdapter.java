package com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.playandroid.gongzhonghao.bean.FeedArticleListData;

import java.util.ArrayList;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.ViewHolder> {
    private Context context;
    private ArrayList<FeedArticleListData.DataBean.DatasBean> list;

    public ListItemAdapter(Context context, ArrayList<FeedArticleListData.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.gongzhonghaoitem,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        FeedArticleListData.DataBean.DatasBean bean = list.get(position);
        holder.name.setText(bean.getAuthor());
        holder.tag.setText(bean.getSuperChapterName()+"/"+bean.getChapterName());
        holder.content.setText(bean.getTitle()+"                                                                              ");
        holder.date.setText(bean.getNiceDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclickItem!=null){
                    onclickItem.onclickItem(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public OnclickItemJumpDetails onclickItem;

    public void setOnclickItem(OnclickItemJumpDetails onclickItem) {
        this.onclickItem = onclickItem;
    }

    public interface OnclickItemJumpDetails{
        void onclickItem(int position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name,tag,content,date;
        public ViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            tag=itemView.findViewById(R.id.tag);
            content=itemView.findViewById(R.id.content);
            date=itemView.findViewById(R.id.date);
        }
    }
}
