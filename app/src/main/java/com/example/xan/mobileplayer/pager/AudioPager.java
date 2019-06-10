package com.example.xan.mobileplayer.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xan.mobileplayer.base.BasePager;
import com.example.xan.mobileplayer.utils.LogUtil;

public class AudioPager extends BasePager {
    private TextView textView;

    public AudioPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        LogUtil.e("本地音频页面被初始化了");
        textView = new TextView(context);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("本地音频数据被初始化了");
        textView.setText("本地音频页面");
    }
}
