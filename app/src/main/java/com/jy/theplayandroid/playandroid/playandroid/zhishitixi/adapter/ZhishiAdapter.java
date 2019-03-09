package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.bean.OneBean;

import java.util.List;
import java.util.Random;

public class ZhishiAdapter extends RecyclerView.Adapter<ZhishiAdapter.Mywang> {
    Context context;
    List<OneBean.DataBean> list;
    OnClickListener onclicklist;

    public void setOnclicklist(OnClickListener onclicklist) {
        this.onclicklist = onclicklist;
    }

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

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull Mywang holder, final int position) {
        holder.itemKnowledgeHierarchyTitle.setText(list.get(position).getName());
        StringBuilder bu = new StringBuilder();
        for (int i = 0; i < list.get(position).getChildren().size(); i++) {
            bu.append(list.get(position).getChildren().get(i).getName()).append("    ");
            holder.itemKnowledgeHierarchyContent.setText(bu.toString());
        }
        holder.itemKnowledgeHierarchyTitle.setTextColor(intrandomColor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclicklist.onclickshow(position);
            }
        });
    }

    //获取随机rgb颜色值
    public int intrandomColor() {
        Random random = new Random();
        int red = random.nextInt(150);//0-190        ,如果颜色值过大,就越接近白色,就看不清了,所以需要限定范围
        int green = random.nextInt(150);//0-190
        int blue = random.nextInt(150);//0-190
        return Color.rgb(red, green, blue);//使用rgb混合生成一种新的颜色,Color.rgb生成的是一个int数
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
            knowledgeHierarchyEnterIv = itemView.findViewById(R.id.knowledge_hierarchy_enter_iv);
            itemKnowledgeHierarchyTitle = itemView.findViewById(R.id.item_knowledge_hierarchy_title);
            itemKnowledgeHierarchyContent = itemView.findViewById(R.id.item_knowledge_hierarchy_content);
        }
    }


    public interface OnClickListener{
        void onclickshow(int i);
    }

}
