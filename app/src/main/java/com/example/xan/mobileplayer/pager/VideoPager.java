package com.example.xan.mobileplayer.pager;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.Formatter;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xan.mobileplayer.R;
import com.example.xan.mobileplayer.activity.SystemVideoPlayer;
import com.example.xan.mobileplayer.adapter.VideoPagerAdapter;
import com.example.xan.mobileplayer.base.BasePager;
import com.example.xan.mobileplayer.domain.MediaItem;
import com.example.xan.mobileplayer.utils.LogUtil;
import com.example.xan.mobileplayer.utils.Utils;


import java.util.ArrayList;



public class VideoPager extends BasePager {
    //private TextView textView;

    private ListView listView;
    private TextView tv_nomedia;
    private ProgressBar pb_loading;
    private ArrayList<MediaItem> mediaItems;

    private VideoPagerAdapter videoPagerAdapter;
    private Handler mhandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(mediaItems != null && mediaItems.size() >0){
                videoPagerAdapter = new VideoPagerAdapter(context, mediaItems);
                listView.setAdapter(videoPagerAdapter);
                tv_nomedia.setVisibility(View.GONE);

            }else {

                tv_nomedia.setVisibility(View.VISIBLE);
            }

            pb_loading.setVisibility(View.GONE);
        }
    };

    public VideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        LogUtil.e("本地视频页面被初始化了");
        /*textView = new TextView(content);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(25);
        textView.setTextColor(Color.RED);
        return textView;*/

        View view =  View.inflate(context, R.layout.video_pager,null);
        listView = view.findViewById(R.id.listview);
        tv_nomedia = view.findViewById(R.id.tv_nomedia);
        pb_loading = view.findViewById(R.id.pb_loading);

        listView.setOnItemClickListener(new MyOnItemClickListener());
        return  view;
    }

    class MyOnItemClickListener implements AdapterView.OnItemClickListener{


        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            MediaItem mediaItem = mediaItems.get(position);
            Toast.makeText(context,"MediaItem=="+mediaItem.toString(),Toast.LENGTH_SHORT).show();

            // 调用系统拥有的播放器播放
            /*Intent intent = new Intent();
            intent.setDataAndType(Uri.parse(mediaItem.getData()),"video/*");
            context.startActivity(intent);*/

            /*Intent intent = new Intent(context, SystemVideoPlayer.class);
            intent.setDataAndType(Uri.parse(mediaItem.getData()),"video/*");
            context.startActivity(intent);*/

            Intent intent = new Intent(context, SystemVideoPlayer.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("videolist",mediaItems);
            intent.putExtras(bundle);
            intent.putExtra("position", position);
            context.startActivity(intent);
        }
    }

    @Override
    public void initData() {
        super.initData();
        LogUtil.e("本地视频数据被初始化了");
        //textView.setText("本地视频页面");

        getDataFromLocal();
    }

    private void getDataFromLocal() {

        new Thread(){

            @Override
            public void run() {
                super.run();
                LogUtil.e("getDataFromLocal.run");
                mediaItems = new ArrayList<>();
                ContentResolver contentResolver = context.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                String[] objs = {

                        MediaStore.Video.Media.DISPLAY_NAME,
                        MediaStore.Video.Media.DURATION,
                        MediaStore.Video.Media.SIZE,
                        MediaStore.Video.Media.DATA,
                        MediaStore.Video.Media.ARTIST

                };

                Cursor cursor =  contentResolver.query(uri,objs,null,null,null);
                if(cursor != null){
                    LogUtil.e("getDataFromLocal.run  cursor != null");
                    while (cursor.moveToNext() ){

                        MediaItem mediaItem = new MediaItem();
                        mediaItems.add(mediaItem);

                        String name = cursor.getString(0);
                        mediaItem.setName(name);
                        long duration = cursor.getLong(1);
                        mediaItem.setDuration(duration);
                        long size = cursor.getLong(2);
                        mediaItem.setSize(size);
                        String data = cursor.getString(3);
                        mediaItem.setData(data);
                        String artist = cursor.getString(4);
                        mediaItem.setArtist(artist);

                        LogUtil.e("getDataFromLocal.run  mediaItem != null");

                    }
                    cursor.close();
                }

                mhandler.sendEmptyMessage(0);
            }
        }.start();
    }




}
