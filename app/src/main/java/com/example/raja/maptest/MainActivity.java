package com.example.raja.maptest;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

/**
 * Created by raja on 10/1/18.
 */

public class MainActivity extends AppCompatActivity{

    private LocationHelper locationHelper;
    private static final String TAG = "MainActivity";

    private TextView txtLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLocation = (TextView) findViewById(R.id.txt_location);
        initLocationHelper();


    }

    private void initLocationHelper() {
        locationHelper = new LocationHelper(MainActivity.this, new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                Status status = locationSettingsResult.getStatus();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:

                        break;
                }
            }
        });
        locationHelper.setLocationListener(new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                txtLocation.setText("Samir");
                if (location != null) {
                    Log.e("onLocationChanged: ", location.getLatitude() + " , " + location.getLongitude());
                    txtLocation.setText(location.getLatitude() + " , " + location.getLongitude());
                }
            }
        });
        locationHelper.startTrackingLocation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationHelper.stopTrackingLocation();
    }
}
