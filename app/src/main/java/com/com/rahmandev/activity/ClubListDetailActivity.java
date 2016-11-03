package com.com.rahmandev.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.com.rahmandev.R;
import com.com.rahmandev.ui.main.MainActivity;
import com.com.rahmandev.utility.SessionManager;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClubListDetailActivity extends AppCompatActivity implements OnMapReadyCallback{
    @BindView(R.id.txt_name)TextView txtName;
    @BindView(R.id.txt_points)TextView txtPoint;
    @BindView(R.id.txt_name_club)TextView txtNamaClub;
    @BindView(R.id.txt_description_club)TextView txtDescClub;

    private SessionManager sessionManager;
    private GoogleMap mMap;
    private String nama, addr, city, country, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_list_detail);
        ButterKnife.bind(this);

        sessionManager = new SessionManager(getApplicationContext());

        HashMap<String, String> user = sessionManager.getUserDetails();
        String firstName = user.get(SessionManager.KEY_FIRST_NAME);
        String lastName = user.get(SessionManager.KEY_LAST_NAME);
        String point = user.get(SessionManager.KEY_POINT_BALANCE);

        getData();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        txtName.setText(firstName + " " + lastName);
        txtPoint.setText(point + " pts");
        txtNamaClub.setText(nama);
        txtDescClub.setText(addr);
    }

    public void getData(){
        Intent i = getIntent();
        nama = i.getStringExtra("namaStore");
        addr = i.getStringExtra("addrStore");
        city = i.getStringExtra("city");
        country = i.getStringExtra("country");
        phone = i.getStringExtra("phone");
    }

    @OnClick(R.id.btn_club_list_detail_close)
    public void closeToMain(View view) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-6.1461135,106.8147041);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Club"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
