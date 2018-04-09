package com.l.version;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.List;

import static android.graphics.Color.RED;

/**
 * Created by love_y on 11-07-2017.
 */

public class map extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    private View vw;
    private MenuInflater menuInflater;

    //for adding any of the menu in the fragment
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.v_map);

        mapFragment.getMapAsync(this);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vw = inflater.inflate(R.layout.map, container, false);
        //taking the button reference
       /* Button search  = (Button)vw.findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {


                EditText et = (EditText)vw.findViewById(R.id.destination);
                String location  = et.getText().toString();
                        if(location != null && !location.equals("")) {


                            Geocoder geocoder = new Geocoder(getActivity());
                            List<Address> list = null;
                            try {

                                list = geocoder.getFromLocationName(location, 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //here we are checking for the list
                            if (list != null && !list.isEmpty()) {
                                Address address = list.get(0);

                               String locality = address.getLocality();
                                Toast.makeText(getActivity(), locality, Toast.LENGTH_LONG).show();
                                goToZoomLocation(address.getLatitude(), address.getLongitude(), 15);
                              //  map.animateCamera(CameraUpdateFactory.newLatLngZoom(ltlng,15));
                            } else
                                Toast.makeText(getActivity(), "Please put the Right Location", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getActivity(),"Null String ",Toast.LENGTH_LONG).show();
                        }

            }
           });*/
        return vw;

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        addPolyLine();
        //    goToZoomLocation(10.754169, 78.820706,15);
       // if(Build.VERSION.SDK_INT >= Build.VERSION.CODES.M)
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        map.setMyLocationEnabled(true);

    }
            Marker marker;
    public  void goToZoomLocation(double lt,double lng,float zoom)
    {

        LatLng latLng = new LatLng(lt, lng);
        //MarkerOptions options = ;
        setMarker(zoom, latLng);

    }

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

        map.addMarker(new MarkerOptions().position(sp).title("Sapphire").snippet("Sapphire Hostel"));
        map.addMarker(new MarkerOptions().position(eee).title("EEE").snippet("EEE Auditorium"));
        map.addMarker(new MarkerOptions().position(ca).title("Lyceum").snippet("Department of CA"));
        map.addPolyline(new PolylineOptions().add(
               sp,eee,ca
        ).width(10)
        .color(RED)
         );
    }
    //for the side menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu,menu);
      super.onCreateOptionsMenu(menu, inflater);
    }


    //it is a getter we can't directly access the menu
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
 public  void geoLocate(View view) throws IOException
    {

 EditText et = (EditText)vw.findViewById(R.id.destination);
        String location  = et.getText().toString();
        Geocoder geocoder = new Geocoder(getActivity());
        List<Address> list = geocoder.getFromLocationName(location,1);
        Address address = list.get(0);

        String locality = address.getLocality();
        Toast.makeText(getActivity(),locality,Toast.LENGTH_LONG).show();
        goToZoomLocation(address.getLatitude(),address.getLongitude(),15);
    }
*/

}
