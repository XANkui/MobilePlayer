package com.example.xan.mobileplayer.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.xan.mobileplayer.R;
import com.example.xan.mobileplayer.utils.Utils;

import androidx.annotation.Nullable;

public class SystemVideoPlayer extends Activity implements View.OnClickListener {

    private VideoView vv_videoview;
    private Uri uri;
    private Utils utils;
    private MyReceiver myReceiver;

    private LinearLayout llTop;
    private TextView tvVideoName;
    private ImageView ivBattary;
    private TextView tvSystemTime;
    private Button btnVideoVoice;
    private SeekBar seekbarVideoVoice;
    private Button btnVideoSwitchPlayer;
    private LinearLayout llBottom;
    private TextView tvCurrenttime;
    private SeekBar seekbarVideo;
    private TextView tvVideoDuration;
    private Button btnVideoExit;
    private Button btnVideoPre;
    private Button btnVideoPausePlay;
    private Button btnVideoNext;
    private Button btnSwitchScreen;

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2019-06-10 13:07:28 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews() {
        setContentView(R.layout.activity_systemvideoplayer);

        llTop = (LinearLayout)findViewById( R.id.ll_top );
        tvVideoName = (TextView)findViewById( R.id.tv_video_name );
        ivBattary = (ImageView)findViewById( R.id.iv_battary );
        tvSystemTime = (TextView)findViewById( R.id.tv_system_time );
        btnVideoVoice = (Button)findViewById( R.id.btn_video_voice );
        seekbarVideoVoice = (SeekBar)findViewById( R.id.seekbar_video_voice );
        btnVideoSwitchPlayer = (Button)findViewById( R.id.btn_video_switch_player );
        llBottom = (LinearLayout)findViewById( R.id.ll_bottom );
        tvCurrenttime = (TextView)findViewById( R.id.tv_currenttime );
        seekbarVideo = (SeekBar)findViewById( R.id.seekbar_video );
        tvVideoDuration = (TextView)findViewById( R.id.tv_video_duration );
        btnVideoExit = (Button)findViewById( R.id.btn_video_exit );
        btnVideoPre = (Button)findViewById( R.id.btn_video_pre );
        btnVideoPausePlay = (Button)findViewById( R.id.btn_video_pause_play );
        btnVideoNext = (Button)findViewById( R.id.btn_video_next );
        btnSwitchScreen = (Button)findViewById( R.id.btn_switch_screen );

        vv_videoview = findViewById(R.id.vv_videoview);

        btnVideoVoice.setOnClickListener( this );
        btnVideoSwitchPlayer.setOnClickListener( this );
        btnVideoExit.setOnClickListener( this );
        btnVideoPre.setOnClickListener( this );
        btnVideoPausePlay.setOnClickListener( this );
        btnVideoNext.setOnClickListener( this );
        btnSwitchScreen.setOnClickListener( this );
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2019-06-10 13:07:28 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if ( v == btnVideoVoice ) {
            // Handle clicks for btnVideoVoice
        } else if ( v == btnVideoSwitchPlayer ) {
            // Handle clicks for btnVideoSwitchPlayer
        } else if ( v == btnVideoExit ) {
            // Handle clicks for btnVideoExit
        } else if ( v == btnVideoPre ) {
            // Handle clicks for btnVideoPre
        } else if ( v == btnVideoPausePlay ) {
            // Handle clicks for btnVideoPausePlay
            if(vv_videoview.isPlaying()){
                vv_videoview.pause();
                btnVideoPausePlay.setBackgroundResource(R.drawable.btn_video_play_selector);

            }else {

                vv_videoview.start();
                btnVideoPausePlay.setBackgroundResource(R.drawable.btn_video_pause_selector);
            }
        } else if ( v == btnVideoNext ) {
            // Handle clicks for btnVideoNext
        } else if ( v == btnSwitchScreen ) {
            // Handle clicks for btnSwitchScreen
        }
    }



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        findViews();


        setListener();

        uri = getIntent().getData();
        if(uri != null){

            vv_videoview.setVideoURI(uri);
        }

        vv_videoview.setMediaController(new MediaController(this));
    }

    private void initData() {
        utils= new Utils();

        myReceiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(myReceiver,intentFilter);
    }

    class MyReceiver extends BroadcastReceiver{


        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level",0);
            setBatteryLevel(level);
        }
    }

    private void setBatteryLevel(int level){

        if(level <= 0){
            ivBattary.setBackgroundResource(R.drawable.ic_battery_0);

        }else if(level <= 10){
            ivBattary.setBackgroundResource(R.drawable.ic_battery_10);

        }else if(level <= 20){
            ivBattary.setBackgroundResource(R.drawable.ic_battery_20);

        }else if(level <= 40){
            ivBattary.setBackgroundResource(R.drawable.ic_battery_40);

        }else if(level <= 60){
            ivBattary.setBackgroundResource(R.drawable.ic_battery_60);

        }else if(level <= 80){
            ivBattary.setBackgroundResource(R.drawable.ic_battery_80);

        }else if(level <= 100){
            ivBattary.setBackgroundResource(R.drawable.ic_battery_100);

        }else {
            ivBattary.setBackgroundResource(R.drawable.ic_battery_100);

        }

    }

    private void setListener() {
        vv_videoview.setOnPreparedListener(new MyOnPreparedListener());
        vv_videoview.setOnErrorListener(new MyOnErrorListener());
        vv_videoview.setOnCompletionListener(new MyOnCompletionListener());
        seekbarVideo.setOnSeekBarChangeListener(new VideoOnSeekBarChangeListener());
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    class MyOnPreparedListener implements MediaPlayer.OnPreparedListener{


        @Override
        public void onPrepared(MediaPlayer mp) {
            vv_videoview.start();
        }
    }

    private class MyOnErrorListener implements MediaPlayer.OnErrorListener {
        @Override
        public boolean onError(MediaPlayer mp, int what, int extra) {

            Toast.makeText(SystemVideoPlayer.this, "播放出错了哦",Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private class MyOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText(SystemVideoPlayer.this,"播放完了："+uri, Toast.LENGTH_SHORT).show();
        }
    }

    private class VideoOnSeekBarChangeListener implements SeekBar.OnSeekBarChangeListener {
        /**
         *
         * @param seekBar
         * @param progress
         * @param fromUser      用户拖动为true ,进度条播放自动为false
         */
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser == true){

                vv_videoview.seekTo(progress);

            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }

    @Override
    protected void onDestroy() {
        if(myReceiver != null){
            unregisterReceiver(myReceiver);
            myReceiver = null;

        }

        super.onDestroy();
    }
}
