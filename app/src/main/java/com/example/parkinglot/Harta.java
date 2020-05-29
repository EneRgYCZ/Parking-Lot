package com.example.parkinglot;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Harta extends FragmentActivity implements OnMapReadyCallback
{
    LatLng PrimaParcare = new LatLng(44.1773748,28.6589111);
    LatLng ADouaParcare = new LatLng(44.1992839,28.6070247);
    LatLng ATreiaParcare = new LatLng(44.2030290,28.6320593);
    LatLng APatraParcare = new LatLng(44.2205018,28.6297885);
    LatLng ACinceaParcare = new LatLng(44.2168731,28.6381903);
    LatLng ASaseaParcare = new LatLng(44.178519,28.6461918);

    final String numeParcare1 = "Modern Beach Parking";
    final String numeParcare2 = "Vivo Shopping Center";
    final String numeParcare3 = "City Park Mall";
    final String numeParcare4 = "Aqua Magic";
    final String numeParcare5 = "Pavilionul Expozitional";
    final String numeParcare6 = "Tomis Mall";

    final String detalii1 = "Rating:3.9 || Locuri libere:20";
    final String detalii2 = "Rating:4.4 || Locuri libere:30";
    final String detalii3 = "Rating:4.0 || Locuri libere:142";
    final String detalii4 = "Rating:4.2 || Locuri libere:10";
    final String detalii5 = "Rating:4.5 || Locuri libere:12";
    final String detalii6 = "Rating:4.9 || Locuri libere:0";


    private GoogleMap mMap;

    LocationManager locationManager;

    LocationListener locationListener;

    private ClusterManager<MyItem> mClusterManager;

    private void setUpClusterer()
    {
        // Position the map.
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(45.4993885,25.5748931), 15));

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        mClusterManager = new ClusterManager<MyItem>(this, mMap);

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        mMap.setOnCameraIdleListener(mClusterManager);
        mMap.setOnMarkerClickListener(mClusterManager);

        // Add cluster items (markers) to the cluster manager.
        addItems();
    }

    private void addItems()
    {
        // Set some lat/lng coordinates to start with.
        double lat = 45.4993885;
        double lng = -25.5748931;

        // Add ten cluster items in close proximity, for purposes of this example.
        for (int i = 0; i < 10; i++) {
            double offset = i / 60d;
            lat = lat + offset;
            lng = lng + offset;
            MyItem offsetItem = new MyItem(lat, lng," "," ");
            mClusterManager.addItem(offsetItem);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harta);
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
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        mMap.setTrafficEnabled(true);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener()
        {
            @Override
            public void onLocationChanged(Location location)
            {
                LatLng locatie = new LatLng(location.getLatitude(), location.getLongitude());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras)
            {

            }

            @Override
            public void onProviderEnabled(String provider)
            {

            }

            @Override
            public void onProviderDisabled(String provider)
            {

            }
        };

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            Location lastKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            LatLng locatie = new LatLng(44.201859, 28.644579);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(locatie, 15));
            mMap.addMarker(new MarkerOptions().position(locatie));
        }

        mMap.setOnMarkerClickListener
                (new GoogleMap.OnMarkerClickListener()
                 {
                     @Override
                     public boolean onMarkerClick(Marker marker)
                     {
                         if (marker.isFlat() == true)
                         {
                             if (marker.getZIndex() == 1.0f)
                             {
                                 Intent intent = new Intent(Harta.this, Rezervation.class);
                                 intent.putExtra("numeParcare1", numeParcare1);
                                 intent.putExtra("detalii1", detalii1);
                                 startActivity(intent);
                             }
                             if (marker.getZIndex() == 2.0f)
                             {
                                 Intent intent2 = new Intent(Harta.this, Rezervation1.class);
                                 intent2.putExtra("numeParcare2", numeParcare2);
                                 intent2.putExtra("detalii2", detalii2);
                                 startActivity(intent2);
                             }
                             if (marker.getZIndex() == 3.0f)
                             {
                                 Intent intent3 = new Intent(Harta.this, Rezervation2.class);
                                 intent3.putExtra("numeParcare3", numeParcare3);
                                 intent3.putExtra("detalii3", detalii3);
                                 startActivity(intent3);
                             }
                             if (marker.getZIndex() == 4.0f)
                             {
                                 Intent intent4 = new Intent(Harta.this, Rezervation3.class);
                                 intent4.putExtra("numeParcare4", numeParcare4);
                                 intent4.putExtra("detalii4", detalii4);
                                 startActivity(intent4);
                             }
                             if (marker.getZIndex() == 5.0f)
                             {
                                 Intent intent5 = new Intent(Harta.this, Rezervation4.class);
                                 intent5.putExtra("numeParcare5", numeParcare5);
                                 intent5.putExtra("detalii5", detalii5);
                                 startActivity(intent5);
                             }
                             if (marker.getZIndex() == 6.0f)
                             {
                                 Toast.makeText(Harta.this,"Ne pare rau aceasta parcare nu mai are locuri disponibile",Toast.LENGTH_LONG);
                             }
                         }
                         return false;
                     }
                 }
                );

        Marker mPrimaParcare = mMap.addMarker(new MarkerOptions()
                .position(PrimaParcare)
                .title(numeParcare1)
                .snippet(detalii1)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .flat(true)
                .zIndex(1.0f)
        );

        Marker mADouaParcare = mMap.addMarker(new MarkerOptions()
                .position(ADouaParcare)
                .title(numeParcare2)
                .snippet(detalii2)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .flat(true)
                .zIndex(2.0f)
        );

        Marker mATreiaParcare = mMap.addMarker(new MarkerOptions()
                .position(ATreiaParcare)
                .title(numeParcare3)
                .snippet(detalii3)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .flat(true)
                .zIndex(3.0f)
        );

        Marker mAPatraParcare = mMap.addMarker(new MarkerOptions()
                .position(APatraParcare)
                .title(numeParcare4)
                .snippet(detalii4)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .flat(true)
                .zIndex(4.0f)
        );

        Marker mACinceaParcare = mMap.addMarker(new MarkerOptions()
                .position(ACinceaParcare)
                .title(numeParcare5)
                .snippet(detalii5)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                .flat(true)
                .zIndex(5.0f)
        );

        Marker mASaseaParcare = mMap.addMarker(new MarkerOptions()
                .position(ASaseaParcare)
                .title(numeParcare6)
                .snippet(detalii6)
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                .flat(true)
                .zIndex(6.0f)
        );
    }
}
