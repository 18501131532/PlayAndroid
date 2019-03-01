package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.OneBean;

import java.util.List;

public class ZhishiAdapter extends RecyclerView.Adapter<ZhishiAdapter.Mywang> {
    Context context;
    List<OneBean.DataBean> list;


    public ZhishiAdapter(Context context, List<OneBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public Mywang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.zhishi_item, parent, false);
        return new Mywang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Mywang holder, int position) {
        holder.itemKnowledgeHierarchyTitle.setText(list.get(position).getName());
        StringBuilder bu=new StringBuilder();

        for(int i=0;i<list.get(position).getChildren().size();i++){
            bu.append(list.get(position).getChildren().get(i).getName()).append("    ");
            holder.itemKnowledgeHierarchyContent.setText(bu.toString());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class Mywang extends RecyclerView.ViewHolder {
        private ImageView knowledgeHierarchyEnterIv;
        private TextView itemKnowledgeHierarchyTitle;
        private TextView itemKnowledgeHierarchyContent;
        public Mywang(View itemView) {
            super(itemView);
            knowledgeHierarchyEnterIv =itemView.findViewById(R.id.knowledge_hierarchy_enter_iv);
            itemKnowledgeHierarchyTitle = itemView.findViewById(R.id.item_knowledge_hierarchy_title);
            itemKnowledgeHierarchyContent = itemView.findViewById(R.id.item_knowledge_hierarchy_content);
        }
    }

}
