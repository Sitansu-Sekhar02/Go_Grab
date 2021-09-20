package com.sauthi.gograb.view;

import android.content.Context;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sauthi.gograb.R;


public class SeparateProgress {
    public static void loadImage(ImageView view, String url, CircularProgressDrawable progressDrawable){
        RequestOptions options=new RequestOptions()
                .placeholder(progressDrawable)
                .error(R.drawable.app_icon);
        Glide.with(view.getContext())
                .setDefaultRequestOptions(options)
                .load(url)
                .into(view);

    }
    public static CircularProgressDrawable setProgress(Context context){
        CircularProgressDrawable circularProgressDrawable=new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(10f);
        circularProgressDrawable.setCenterRadius(50f);
        circularProgressDrawable.start();
        return circularProgressDrawable;
    }
}
