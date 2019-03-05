package com.jy.theplayandroid.playandroid.Dialogfragment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;

import java.util.List;

public class LishiAdapter extends RecyclerView.Adapter<LishiAdapter.Mywang> {

    Context context;
    List<String> list;
    OnClickListener onclicklist;

    public void setOnclicklist(OnClickListener onclicklist) {
        this.onclicklist = onclicklist;
    }

    public LishiAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public LishiAdapter.Mywang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.lishi,parent,false);
        return new Mywang(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LishiAdapter.Mywang holder, final int position) {
        holder.text.setText(list.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclicklist.onclicks(position);
            }
        });
    }

    public interface OnClickListener{
        void onclicks(int i);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class Mywang extends RecyclerView.ViewHolder{
        TextView text;
        ImageView image;
        public Mywang(View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.lishi_text);
            image=itemView.findViewById(R.id.lishi_image);
        }
    }
}
