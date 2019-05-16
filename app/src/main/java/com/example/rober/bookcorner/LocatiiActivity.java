package com.example.rober.bookcorner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class LocatiiActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<LatLng> listaLocatii = new ArrayList<>();

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locatii);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        incarcaListaLocatii();
        mMap = googleMap;

        for (LatLng location : listaLocatii) {
            mMap.addMarker(new MarkerOptions().position(location));
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(listaLocatii.get(0), 5F));
    }

    private void incarcaListaLocatii() {
        LatLng bucuresti = new LatLng(44.4361414, 26.1027202);
        LatLng cluj = new LatLng(46.770439, 23.591423);
        LatLng constanta = new LatLng(45.6523093, 25.6102746);

        listaLocatii.add(bucuresti);
        listaLocatii.add(cluj);
        listaLocatii.add(constanta);
    }
}
