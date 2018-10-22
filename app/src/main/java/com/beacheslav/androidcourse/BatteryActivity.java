package com.beacheslav.androidcourse;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class BatteryActivity extends AppCompatActivity {

    private TextView mContentTxt;
    private BroadcastReceiver mBatInfoReceiver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        mBatInfoReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context arg0, Intent intent) {
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, 0);
                if (BatteryManager.BATTERY_STATUS_CHARGING == status){
                    mContentTxt.setText("Батарея заряжается: "+ String.valueOf(level) + "%");
                }else {
                    mContentTxt.setText("Батарея работает от аккумулятора:"+ String.valueOf(level) + "%");
                }
            }
        };
        registerReceiver(mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        mContentTxt = (TextView) findViewById(R.id.text_view_battery);
    }
}
