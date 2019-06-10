package com.example.xan.mobileplayer.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.xan.mobileplayer.R;

import androidx.annotation.Nullable;

public class TitleBar extends LinearLayout implements View.OnClickListener {

    private Context context;


    private View tv_search;
    private View rl_game;
    private View iv_record;


    public TitleBar(Context context) {
        this(context,null);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context =context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        tv_search = getChildAt(1);
        rl_game = getChildAt(2);
        iv_record =getChildAt(3);

        tv_search.setOnClickListener(this);
        rl_game.setOnClickListener(this);
        iv_record.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tv_search:
                Toast.makeText(context,"搜索", Toast.LENGTH_SHORT).show();
                break;

            case R.id.rl_game:
                Toast.makeText(context,"游戏", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_record:
                Toast.makeText(context,"记录", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
