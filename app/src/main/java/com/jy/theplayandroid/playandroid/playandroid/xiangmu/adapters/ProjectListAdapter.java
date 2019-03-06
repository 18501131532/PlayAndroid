package com.jy.theplayandroid.playandroid.playandroid.xiangmu.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.http.HttpGreendao;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ImageList;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.bean.ProjectListBean;

import java.util.List;

public class ProjectListAdapter extends RecyclerView.Adapter<ProjectListAdapter.ViewHoder>{
    private Context context;
    private List<ProjectListBean.DataBean.DatasBean> mList;

    public ProjectListAdapter(Context context, List<ProjectListBean.DataBean.DatasBean> list) {
        this.context = context;
        mList = list;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_project_list,parent,false);
        ViewHoder viewHoder = new ViewHoder(view);
        return viewHoder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, final int position) {
        ProjectListBean.DataBean.DatasBean datasBean = mList.get(position);
        List<ImageList> imageLists = HttpGreendao.getInstance().selectImg();
        boolean one = imageLists.get(0).getOne();
        if (one){
            holder.mImage.setImageResource(R.mipmap.ic_launcher);
        }else {
            Glide.with(context).load(datasBean.getEnvelopePic()).into(holder.mImage);
        }


        holder.mTitle.setText(datasBean.getTitle());
        holder.mTv.setText(datasBean.getDesc());
        holder.mTime.setText(datasBean.getNiceDate());
        holder.mName.setText(datasBean.getAuthor());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItmeClick.ondianji(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void addData(List<ProjectListBean.DataBean.DatasBean> dataBeans) {
        mList.clear();
        mList.addAll(dataBeans);
        notifyDataSetChanged();

    }

    public class ViewHoder extends RecyclerView.ViewHolder {

        private final ImageView mImage;
        private final TextView mTitle;
        private final TextView mTv;
        private final TextView mTime;
        private final TextView mName;

        public ViewHoder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.item_project_list_iv);
            mTitle = itemView.findViewById(R.id.item_project_list_title_tv);
            mTv = itemView.findViewById(R.id.item_project_list_content_tv);
            mTime = itemView.findViewById(R.id.item_project_list_time_tv);
            mName = itemView.findViewById(R.id.item_project_list_author_tv);
        }
    }

    private OnItmeClick mOnItmeClick;

    public void setOnItmeClick(OnItmeClick onItmeClick) {
        mOnItmeClick = onItmeClick;
    }

    public interface OnItmeClick{
        void ondianji(int position);
    }
}
