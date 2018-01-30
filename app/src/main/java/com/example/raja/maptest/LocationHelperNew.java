//package com.example.raja.maptest;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.IntentSender;
//import android.content.pm.PackageManager;
//import android.location.Location;
//import android.location.LocationManager;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v4.app.ActivityCompat;
//
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.api.GoogleApiClient;
//import com.google.android.gms.common.api.PendingResult;
//import com.google.android.gms.common.api.ResolvableApiException;
//import com.google.android.gms.common.api.ResultCallback;
//import com.google.android.gms.location.FusedLocationProviderClient;
//import com.google.android.gms.location.LocationCallback;
//import com.google.android.gms.location.LocationListener;
//import com.google.android.gms.location.LocationRequest;
//import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.LocationSettingsRequest;
//import com.google.android.gms.location.LocationSettingsResponse;
//import com.google.android.gms.location.LocationSettingsResult;
//import com.google.android.gms.location.SettingsClient;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//
//import static android.content.pm.PackageManager.PERMISSION_GRANTED;
//
//
//public class LocationHelperNew implements LocationListener, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
//
//    private FusedLocationProviderClient mFusedLocationProviderClient;
//    private Location mCurrentLocation;
//    private LocationRequest mLocationRequest;
//    private ResultCallback<LocationSettingsResult> resultCallback;
//    private LocationListener mLocationListener;
//    private Context mContext;
//
//    private boolean mRequestingLocationUpdates;
//    private LocationCallback mLocationCallback;
//
//
//    public LocationHelperNew(Context context) {
//        this.mContext = context;
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
//    public void getLocation() {
//        if (mContext.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") == PERMISSION_GRANTED) {
//            mFusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
//                @Override
//                public void onSuccess(Location location) {
//                    if (location != null) {
//                        mCurrentLocation = location;
//                    }
//                }
//            });
//        }
//    }
//
//    protected void createLocationRequest() {
//        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            return;
//        }
//        LocationManager locationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
//        android.location.LocationListener locationListener = new android.location.LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//
//            }
//
//            @Override
//            public void onStatusChanged(String s, int i, Bundle bundle) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String s) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String s) {
//
//            }
//        };
//
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//
//        mLocationRequest = new LocationRequest();
//        mLocationRequest.setInterval(1000);
//        mLocationRequest.setFastestInterval(1000);
//        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
//        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder().addLocationRequest(mLocationRequest);
//        SettingsClient client = LocationServices.getSettingsClient(mContext);
//        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());
//        task.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
//            @Override
//            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {
//
//            }
//        });
//
//        task.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                if (e instanceof ResolvableApiException) {
//                    ResolvableApiException resolvableApiException = (ResolvableApiException) e;
//                    try {
//                        resolvableApiException.startResolutionForResult((Activity) mContext, 0x1);
//                    } catch (IntentSender.SendIntentException e1) {
//                        e1.printStackTrace();
//                    }
//
//                }
//            }
//        });
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
//        mFusedLocationProviderClient.removeLocationUpdates(mLocationCallback);
//    }
//
//    public void startTrackingLocation() {
//        if (mRequestingLocationUpdates)
//            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                return;
//            }
//            mFusedLocationProviderClient.requestLocationUpdates(mLocationRequest, mLocationCallback, null);
//    }
//
//    public void onDestroy() {
//        if (mGoogleApiClient != null) {
//            mGoogleApiClient.disconnect();
//            mGoogleApiClient = null;
//            mContext = null;
//        }
//    }
//}