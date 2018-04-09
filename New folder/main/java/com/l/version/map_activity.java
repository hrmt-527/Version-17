package com.l.version;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.graphics.Color.RED;

public class map_activity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap map;
    private View vw;
    private MenuInflater menuInflater;
    private ActionBar supportActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_map_activity);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        //supportActionBar.setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
        map = googleMap;
        addPolyLine();
        goToLocationZoom(10.754352, 78.819783, 15);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission( Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission( Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
        }
        map.setMyLocationEnabled(true);
    }
    public  void goToZoomLocation(double lt,double lng,float zoom)
    {

        LatLng latLng = new LatLng(lt, lng);
        //MarkerOptions options = ;
        setMarker(zoom, latLng);

    }
    public void goToLocationZoom(double lt,double lng,float zoom)
    {
        LatLng latLng = new LatLng(lt, lng);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng , zoom);
        map.moveCamera(cameraUpdate);
    }
    Marker marker = null;
    private void setMarker(float zoom, LatLng latLng) {
        if(marker != null)
        {
            marker.remove();
        }

        marker  =  map.addMarker(new MarkerOptions().position(latLng));
        addPolyLine();
        //map.addMarker(options);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
    }

    //in this function we are adding the marker to the places where needed
    public void addPolyLine()
    {
        LatLng sp = new LatLng(10.765675, 78.814412);
        LatLng eee = new LatLng(10.759158, 78.814657);
        LatLng ca = new LatLng(10.760618, 78.817359);
        LatLng gate = new LatLng(10.754192, 78.819805);
        LatLng mess = new LatLng(10.762762, 78.812909);
        LatLng atm = new LatLng(10.760892, 78.819050);
        LatLng eatm = new LatLng(10.759432, 78.813989);
        map.addMarker(new MarkerOptions().position(sp).title("Sapphire").snippet("Sapphire Hostel"));
        map.addMarker(new MarkerOptions().position(eee).title("EEE").snippet("EEE Auditorium"));
        map.addMarker(new MarkerOptions().position(ca).title("Lyceum").snippet("Department of CA"));
        map.addMarker(new MarkerOptions().position(gate).title("Main Gate").snippet("National Institute of Technology,Tiruchirapalli"));
        map.addMarker(new MarkerOptions().position(mess).title("Mess A").snippet("Mess"));
        map.addMarker(new MarkerOptions().position(atm).title("State Bank ATM").snippet("Buhari"));
        map.addMarker(new MarkerOptions().position(eatm).title("State Bank ATM").snippet("Front of EEE"));

        map.addPolyline(new PolylineOptions().add(
                sp,eee,ca,gate,mess,atm,eatm
                ).width(10)
                        .color(RED)
        );
    }
    //for the side menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);

    }


    public MenuInflater getMenuInflater() {
        return menuInflater;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.mapTypeNone:
                map.setMapType(GoogleMap.MAP_TYPE_NONE);break;
            case R.id.mapTypeHybrid:
                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);break;
            case R.id.mapTypeNormal:
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);break;
            case R.id.mapTypeSetelite:
                map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);break;
            case R.id.mapTypeTerrain:
                map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);break;
            default: break;
        }
        return super.onOptionsItemSelected(item);
    }
/*
 @RequiresApi(api = Build.VERSION_CODES.KITKAT)
 public  void geoLocate(View v) throws IOException
    {

 EditText et = (EditText)findViewById(R.id.destination);
        String location  = et.getText().toString();
        if(!Objects.equals(location,null))
        Toast.makeText(map_activity.this, location , Toast.LENGTH_SHORT ).show();
        Geocoder geocoder = new Geocoder(this);
        //location and max number of result
       ArrayList<Address> list = (ArrayList<Address>) geocoder.getFromLocationName(location , 2);
        Address address = list.get(1);
        String locality = address.getLocality();
        Toast.makeText(map_activity.this,locality,Toast.LENGTH_LONG).show();
        goToZoomLocation(address.getLatitude(),address.getLongitude(),15);
    }*/


    public ActionBar getSupportActionBar() {
        return supportActionBar;
    }
}
