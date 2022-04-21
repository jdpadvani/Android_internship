package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    protected LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        checkPermissionLocation(Manifest.permission.ACCESS_FINE_LOCATION);
        checkPermissionLocation(Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location)
        {
            LatLng marker = new LatLng(location.getLatitude(),  location.getLongitude());
            mMap.clear();
            mMap.addMarker(new MarkerOptions().position(marker).title("Marker in Ahmedabad"));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(marker, 25.0f));
        }
    };

    public void checkPermissionLocation(String permission) {
        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(MapActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(MapActivity.this, new String[]{permission}, 55);
        } else {
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100,
                    0, mLocationListener);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        LatLng sydney = new LatLng(23.0222662,  72.5704408);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Ahmedabad"));
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 25.0f));
        mMap.getUiSettings().setZoomControlsEnabled(true);
    }

    @SuppressLint("MissingPermission")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 55 && resultCode == Activity.RESULT_OK) {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
    }
}