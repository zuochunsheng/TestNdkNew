package com.huican.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.huican.test.R;

import java.util.ArrayList;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;
import pl.droidsonroids.gif.GifTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GifTextView giftv =   (GifTextView)findViewById(R.id.giftv) ;
        GifImageView gifiv =   (GifImageView)findViewById(R.id.gifiv) ;
        ImageView iv =   (ImageView)findViewById(R.id.iv) ;

        Glide.with(this)
                .load(R.drawable.anim_flag_chile)
                .asGif()
                .into(iv);


    }

    public static Bitmap getBitmapArrayByGif(Context context, String assertPath, int index) {
        try {
            ArrayList<Bitmap> list = new ArrayList<>();
            GifDrawable gifFromAssets = new GifDrawable(context.getAssets(), assertPath);//代表android中assert的gif文件名
            int totalCount = gifFromAssets.getNumberOfFrames();
            if (totalCount < index) {
                index = totalCount - 1;
            }
            return gifFromAssets.seekToFrameAndGet(index);
        } catch (Exception e) {
            return null;
        }
    }

}
