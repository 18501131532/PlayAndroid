package com.jy.theplayandroid.playandroid.Dialogfragment;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jy.theplayandroid.playandroid.R;
import com.jy.theplayandroid.playandroid.base.DialogFragment;
import com.jy.theplayandroid.playandroid.concat.SearchHotkey;
import com.jy.theplayandroid.playandroid.playandroid.daohang.utils.FlowLayout;
import com.jy.theplayandroid.playandroid.playandroid.xiangmu.presenter.IPSearchHotkey;
import com.jy.theplayandroid.playandroid.playandroid.zhishitixi.bean.SearchHotkeyBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sousuo extends DialogFragment<SearchHotkey.SearchHotkeyV, IPSearchHotkey<SearchHotkey.SearchHotkeyV>> implements SearchHotkey.SearchHotkeyV, View.OnClickListener {


    @BindView(R.id.search_back_ib)
    ImageButton searchBackIb;
    @BindView(R.id.search_tv)
    TextView searchTv;
    @BindView(R.id.search_edit)
    EditText searchEdit;
    @BindView(R.id.search_tint_tv)
    TextView searchTintTv;
    @BindView(R.id.search_toolbar)
    Toolbar searchToolbar;
    @BindView(R.id.top_search_flow_layout)
    FlowLayout topSearchFlowLayout;
    @BindView(R.id.search_history_clear_all_tv)
    TextView searchHistoryClearAllTv;
    @BindView(R.id.search_history_null_tint_tv)
    TextView searchHistoryNullTintTv;
    @BindView(R.id.search_history_rv)
    RecyclerView searchHistoryRv;
    @BindView(R.id.search_scroll_view)
    NestedScrollView searchScrollView;
    @BindView(R.id.search_coordinator_group)
    CoordinatorLayout searchCoordinatorGroup;
    Unbinder unbinder;
    int j=0;

    private SharedPreferences.Editor ed;
    private SharedPreferences sh;

    HashSet<String> set=new HashSet<>();
    List<String> list=new ArrayList<>();
    private LishiAdapter shi;

    int count=0;
    public Sousuo() {
        // Required empty public constructor
    }


    @Override
    public int createLayoutId() {
        return R.layout.fragment_sousuo;
    }

    @Override
    protected void init() {
        Mpresenter.getSearchHotkey();

        searchTv.setOnClickListener(this);
        searchBackIb.setOnClickListener(this);
        searchHistoryClearAllTv.setOnClickListener(this);
        sh = getActivity().getSharedPreferences("search", Context.MODE_PRIVATE);
        ed = sh.edit();



        while (!sh.getString(count+"","").equals("")){
            list.add(sh.getString(count+"",""));
            count++;
        }


        shi = new LishiAdapter(context,list);
        searchHistoryRv.setAdapter(shi);
        searchHistoryRv.setLayoutManager(new LinearLayoutManager(context));

        shi.setOnclicklist(new LishiAdapter.OnClickListener() {
            @Override
            public void onclicks(int i) {
                Intent in=new Intent(context,SearchActivity.class);
                in.putExtra("k",list.get(i));
                startActivity(in);
                getDialog().dismiss();

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        initDialog();
    }


    private void initDialog() {
        Window window = getDialog().getWindow();
//        生成popuwindows的宽高
        WindowManager manager = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
        int width = manager.getDefaultDisplay().getWidth();
        int height = manager.getDefaultDisplay().getHeight();
        window.setLayout(width, height);

        //取消过渡动画 , 使DialogSearch的出现更加平滑
        window.setWindowAnimations(R.style.DialogEmptyAnimation);



    }

    @Override
    public IPSearchHotkey<SearchHotkey.SearchHotkeyV> createPresenter() {
        return new IPSearchHotkey<>();
    }

    @Override
    public void showSearchHotkey(final List<SearchHotkeyBean.DataBean> dataBeans) {

        for (int i = 0; i < dataBeans.size(); i++) {
            final int j=i;
            Random myRandom = new Random();
            int ranColor = 0xff000000 | myRandom.nextInt(0x00ffffff);
             final TextView tv = (TextView) LayoutInflater.from(context).inflate(R.layout.layout, topSearchFlowLayout, false);
            tv.setText(dataBeans.get(i).getName());
            tv.setBackgroundColor(ranColor);
            topSearchFlowLayout.addView(tv);

            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in=new Intent(context,SearchActivity.class);
                    in.putExtra("k",dataBeans.get(j).getName());
                    startActivity(in);
                    getDialog().dismiss();

                }
            });
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();

        for(int i=0;i<list.size();i++){
            ed.putString(i+"",list.get(i));
        }
        ed.commit();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_tv:
                boolean ifs=true;
                String s = searchEdit.getText().toString();
                if (!s.equals("")) {
                    for (int i = 0; i < list.size(); i++) {
                        if (list.get(i).equals(s)) {
                            ifs = false;
                            break;
                        }
                    }
                    if (ifs) {
                        list.add(s);
                    }
                    shi.notifyDataSetChanged();
                    Intent in=new Intent(context,SearchActivity.class);
                    in.putExtra("k",s);

                    startActivity(in);
                    getDialog().dismiss();
                }else{
                    Toast.makeText(context, "输入不能为空", Toast.LENGTH_SHORT).show();
                }
                searchEdit.setText("");
                break;
            case R.id.search_back_ib:
                getDialog().dismiss();
                break;
            case R.id.search_history_clear_all_tv:
                ed.clear();
                ed.commit();
                list.clear();
                shi.notifyDataSetChanged();
                break;
        }
    }
}
