package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class education extends AppCompatActivity {

    Spinner spType;
    Button btFind;
    SupportMapFragment supportMapFragment;
    GoogleMap map;
    FusedLocationProviderClient fusedLocationProviderClient;
    double currentLat = 0, currentLong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education);

        spType = findViewById(R.id.sp_type);
        btFind = findViewById(R.id.bt_find);
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);

        String[] placeNameList = {"School","ATM", "Hospital", "Restaurant","PublicBuilding"};

        spType.setAdapter(new ArrayAdapter<>(education.this, android.R.layout.simple_spinner_dropdown_item, placeNameList));

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(education.this
                , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(education.this
                    , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }

        btFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNearbyEducation();
            }
        });
    }

    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {

            public void onSuccess(Location location) {

                if (location != null) {
                    currentLat = location.getLatitude();
                    currentLong = location.getLongitude();
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            map = googleMap;
                            map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(currentLat, currentLong), 10
                            ));
                        }
                    });
                }
            }
        });
    }

    private void displayNearbyEducation() {
        if (map != null) {
            map.clear(); // Clear previous markers

//            // Add markers for 2-3 nearby ATMs (hardcoded latitude and longitude values)
//            List<LatLng> atms = new ArrayList<>();
//            atms.add(new LatLng(18.489460, 73.813770)); //State bank atm
//            atms.add(new LatLng(9.990741, 76.287924)); //icici bank atm
//            atms.add(new LatLng(18.500350, 73.839140));//bom atm
//
//            for (LatLng atm : atms) {
//                map.addMarker(new MarkerOptions().position(atm).title("ATM"));
            List<LatLng> education = new ArrayList<>();
            education.add(new LatLng(18.4910, 73.8097)); // MMCOE
            education.add(new LatLng(18.4914, 73.8109)); // Millennium
            education.add(new LatLng(18.4861, 73.8162)); // Cummins

            List<String> educationNames = new ArrayList<>();
            educationNames.add("MMCOE");
            educationNames.add("Millennium school");
            educationNames.add("MKSSS's Cummins College of Engineering For Women");

            for (int i = 0; i < education.size(); i++) {
                LatLng educationLatLng = education.get(i);
                String educationName = educationNames.get(i);
                map.addMarker(new MarkerOptions().position(educationLatLng).title(educationName));
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }
}

