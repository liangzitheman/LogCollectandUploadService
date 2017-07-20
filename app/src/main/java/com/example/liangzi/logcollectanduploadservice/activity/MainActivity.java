package com.example.liangzi.logcollectanduploadservice.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.liangzi.logcollectanduploadservice.R;
import com.example.liangzi.logcollectanduploadservice.database.LocalDataBase;
import com.example.liangzi.logcollectanduploadservice.model.JankDataBean;

/**
 * Created by liangzi on 2017/7/18.
 */

public class MainActivity extends Activity {

    private static final String TAG = "MainActivity";
    private LocalDataBase localDataBase;
    private JankDataBean jankDataBean;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "provider is ok");

        localDataBase = LocalDataBase.getInstance(this);
        jankDataBean = new JankDataBean();
        jankDataBean.setAPPNAME("WeChat");
        jankDataBean.setCASENAME("Sending");

        localDataBase.InsertJankDataBean(jankDataBean);

        jankDataBean.setAPPNAME("AliPay");
        jankDataBean.setCASENAME("pay");
        localDataBase.InsertJankDataBean(jankDataBean);

        localDataBase.InsertJankDataBean(jankDataBean);



        localDataBase.deletefromJankDataBean(2);
    }
}

