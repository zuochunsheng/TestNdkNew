package com.huican.test;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.huican.test.R;
import java.nio.Buffer;
import java.nio.ByteBuffer;


public class SampleActivity extends Activity {

    private static final String  TAG = "SampleActivity";


    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        Bitmap bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        bitmap.eraseColor(0xff336699); // AARRGGBB
        byte[] bytes = new byte[bitmap.getWidth() * bitmap.getHeight() * 4];
        Buffer dst = ByteBuffer.wrap(bytes);
        bitmap.copyPixelsToBuffer(dst);
        // ARGB_8888 真实的存储顺序是 R-G-B-A
        Log.d(TAG, "R: " + Integer.toHexString(bytes[0] & 0xff));
        Log.d(TAG, "G: " + Integer.toHexString(bytes[1] & 0xff));
        Log.d(TAG, "B: " + Integer.toHexString(bytes[2] & 0xff));
        Log.d(TAG, "A: " + Integer.toHexString(bytes[3] & 0xff));
        passBitmap(bitmap);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());


        test();
    }

    public native void passBitmap(Bitmap bitmap); // 传递一个 Bitmap 给 NDK
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    public native void test();
}
