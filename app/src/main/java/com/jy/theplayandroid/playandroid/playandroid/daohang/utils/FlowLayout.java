package com.jy.theplayandroid.playandroid.playandroid.daohang.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jy.theplayandroid.playandroid.Dialogfragment.Changyong;

import java.util.ArrayList;
import java.util.List;


/**
 * 流式布局
 * Created by MQ on 2017/5/24.
 */

public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        this(context, null);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //获取设置的宽高的模式和具体的值(屏幕的宽高)
        int widhtMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //至多模式，使用以下两个变量计算真实的宽高
        int height = 0;
        int width = 0;
        //每一行的宽高
        int lineWidth = 0;
        int lineHeight = 0;
        //获取子视图
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //只有调用了如下的方法，方可计算子视图测量的宽和高
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            //获取子视图的宽和高
            int childWidth = childView.getMeasuredWidth();
            int childHight = childView.getMeasuredHeight();
            //要想保证可以获取子视图的边距参数对象，必须重写generateLayoutParams()
            MarginLayoutParams mp = (MarginLayoutParams) childView.getLayoutParams();
            if (lineWidth + childWidth + mp.leftMargin + mp.rightMargin < widthSize) {//不换行
                lineWidth += childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = Math.max(lineHeight, childHight + mp.topMargin + mp.bottomMargin);
            } else {//换行
                width = Math.max(lineWidth, width);
                height += lineHeight;
                //重置
                lineWidth = childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = childHight + mp.topMargin + mp.bottomMargin;
            }
            //最后一个元素
            if (i == childCount - 1) {
                height += lineHeight;
                width = Math.max(lineWidth, width);
            }
        }
        Log.e("TAG", "widthSize==" + widthSize + ",heightSize==" + heightSize);// widthSize==768,heightSize==838
        Log.e("TAG", "width==" + width + ",height==" + height);//width==720,height==282
        setMeasuredDimension(widhtMode == MeasureSpec.EXACTLY ? widthSize : width, heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    //重写的目的，给每个子视图指定显示的位置:childview.layout();
    private List<List<View>> allViews = new ArrayList<>();//每一行的子视图宽度的集合构成的集合
    private List<Integer> allHeight = new ArrayList<>();//每一行的高度构成的集合

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //1.给两个集合添加元素
        int childCount = getChildCount();
        //获取布局的宽(也就是屏幕的宽)
        int widthSize = this.getMeasuredWidth();
        //行高和行宽
        int lineWidth = 0;
        int lineHeight = 0;
        List<View> lineList = new ArrayList<>();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //获取子视图的宽和高
            int childWidth = childView.getMeasuredWidth();
            int childHight = childView.getMeasuredHeight();
            //要想保证可以获取子视图的边距参数对象，必须重写generateLayoutParams()
            MarginLayoutParams mp = (MarginLayoutParams) childView.getLayoutParams();
            if (lineWidth + childWidth + mp.leftMargin + mp.rightMargin <= widthSize) {//不换行
                lineList.add(childView);
                lineWidth += childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = Math.max(lineHeight, childHight + mp.topMargin + mp.bottomMargin);
            } else {//换行
                allHeight.add(lineHeight);
                allViews.add(lineList);
                //重置
                lineWidth = childWidth + mp.leftMargin + mp.rightMargin;
                lineHeight = childHight + mp.topMargin + mp.bottomMargin;
                lineList = new ArrayList<>();
                lineList.add(childView);
            }
            if(i==childCount-1){
                allHeight.add(lineHeight);
                allViews.add(lineList);
            }

        }
        Log.e("TAG","allviews.size=="+allViews.size()+",allHeights.size=="+allHeight.size());
        //2.给每个元素添加位置
        int x=0;
        int y=0;
        for(int i=0;i<allViews.size();i++){
            List<View> views = allViews.get(i);//获取每一行的集合
            for(int j=0;j<views.size();j++){//获取每一行的view
                View childView = views.get(j);
                MarginLayoutParams mp = (MarginLayoutParams) childView.getLayoutParams();
                int left=x+mp.leftMargin;
                int top=y+mp.topMargin;
                int right=left+childView.getMeasuredWidth();
                int bottom=top+childView.getMeasuredHeight();
                x+=mp.leftMargin+mp.rightMargin+childView.getMeasuredWidth();
                childView.layout(left,top,right,bottom);
            }
            y+=allHeight.get(i);
            x=0;
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        MarginLayoutParams mp = new MarginLayoutParams(getContext(), attrs);
        return mp;
    }


}
