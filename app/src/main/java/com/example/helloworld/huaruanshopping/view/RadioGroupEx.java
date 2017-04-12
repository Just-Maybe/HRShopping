package com.example.helloworld.huaruanshopping.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;

/**
 * Created by helloworld on 2017/3/24.
 */

public class RadioGroupEx extends RadioGroup {
    public RadioGroupEx(Context context) {
        super(context);
    }

    public RadioGroupEx(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getSize(heightMeasureSpec);

//        调用ViewGroup的方法 测量子view
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int maxWidth = 0;
        int totalHeight = 0;

//        当前这行的累积行宽
        int lineWidth = 0;
        //用于记录换行前的行宽和行高
        int maxLineHeight = 0;

        //用于记录换行前的行宽和行高
        int oldHeight;
        int oldWidth;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            //得到这行的最高
            oldHeight = maxLineHeight;
            //当前最大宽高
            oldWidth = maxWidth;

            int deltaX = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            if (lineWidth + deltaX + getPaddingLeft() + getPaddingRight() > widthSize) {
                //如果折行，height增加
                //和目前最大的宽度比较，得到最宽。不能加上当前child的宽，所有用的是oldWidth
                maxWidth = Math.max(lineWidth, oldWidth);
                //重置宽度
                lineWidth = deltaX;
                totalHeight += oldHeight;
                //重置行高，当前这个view，属于下一行，因此当前最大行高为这个child的高度加上margin
                maxLineHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
            } else {
                //不换行，累加宽度
                lineWidth += deltaX;
                //不换行，计算行最高
                int deltaY = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
                maxLineHeight = Math.max(maxLineHeight, deltaY);

            }
            if (i == count - 1) {
                //前面没有加上下一行的高，如果是最后一行，还要再叠加上最后一行的最高的值
                totalHeight += maxLineHeight;
                maxWidth = Math.max(lineWidth, oldWidth);
            }
        }
        //加上当前容器的padding的值
        maxWidth += getPaddingLeft() + getPaddingRight();
        totalHeight += getPaddingTop() + getPaddingBottom();
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : maxWidth,
                heightMode == MeasureSpec.EXACTLY ? heightSize : totalHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        //pre为前面所有的child相加后的位置
        int preLeft = getPaddingLeft();
        int preTop = getPaddingTop();
        //记录每一行的最高值
        int maxHeight = 0;
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
            //r-1为当前容器的宽度。如果子view的累积宽度大于容器宽度，就换行
            if (preLeft + params.leftMargin + child.getMeasuredWidth() + params.rightMargin + getPaddingRight() > (r - 1)) {
                //重置
                preLeft = getPaddingLeft();
                //要选择child的height最大的作为设置
                preTop = preTop + maxHeight;
                maxHeight = getChildAt(i).getMeasuredHeight() + params.topMargin + params.bottomMargin;
            } else {
                maxHeight = Math.max(maxHeight, child.getMeasuredHeight() + params.topMargin + params.bottomMargin);
            }
            //left坐标
            int left = preLeft + params.leftMargin;
            //top坐标
            int top = preTop + params.topMargin;
            int right = left + child.getMeasuredWidth();
            int bottom = top + child.getMeasuredHeight();
            //为子view布局
            child.layout(left, top, right, bottom);
            //计算布局结束后，preLeft的值
            preLeft += params.leftMargin + child.getMeasuredWidth() + params.rightMargin;
        }
    }

}
