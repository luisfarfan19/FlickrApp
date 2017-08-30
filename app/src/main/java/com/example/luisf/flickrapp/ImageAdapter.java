package com.example.luisf.flickrapp;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by luisf on 28/08/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> arrayList;

    public ImageAdapter(Context c, ArrayList<String> stringArrayList) {
        arrayList= stringArrayList;
        mContext = c;
    }

    public int getCount() {
        return arrayList.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.with(mContext).load(arrayList.get(position)).into(imageView);
        return imageView;
    }
}
