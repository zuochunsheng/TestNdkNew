package com.huican.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.huican.test.R;
import com.huican.test.bean.DataBean;
import com.huican.test.NativeLibrary;

public class C2JavaActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "C2JavaActivity";

    private Button mGetDataBtn;
    private Button mSetDataBtn;

    private DataBean mDataBean ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c2_java);

        initView();
    }
    private void initView() {
        mGetDataBtn = (Button)findViewById(R.id.get_btn);
        mSetDataBtn = (Button)findViewById(R.id.set_btn);
        mGetDataBtn.setOnClickListener(this);
        mSetDataBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.get_btn:
                mDataBean = NativeLibrary.getDataFromNative();
                Log.e(TAG, "getDataFromNative: " + mDataBean);
                break;
            case R.id.set_btn:
                mDataBean.mInner.mMessage = "data from java";
                Log.e(TAG, "transferDataToNative: " + mDataBean);
                NativeLibrary.transferDataToNative(mDataBean);
                break;
        }
    }
}
