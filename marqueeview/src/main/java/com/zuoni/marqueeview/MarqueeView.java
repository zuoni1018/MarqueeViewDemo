package com.zuoni.marqueeview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.zuoni.bean.ALiNotice;

import java.util.List;

/**
 * 垂直跑马灯
 */
public class MarqueeView extends ViewFlipper {

    private Context mContext;
    private List<ALiNotice> mList;
    private boolean isSetAnimDuration = false;
    private int interval = 2000;
    private int animDuration = 500;

    public MarqueeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        setFlipInterval(interval);//设置切换时间间隔
    }

    // 根据公告字符串列表启动轮播
    public void startWithList(List<ALiNotice> mList) {
        this.mList = mList;
        start();
    }

    /**
     * 启动轮播
     */
    public boolean start() {
        //判断列表长度
        if (mList == null || mList.size() == 0) {
            return false;
        }
        removeAllViews();//移除所有View
        resetAnimation();//重置动画效果

        //根据List生成View
        for (int i = 0; i < mList.size(); i++) {
            View mView = LayoutInflater.from(mContext).inflate(R.layout.view_style, null);
            LinearLayout layoutNotice01 = (LinearLayout) mView.findViewById(R.id.layoutNotice01);
            LinearLayout layoutNotice02 = (LinearLayout) mView.findViewById(R.id.layoutNotice02);
            TextView tvType01 = (TextView) mView.findViewById(R.id.tvType01);
            TextView tvType02 = (TextView) mView.findViewById(R.id.tvType02);
            TextView tvContent01 = (TextView) mView.findViewById(R.id.tvContent01);
            TextView tvContent02 = (TextView) mView.findViewById(R.id.tvContent02);

            tvType01.setText(mList.get(i).getType01());
            tvContent01.setText(mList.get(i).getContent01());
            final int finalI1 = i;
            layoutNotice01.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, mList.get(finalI1).getType01() + mList.get(finalI1).getContent01(), Toast.LENGTH_SHORT).show();
                }
            });
            if (!mList.get(i).getType02().equals("")) {
                tvType02.setText(mList.get(i).getType02());
                tvContent02.setText(mList.get(i).getContent02());
                layoutNotice02.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, mList.get(finalI1).getType02() + mList.get(finalI1).getContent02(), Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                layoutNotice02.setVisibility(GONE);
            }
            addView(mView);
        }

        if (mList.size() > 1) {
            //若列表大于0则开始滚动
            startFlipping();
        } else {
            stopFlipping();
        }
        return true;
    }

    //重置 设置动画
    private void resetAnimation() {
        clearAnimation();//清除所有动画

        Animation animIn = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_in);
        if (isSetAnimDuration) animIn.setDuration(animDuration);
        setInAnimation(animIn);//设置进入动画

        Animation animOut = AnimationUtils.loadAnimation(mContext, R.anim.anim_marquee_out);
        if (isSetAnimDuration) animOut.setDuration(animDuration);
        setOutAnimation(animOut);//设置退出动画
    }
}
