package com.jy.theplayandroid.playandroid.Dialogfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.SearchBean;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.Mywang> {
    Context context;
    List<SearchBean.DataBean.DatasBean> list;
    OnClickListener onclicklist;
    itemonclicklist itemonclicks;

    public void setItemonclicks(itemonclicklist itemonclicks) {
        this.itemonclicks = itemonclicks;
    }

    public void setOnclicklist(OnClickListener onclicklist) {
        this.onclicklist = onclicklist;
    }

    public SearchAdapter(Context context, List<SearchBean.DataBean.DatasBean> list) {
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
        holder.zhishiActivityItemHou.setText(list.get(position).getSuperChapterName()+"/"+list.get(position).getChapterName());
        holder.zhishiActivityItemContext.setText(list.get(position).getTitle());
        holder.zhishiActivityItemTim.setText(list.get(position).getNiceDate()+"");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemonclicks.itemonclick(position);
            }
        });

        holder.homePageItemlistLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclicklist.onclicks(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnClickListener{
        void onclicks(int i);
    }


    public interface itemonclicklist{
        void itemonclick(int i);
    }
    class Mywang extends RecyclerView.ViewHolder {
        private LinearLayout itemSearchPagerGroup;
        private TextView zhishiActivityItemTitle;
        private TextView zhishiActivityItemHou;
        private TextView zhishiActivityItemContext;
        private LinearLayout itemSearchTagGroup;
        private ImageView homePageItemlistLike;
        private TextView zhishiActivityItemTim;


        public Mywang(View itemView) {
            super(itemView);
            itemSearchPagerGroup = itemView.findViewById(R.id.item_search_pager_group);
            zhishiActivityItemTitle = itemView.findViewById(R.id.zhishi_activity_item_title);
            zhishiActivityItemHou = itemView.findViewById(R.id.zhishi_activity_item_hou);
            zhishiActivityItemContext = itemView.findViewById(R.id.zhishi_activity_item_context);
            itemSearchTagGroup = itemView.findViewById(R.id.item_search_tag_group);
            homePageItemlistLike = itemView.findViewById(R.id.home_page_itemlist_like);
            zhishiActivityItemTim = itemView.findViewById(R.id.zhishi_activity_item_tim);
        }
    }
}
