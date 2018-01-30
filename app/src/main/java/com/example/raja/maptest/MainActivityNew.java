package com.example.raja.maptest;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.location.LocationListener;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by raja on 10/1/18.
 */

public class MainActivityNew extends AppCompatActivity{

    private LocationHelper locationHelper;
    private static final String TAG = "MainActivity";

    private TextView txtLocation;
    private LocationHelperLibrary mLocationHelperLibrary;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLocation = (TextView) findViewById(R.id.txt_location);
        initLocationHelperNew();

        ActivityCompat.requestPermissions(MainActivityNew.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                100);


    }

    private void initLocationHelperNew() {
        mLocationHelperLibrary = new LocationHelperLibrary(MainActivityNew.this);
        //mLocationHelperLibrary.startLocationUpdate();
        mLocationHelperLibrary.setLocationListener(new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                if (location != null) {
                    String data = location.getLatitude() + " , " + location.getLongitude() +"\n" + DateFormat.getTimeInstance().format(new Date());
                    txtLocation.setText(data);
                }
            }
        });

    }


    @Override
    protected void onPause() {
        super.onPause();
        mLocationHelperLibrary.stopTrackingLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume: "  );
        mLocationHelperLibrary.startTrackingLocation();
    }
}
