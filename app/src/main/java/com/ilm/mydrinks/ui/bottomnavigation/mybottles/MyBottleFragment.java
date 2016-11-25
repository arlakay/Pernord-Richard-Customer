package com.ilm.mydrinks.ui.bottomnavigation.mybottles;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ilm.mydrinks.R;
import com.ilm.mydrinks.adapter.MyBottleAdapter;
import com.ilm.mydrinks.api.RestApi;
import com.ilm.mydrinks.api.services.ApiService;
import com.ilm.mydrinks.model.MyBottle;
import com.ilm.mydrinks.model.MyBottleResponse;
import com.ilm.mydrinks.utility.SessionManager;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ERD on 11/11/2016.
 */

public class MyBottleFragment extends Fragment {
    @BindView(R.id.fragment_my_bottle_recycler_view)RecyclerView recyclerView;

    private static final String TAG = "MyBottleFragment";
    private SessionManager sessionManager;
    private RecyclerView.LayoutManager layoutManager;
    private List<MyBottle> myBottleList;
    private MyBottleAdapter adapter2;
    private String username;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_bottle, container, false);
        ButterKnife.bind(this, view);

        sessionManager = new SessionManager(getActivity().getApplicationContext());

        final HashMap<String, String> user = sessionManager.getUserDetails();
        username = user.get(SessionManager.KEY_CUSTOMER_CODE);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        getMyBottle(username);

        return view;
    }

    private void getMyBottle(final String customer_code){
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "loading...");

        ApiService apiService =
                RestApi.getClient().create(ApiService.class);

        Call<MyBottleResponse> call = apiService.myBottle(customer_code);
        call.enqueue(new Callback<MyBottleResponse>() {
            @Override
            public void onResponse(Call<MyBottleResponse>call, Response<MyBottleResponse> response) {
                dialog.dismiss();

                myBottleList = response.body().getMy_bottles();

                Log.d(TAG, "Status Code = " + response.code());
                Log.d(TAG, "Data received: " + new Gson().toJson(response.body()));

                adapter2 = new MyBottleAdapter(myBottleList, R.layout.list_item_club, getActivity().getApplicationContext(), new MyBottleAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(MyBottle model) {
                        String productName =  model.getProduct_description();
                        String purchaseDate =  model.getPurchase_date();
                        String keepDate = model.getKeep_date();
                        String takeDate = model.getTake_date();
                        String originalVolume = model.getOriginal_volume();
                        String lastVolume = model.getLast_volume();
                        String picture = model.getPicture();

                        MyBottleDetailFragment myBottleDetailFragment = new MyBottleDetailFragment();
                        Bundle arguments = new Bundle();
                        arguments.putString("productName", productName);
                        myBottleDetailFragment.setArguments(arguments);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
                                .add(R.id.root_frame, myBottleDetailFragment, "myBottleDetailFragment")
                                .addToBackStack("myBottleDetailFragment")
                                .commit();

                    }
                });
                adapter2.notifyDataSetChanged();
                recyclerView.setAdapter(adapter2);
            }

            @Override
            public void onFailure(Call<MyBottleResponse>call, Throwable t) {
                dialog.dismiss();

                Log.e(TAG, t.toString());

                AlertDialog alertDialog = new AlertDialog.Builder(getActivity()).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Kesalahan Jaringan");
                alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        getActivity().finish();
                        startActivity(getActivity().getIntent());
                    }
                });
                alertDialog.show();
            }
        });
    }


}
