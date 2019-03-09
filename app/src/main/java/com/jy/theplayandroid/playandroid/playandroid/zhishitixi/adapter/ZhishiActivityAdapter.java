package com.jy.theplayandroid.playandroid.playandroid.zhishitixi.adapter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.LoadingActivity;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.bean.TwoBEAN;

import java.util.List;


public class ZhishiActivityAdapter extends RecyclerView.Adapter<ZhishiActivityAdapter.Mywang> {

    Context context;
    List<TwoBEAN.DataBean.DatasBean> list;
    OnClickListener onclicklist;
    private final SharedPreferences sh;
    private final SharedPreferences.Editor ed;


    public void setOnclicklistlike(OnClickListenerlike onclicklistlike) {
        this.onclicklistlike = onclicklistlike;
    }

    OnClickListenerlike onclicklistlike;




    public void setOnclicklist(OnClickListener onclicklist) {
        this.onclicklist = onclicklist;
    }

    public ZhishiActivityAdapter(Context context, List<TwoBEAN.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
        Log.e("liangxq1", list.toString());
        sh = context.getSharedPreferences("loging", Context.MODE_PRIVATE);
        ed = sh.edit();



    }

    @NonNull
    @Override
    public Mywang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.zhishi_activity_item, parent, false);
        Log.i("liangxq", "onCreateViewHolder: " + "onCreateViewHolder");
        return new Mywang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Mywang holder, int position) {
        Log.e("liangxq1", "onBindViewHolder: " + "123123123123123123123123");

        holder.zhishiActivityItemTitle.setText(list.get(position).getAuthor());
        holder.zhishiActivityItemHou.setText(list.get(position).getSuperChapterName() + "/" + list.get(position).getChapterName());
        holder.zhishiActivityItemContext.setText(list.get(position).getTitle());
        holder.zhishiActivityItemTim.setText(list.get(position).getNiceDate());


        holder.itemSearchPagerGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclicklist.onclickshow(position,v);
            }
        });


        holder.homePageItemlistLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!sh.getBoolean("loging",false)){
                    Intent in=new Intent(context, LoadingActivity.class);
                    context.startActivity(in);
                }else{
                    if(!sh.getBoolean("title",false)){
                        holder.homePageItemlistLike.setImageResource(R.drawable.icon_like);
                        ed.putBoolean("title",true);
                    }else{
                        holder.homePageItemlistLike.setImageResource(R.drawable.icon_like_article_not_selected);
                        ed.putBoolean("title",false);
                    }
                    ed.commit();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public void setData(List<TwoBEAN.DataBean.DatasBean> data) {

        list.addAll(data);
        notifyDataSetChanged();
    }



    public class Mywang extends RecyclerView.ViewHolder {
        private TextView zhishiActivityItemTitle;
        private TextView zhishiActivityItemHou;
        private TextView zhishiActivityItemContext;
        private TextView zhishiActivityItemTim;
        private LinearLayout itemSearchPagerGroup;
        private ImageView homePageItemlistLike;


        public Mywang(View itemView) {
            super(itemView);
            homePageItemlistLike = itemView.findViewById(R.id.home_page_itemlist_like);
            itemSearchPagerGroup = itemView.findViewById(R.id.item_search_pager_group);
            zhishiActivityItemTitle = itemView.findViewById(R.id.zhishi_activity_item_title);
            zhishiActivityItemHou = itemView.findViewById(R.id.zhishi_activity_item_hou);
            zhishiActivityItemContext = itemView.findViewById(R.id.zhishi_activity_item_context);
            zhishiActivityItemTim = itemView.findViewById(R.id.zhishi_activity_item_tim);
        }
    }


    public interface OnClickListener {
        void onclickshow(int i,View view);
    }
    public interface OnClickListenerlike {
        void onclicklike(int i,Mywang holder);
    }
}
