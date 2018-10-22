package com.beacheslav.androidcourse;

import android.Manifest;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class LocationActivity extends AppCompatActivity{

    private static final String TAG = "LocationActivity";
    private static final String[] LOCATION_PERMISSIONS = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,};
    private static final int REQUEST_LOCATION_PERMISSIONS = 0;

    private static final int REQUEST_ERROR = 0;

    private TextView mTextView;
    private GoogleApiClient mClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        mClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .build();

        mTextView = (TextView) findViewById(R.id.text_view_location);
        if (hasLocationPermission()) {
            findLocation();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mTextView.setText("Для получения данных о местоположении одобрите разрешение в всплывающем окне");
            requestPermissions(LOCATION_PERMISSIONS,
                    REQUEST_LOCATION_PERMISSIONS);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();

        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int errorCode = apiAvailability.isGooglePlayServicesAvailable(this);

        if (errorCode != ConnectionResult.SUCCESS){
            Dialog errorDialog = apiAvailability
                    .getErrorDialog(this, errorCode, REQUEST_ERROR, new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            //выйти если сервис недоступен.
                            finish();
                        }
                    });
            errorDialog.show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mClient.disconnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mClient = null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSIONS:
                if (hasLocationPermission()) {
                    findLocation();
                }
            default:
                super.onRequestPermissionsResult(requestCode, permissions,
                        grantResults);
        }
    }

    private void findLocation() throws SecurityException {
        LocationRequest request = LocationRequest.create();
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        request.setNumUpdates(1);
        request.setInterval(0);
        LocationServices.FusedLocationApi
                .requestLocationUpdates(mClient, request, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        mTextView.setText("Latitude: " + location.getLatitude() + " Longitude: " + location.getLongitude());
                    }
                });
    }

    private boolean hasLocationPermission() {
        int result = ContextCompat
                .checkSelfPermission(this, LOCATION_PERMISSIONS[0]);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}
