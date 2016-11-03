package com.com.rahmandev.ui.main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.com.rahmandev.R;
import com.com.rahmandev.activity.ChangePasswordActivity;
import com.com.rahmandev.activity.ClubListDetailActivity;
import com.com.rahmandev.activity.MyBottleDetailActivity;
import com.com.rahmandev.activity.MyPointDetailActivity;
import com.com.rahmandev.activity.ProfileDetailActivity;
import com.com.rahmandev.activity.SettingsActivity;
import com.com.rahmandev.adapter.ClubListAdapter;
import com.com.rahmandev.api.RestApi;
import com.com.rahmandev.api.services.ApiService;
import com.com.rahmandev.model.Club;
import com.com.rahmandev.model.ClubResponse;
import com.com.rahmandev.ui.myqr.MyQRActivity;
import com.com.rahmandev.utility.SessionManager;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentProfile extends Fragment {
    private FrameLayout fragmentContainer;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private SessionManager sessionManager;
    private static final String TAG = FragmentProfile.class.getSimpleName();
    private List<Club> clubList;
    private ClubListAdapter adapter2;

    /**
     * Create a new instance of the fragment
     */
    public static FragmentProfile newInstance(int index) {
        FragmentProfile fragment = new FragmentProfile();
        Bundle b = new Bundle();
        b.putInt("index", index);
        fragment.setArguments(b);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getArguments().getInt("index", 0) == 0) {
            View view = inflater.inflate(R.layout.fragment_profile, container, false);
            initHomeView(view);
            sessionManager = new SessionManager(getActivity().getApplicationContext());
            return view;
        } if (getArguments().getInt("index", 1) == 1) {
            View view = inflater.inflate(R.layout.fragment_my_bottle, container, false);
            initMyBottle(view);
            return view;
        } if (getArguments().getInt("index", 2) == 2) {
            View view = inflater.inflate(R.layout.fragment_my_points, container, false);
            initMyPoints(view);
            return view;
        } if (getArguments().getInt("index", 3) == 3) {
            View view = inflater.inflate(R.layout.fragment_club_list, container, false);
            initClubList2(view);
            return view;
        } if (getArguments().getInt("index", 4) == 4) {
            View view = inflater.inflate(R.layout.fragment_notifications, container, false);
//            initDemoSettings(view);
            return view;
        } else {
            View view = inflater.inflate(R.layout.fragment_demo_list, container, false);
//            initDemoList(view);
            return view;
        }
    }

    private void initHomeView(View view){
        final ImageButton btnHomeMyQR = (ImageButton) view.findViewById(R.id.btn_home_my_qr);
        final ImageButton btnHomeChangePass = (ImageButton) view.findViewById(R.id.btn_home_change_password);
        final ImageButton btnHomeProfile = (ImageButton) view.findViewById(R.id.btn_home_profile);
        final ImageButton btnHomeSettings = (ImageButton) view.findViewById(R.id.btn_home_settings);
        final ImageButton btnHomeLogout = (ImageButton) view.findViewById(R.id.btn_home_logout);

        btnHomeMyQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MyQRActivity.class);
                startActivity(i);
            }
        });

        btnHomeChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        btnHomeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ProfileDetailActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        btnHomeSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SettingsActivity.class);
                startActivity(i);
            }
        });

        btnHomeLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sessionManager.isLoggedIn()) {
                    sessionManager.setLogin(false);
                    sessionManager.logoutUser();
                    getActivity().finish();
                }
            }
        });

    }

    private void initMyPoints(View view){
        final Button btnPoint1 = (Button) view.findViewById(R.id.btn_my_point_1);
        final Button btnPoint2 = (Button) view.findViewById(R.id.btn_my_point_2);
        final Button btnPoint3 = (Button) view.findViewById(R.id.btn_my_point_3);

        btnPoint1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MyPointDetailActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
    }

    private void initMyBottle(View view){
        final Button btnBottle1 = (Button) view.findViewById(R.id.btn_my_bottle_1);
        final Button btnBottle2 = (Button) view.findViewById(R.id.btn_my_bottle_2);
        final Button btnBottle3 = (Button) view.findViewById(R.id.btn_my_bottle_3);
        final Button btnBottle4 = (Button) view.findViewById(R.id.btn_my_bottle_4);
        final Button btnBottle5 = (Button) view.findViewById(R.id.btn_my_bottle_5);
        final Button btnBottle6 = (Button) view.findViewById(R.id.btn_my_bottle_6);
        final Button btnBottleClose = (Button) view.findViewById(R.id.btn_my_bottle_close);

        btnBottleClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });

        btnBottle1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MyBottleDetailActivity.class);
                i.putExtra("bottle_name","Glenlivet");
                startActivity(i);
//                getActivity().finish();
            }
        });

        btnBottle2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MyBottleDetailActivity.class);
                i.putExtra("bottle_name","Martell XO");
                startActivity(i);
//                getActivity().finish();
            }
        });

        btnBottle3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MyBottleDetailActivity.class);
                i.putExtra("bottle_name","Martell Cordon Bleu");
                startActivity(i);
//                getActivity().finish();
            }
        });

        btnBottle4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MyBottleDetailActivity.class);
                i.putExtra("bottle_name","Martell VSOP XO");
                startActivity(i);
//                getActivity().finish();
            }
        });

        btnBottle5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MyBottleDetailActivity.class);
                i.putExtra("bottle_name","Chivas");
                startActivity(i);
//                getActivity().finish();
            }
        });

        btnBottle6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MyBottleDetailActivity.class);
                i.putExtra("bottle_name","Absolut Vodka");
                startActivity(i);
//                getActivity().finish();
            }
        });

    }

//    private void initDemoSettings(View view) {
//
//        final MainActivity demoActivity = (MainActivity) getActivity();
//        final SwitchCompat switchColored = (SwitchCompat) view.findViewById(R.id.fragment_demo_switch_colored);
//        final SwitchCompat showHideBottomNavigation = (SwitchCompat) view.findViewById(R.id.fragment_demo_show_hide);
//        final SwitchCompat showSelectedBackground = (SwitchCompat) view.findViewById(R.id.fragment_demo_selected_background);
//        final SwitchCompat switchForceTitleHide = (SwitchCompat) view.findViewById(R.id.fragment_demo_force_title_hide);
//
//        switchColored.setChecked(demoActivity.isBottomNavigationColored());
//
//        switchColored.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                demoActivity.updateBottomNavigationColor(isChecked);
//            }
//        });
//        showHideBottomNavigation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                demoActivity.showOrHideBottomNavigation(isChecked);
//            }
//        });
//        showSelectedBackground.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                demoActivity.updateSelectedBackgroundVisibility(isChecked);
//            }
//        });
//        switchForceTitleHide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                demoActivity.setForceTitleHide(isChecked);
//            }
//        });
//    }

    private void initClubList2(View view) {

//        fragmentContainer = (FrameLayout) view.findViewById(R.id.fragment_container);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_club_list_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        getClubList();

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

                        Intent intent = new Intent(getActivity(), ClubListDetailActivity.class);
                        intent.putExtra("namaStore", namaStore);
                        intent.putExtra("addrStore", addrStore);
                        intent.putExtra("city", cityStore);
                        intent.putExtra("country", countryStore);
                        intent.putExtra("phone", phoneStore);
                        startActivity(intent);
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

    /**
     * Refresh
     */
    public void refresh() {
        if (getArguments().getInt("index", 0) > 0 && recyclerView != null) {
            recyclerView.smoothScrollToPosition(0);
        }
    }

    /**
     * Called when a fragment will be displayed
     */
    public void willBeDisplayed() {
        // Do what you want here, for example animate the content
        if (fragmentContainer != null) {
            Animation fadeIn = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_in);
            fragmentContainer.startAnimation(fadeIn);
        }
    }

    /**
     * Called when a fragment will be hidden
     */
    public void willBeHidden() {
        if (fragmentContainer != null) {
            Animation fadeOut = AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out);
            fragmentContainer.startAnimation(fadeOut);
        }
    }
}
