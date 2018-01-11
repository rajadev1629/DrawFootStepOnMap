package com.example.raja.maptest;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationHelper locationHelper;
    private List<Location> locationList = new ArrayList<>();

    private boolean isTrackingStart;
    private Polyline polyline;
    private PolylineOptions polylineOptions;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        initLocationHelper();
        polylineOptions = new PolylineOptions();
        polylineOptions.color(Color.GRAY);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));




    }

    private void initLocationHelper() {
        locationHelper = new LocationHelper(MapsActivity.this, new ResultCallback<LocationSettingsResult>() {
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
                drawMap(location);
            }
        });
        locationHelper.startTrackingLocation();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (locationHelper != null)
            locationHelper.startTrackingLocation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (locationHelper != null)
            locationHelper.stopTrackingLocation();
    }

    private void drawMap(Location location) {
        if (mMap == null || location == null)
            return;
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.addPolyline(polylineOptions.add(latLng));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        if (!isTrackingStart) {
            isTrackingStart = true;
            mMap.addMarker(new MarkerOptions().position(latLng).title("First Place"));
        }
    }
}
