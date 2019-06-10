package com.example.xan.mobileplayer.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.xan.mobileplayer.base.BasePager;
import com.example.xan.mobileplayer.utils.LogUtil;

public class NetAudioPager extends BasePager {
    private TextView textView;

    public NetAudioPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        LogUtil.e("网络音频页面被初始化了");
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("网络音频数据被初始化了");
        textView.setText("网络音频页面");
    }
}
