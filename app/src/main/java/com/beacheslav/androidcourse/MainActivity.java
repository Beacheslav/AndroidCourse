package com.beacheslav.androidcourse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private Button mBatteryButton;
    private Button mLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"method onCreate() called");

        mBatteryButton = (Button) findViewById(R.id.battery_button);
        mBatteryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BatteryActivity.class);
                startActivity(i);
            }
        });
        mLocationButton = (Button) findViewById(R.id.location_button);
        mLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(i);
            }
        });

        Toast.makeText(this, "Прошло 30 минут", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        Log.i(TAG,"method onStart() called");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i(TAG,"method onStop() called");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG,"method onDestroy() called");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.i(TAG,"method onResume() called");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i(TAG,"method onPause() called");
        super.onPause();
    }
}
