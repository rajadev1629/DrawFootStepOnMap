//package com.example.raja.maptest;
//
//import android.Manifest;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.PendingResult;
//import com.google.android.gms.common.api.ResultCallback;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationListener;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.LocationSettingsRequest;
//import com.google.android.gms.location.LocationSettingsResponse;
//import com.google.android.gms.location.LocationSettingsResult;
//import com.google.android.gms.location.SettingsClient;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//
//import static android.content.pm.PackageManager.PERMISSION_GRANTED;
//
//
//public class LocationHelperNew implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
//
//    private FusedLocationProviderClient mFusedLocationProviderClient;
//    private Location location;
//    private LocationRequest mLocationRequest;
//    private ResultCallback<LocationSettingsResult> resultCallback;
//    private LocationListener mLocationListener;
//    private Context mContext;
//
//
//    public LocationHelperNew(Context context, ResultCallback<LocationSettingsResult> resultResultCallback) {
//        this.mContext = context;
//        this.resultCallback = resultResultCallback;
//        buildGoogleApiClient();
//        createLocationRequest();
//    }
//
//
//    public void setLocationListener(LocationListener locationListener) {
//        this.mLocationListener = locationListener;
//    }
//
//    private void buildGoogleApiClient() {
//        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(mContext);
//    }
//
//    protected void createLocationRequest() {
//        mLocationRequest = new LocationRequest();
//        mLocationRequest.setInterval(1000);
//        mLocationRequest.setFastestInterval(1000);
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
//        builder.setAlwaysShow(true);
//
//        SettingsClient client = LocationServices.getSettingsClient(mContext);
//        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
//
//
//        PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
//        result.setResultCallback(resultCallback);
//    }
//
//    @Override
//    public void onConnected(@Nullable Bundle bundle) {
//        if (mContext.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == PERMISSION_GRANTED) {
//            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
//        }
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//        mGoogleApiClient.connect();
//    }
//
//    @Override
//    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//        buildGoogleApiClient();
//    }
//
//    @Override
//    public void onLocationChanged(Location location) {
//        this.location = location;
//        if (mLocationListener != null)
//            mLocationListener.onLocationChanged(location);
//    }
//
//
//    public boolean isConnected() {
//        return mGoogleApiClient != null && mGoogleApiClient.isConnected();
//    }
//
//    public void stopTrackingLocation() {
//        if (isConnected()) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
//        }
//    }
//
//    public void startTrackingLocation() {
//        if (mGoogleApiClient != null)
//            mGoogleApiClient.connect();
//    }
//
//    public void onDestroy() {
//        if (mGoogleApiClient != null) {
//            mGoogleApiClient.disconnect();
//            mGoogleApiClient = null;
//            mContext = null;
//        }
//    }
//
//    public void getLocation() {
//        if (mContext.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == PERMISSION_GRANTED) {
//            mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
//                @Override
//                public void onSuccess(Location location) {
//                    if (location != null) {
//
//                    }                }
//            });
//        }
//    }
//}