package com.jy.theplayandroid.playandroid.playandroid.daohang.adapter;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jy.theplayandroid.playandroid.R;

import java.util.ArrayList;

public class DaoHangListAdapter extends BaseAdapter {
    public ArrayList<String> mStrings;
    private int mCurrentPos = 0;
    private boolean mBool;

    public DaoHangListAdapter(ArrayList<String> strings) {
        mStrings = strings;
    }

    @Override
    public int getCount() {
        return mStrings.size();
    }

    @Override
    public Object getItem(int position) {
        return mStrings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lv, null, false);
            viewHolder.mTextView = convertView.findViewById(R.id.tv_itemlv);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mTextView.setText(mStrings.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView mTextView;
    }
}
