package com.example.luisf.flickrapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by luisf on 29/08/2017.
 */

public class CustomAdapter extends ArrayAdapter {
    private TextView textView;
    private ImageView imageView;

    public CustomAdapter(@NonNull Context context, ArrayList<Category> categories){
        super(context, R.layout.file, categories);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View vistaCustom = layoutInflater.inflate(R.layout.file, parent, false);
        Category item = (Category) getItem(position);

        textView = (TextView) vistaCustom.findViewById(R.id.tvCategoryInfo);
        textView.setText(item.getCategoryName());

        imageView = (ImageView) vistaCustom.findViewById(R.id.ivCategoryPhoto);
        try {
            InputStream stream = getContext().getAssets().open(item.getCategoryPhoto()+".jpg");
            Drawable drawable = Drawable.createFromStream(stream, null);
            imageView.setImageDrawable(drawable);

        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return vistaCustom;
    }
}
