package com.example.xan.mobileplayer.adapter;

import android.content.Context;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xan.mobileplayer.R;
import com.example.xan.mobileplayer.domain.MediaItem;
import com.example.xan.mobileplayer.pager.VideoPager;
import com.example.xan.mobileplayer.utils.Utils;

import java.util.ArrayList;

public class VideoPagerAdapter extends BaseAdapter{
    private Utils utils;
    private Context context;
    private ArrayList<MediaItem> mediaItems;

    public VideoPagerAdapter(Context context, ArrayList<MediaItem> mediaItems){
        this.context = context;
        this.mediaItems = mediaItems;
        utils = new Utils();

    }

    @Override
    public int getCount() {
        return mediaItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){

            View view = View.inflate(context ,R.layout.item_video_pager,null);
            viewHolder = new ViewHolder();
            viewHolder.iv_icon = view.findViewById(R.id.iv_icon);
            viewHolder.tv_videoname = view.findViewById(R.id.tv_videoname);
            viewHolder.tv_videosize = view.findViewById(R.id.tv_videosize);
            viewHolder.tv_videotime = view.findViewById(R.id.tv_videotime);

            convertView.setTag(viewHolder);

        }else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        MediaItem mediaItem = mediaItems.get(position);
        viewHolder.tv_videoname.setText(mediaItem.getName());
        viewHolder.tv_videosize.setText(Formatter.formatFileSize(context,mediaItem.getSize()));
        viewHolder.tv_videotime.setText(utils.stringForTime((int)mediaItem.getDuration()));

        return convertView;
    }

    static class ViewHolder{
        ImageView iv_icon;
        TextView tv_videoname;
        TextView tv_videotime;
        TextView tv_videosize;

    }
}
