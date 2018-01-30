package com.example.raja.maptest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by raja on 18/1/18.
 */

public class GPSLocation extends AppCompatActivity {
    TextView txtLocation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.e("onCreate: ", "GPSLocation");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLocation = (TextView) findViewById(R.id.txt_location);
        fetchLocation();

    }

    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.e("fetchLocation: ", "Location Permission not granted");
            return;
        }
        final LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.e("onLocationChanged: ", location + "");
                if (location != null)
                    txtLocation.setText(location.getLatitude() + " , " + location.getLongitude());
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
                Log.e("onStatusChanged: ", s + " , " + i);
            }

            @Override
            public void onProviderEnabled(String s) {
                Log.e("onProviderEnabled: a", s);
                if (ActivityCompat.checkSelfPermission(GPSLocation.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(GPSLocation.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Log.e("onProviderEnabled: b", "permission issue");
                    return;
                }
                locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            }

            @Override
            public void onProviderDisabled(String s) {
                Log.e("onProviderDisabled: ", s);
            }
        };

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }
}
