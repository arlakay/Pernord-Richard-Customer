package com.ilm.mydrinks.ui.bottomnavigation.clublist;

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
import com.ilm.mydrinks.adapter.ClubListAdapter;
import com.ilm.mydrinks.api.RestApi;
import com.ilm.mydrinks.api.services.ApiService;
import com.ilm.mydrinks.model.Club;
import com.ilm.mydrinks.model.ClubResponse;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ERD on 11/11/2016.
 */

public class ClubListFragment extends Fragment {
    private static final String TAG = "ClubListFragment";
    private Unbinder unbinder;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Club> clubList;
    private ClubListAdapter adapter2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club_list, container, false);
        ButterKnife.bind(this, view);

        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_club_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        getClubList();

        return view;
    }

    private void getClubList(){
        final ProgressDialog dialog = ProgressDialog.show(getActivity(), "", "loading...");

        ApiService apiService =
                RestApi.getClient().create(ApiService.class);

        Call<ClubResponse> call = apiService.getClubList();
        call.enqueue(new Callback<ClubResponse>() {
            @Override
            public void onResponse(Call<ClubResponse>call, Response<ClubResponse> response) {
                dialog.dismiss();

                clubList = response.body().getClubs();

                Log.d(TAG, "Status Code = " + response.code());
                Log.d(TAG, "Data received: " + new Gson().toJson(response.body()));

                adapter2 = new ClubListAdapter(clubList, R.layout.list_item_club, getActivity().getApplicationContext(), new ClubListAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Club model) {
                        String namaStore =  model.getStore_name();
                        String addrStore =  model.getStreet();
                        String cityStore = model.getCity_description();
                        String countryStore = model.getCountry_description();
                        String phoneStore = model.getPhone();

                        ClubListDetailFragment restaurantDetailFragment = new ClubListDetailFragment();
                        Bundle arguments = new Bundle();
                        arguments.putString("namaStore", namaStore);
                        arguments.putString("addrStore", addrStore);
                        arguments.putString("city", cityStore);
                        arguments.putString("country", countryStore);
                        arguments.putString("phone", phoneStore);
                        restaurantDetailFragment.setArguments(arguments);
                        getActivity().getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
                                .add(R.id.root_frame, restaurantDetailFragment, "clubdetailfragment")
                                .addToBackStack("clubdetailfragment")
                                .commit();

                    }
                });
                adapter2.notifyDataSetChanged();
                recyclerView.setAdapter(adapter2);
            }

            @Override
            public void onFailure(Call<ClubResponse>call, Throwable t) {
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

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }

}
