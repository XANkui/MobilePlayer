package com.example.xan.mobileplayer.activity;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.example.xan.mobileplayer.R;
import com.example.xan.mobileplayer.base.BasePager;
import com.example.xan.mobileplayer.myfragment.RepalaceFragment;
import com.example.xan.mobileplayer.pager.AudioPager;
import com.example.xan.mobileplayer.pager.NetAudioPager;
import com.example.xan.mobileplayer.pager.NetVideoPager;
import com.example.xan.mobileplayer.pager.VideoPager;
import com.example.xan.mobileplayer.utils.DyGetPermission;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    private FrameLayout fl_main_content;
    private RadioGroup rg_bottom_tag;

    private ArrayList<BasePager> basePagers;
    private int position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

   /*     TextView textView = new TextView(this);
        textView.setText("我是主界面");
        textView.setTextSize(30);
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);*/

        setContentView(R.layout.activity_main);

        fl_main_content = findViewById(R.id.fl_main_content);
        rg_bottom_tag = findViewById(R.id.rg_bottom_tag);


        basePagers = new ArrayList<>();
        basePagers.add(new VideoPager(this));
        basePagers.add(new AudioPager(this));
        basePagers.add(new NetVideoPager(this));
        basePagers.add(new NetAudioPager(this));

        rg_bottom_tag.setOnCheckedChangeListener(new MyOnCheckedListener());
        rg_bottom_tag.check(R.id.rb_video);     // 设置默认的
    }


    private class MyOnCheckedListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {

            switch (checkedId){

                default:
                    DyGetPermission.isGrantExternalRW(MainActivity.this);
                    position =0;
                    break;

                case R.id.rb_video:
                    DyGetPermission.isGrantExternalRW(MainActivity.this);

                    position =0;
                    break;

                case R.id.rb_audio:
                    DyGetPermission.isGrantExternalRW(MainActivity.this);

                    position =1;
                    break;
                case R.id.rb_net_video:
                    position =2;
                    break;
                case R.id.rb_net_audio:
                    position =3;
                    break;


            }

            setFragment();
        }
    }

    private void setFragment() {

        FragmentTransaction fragmentTransaction = testFun();

        fragmentTransaction.commit();

    }

    @NonNull
    private FragmentTransaction testFun() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_main_content, new RepalaceFragment(getBasePager()));
        return fragmentTransaction;
    }

    private BasePager getBasePager() {
        BasePager basePager = basePagers.get(position);
        if(basePager != null && basePager.isInitData == false){
            basePager.isInitData = true;
            basePager.initData();
        }

        return basePager;

    }




}
