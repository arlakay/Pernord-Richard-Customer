package com.com.rahmandev.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.com.rahmandev.BaseActivity;
import com.com.rahmandev.R;
import com.com.rahmandev.adapter.EventRecyclerAdapter;
import com.com.rahmandev.app.AppController;
import com.com.rahmandev.model.Event;
import com.com.rahmandev.utils.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rahmandev on 6/15/2016.
 */
public class ClubListActivity extends BaseActivity {
    private String TAG = ClubListActivity.class.getSimpleName();
    private static final String url = Constants.BASE_URL + "/transaction/event";
    private String jsonResponse;
    private JSONArray data;
    private List<Event> restaurantList;
    private EventRecyclerAdapter adapter;


    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.dummyfrag_scrollableview)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu1_mybottle);
        ButterKnife.bind(this);

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        /*restaurantList = new ArrayList<>();
        for (int i = 0; i < VersionModel.data.length; i++) {
            list.add(VersionModel.data[i]);
        }*/

        /*adapter = new EventRecyclerAdapter(restaurantList, new EventRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Event model) {
                String title = model.getJudul();
                String snipp = model.getSnippet();
                String isi = model.getIsi_berita();
                String gambar = model.getGambar();

                Intent intent = new Intent(getActivity(), DetailBeritaActivity.class);

                intent.putExtra("judul", title);
                intent.putExtra("snippet", snipp);
                intent.putExtra("isi", isi);
                intent.putExtra("gambar", gambar);

                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);*/

        getEventFromAPI();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    private void getEventFromAPI() {
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String rs = response.toString();
                Log.d(TAG, response.toString());
                try {
                    jsonResponse = "";
                    JSONObject job = new JSONObject(rs);
                    data = job.getJSONArray("store");

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject person = data.getJSONObject(i);

                        String id = person.getString("idstore");
                        String mid = person.getString("merchant_id");
                        String sid = person.getString("store_id");
                        String sname = person.getString("store_name");
                        String desc = person.getString("description");
                        String street = person.getString("street");
                        String city = person.getString("city");
                        String country = person.getString("country");
                        String phone = person.getString("phone");

                        Event event = new Event(id, mid, sid, sname, desc, street, city, country, phone);
                        restaurantList.add(event);

                        jsonResponse += "ID: "      + id        + "\n";
                        jsonResponse += "MID: "     + mid       + "\n";
                        jsonResponse += "SID: "     + sid       + "\n";
                        jsonResponse += "Name: "    + sname     + "\n";
                        jsonResponse += "Desc: "    + desc      + "\n";
                        jsonResponse += "Street: "  + street    + "\n";
                        jsonResponse += "City: "    + city      + "\n";
                        jsonResponse += "Country: " + country   + "\n";
                        jsonResponse += "Phone: "   + phone     + "\n";

                        Log.d(TAG, jsonResponse);
                        //txtResponse.setText(jsonResponse);

//                        Log.d(TAG, String.valueOf(lati));
//                        Log.d(TAG, String.valueOf(lngi));
//
//                        lati = Double.parseDouble(lat);
//                        lngi = Double.parseDouble(lng);
//
//                        LatLng koor = new LatLng(lati, lngi);
//
//                        Constants.BAY_AREA_LANDMARKS.put(sname,koor);
                        //Log.d(TAG, String.valueOf(Constants.BAY_AREA_LANDMARKS));

                    }
                    //txtResponse.setText(jsonResponse);
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        AppController.getInstance().addToRequestQueue(req);
    }
}
