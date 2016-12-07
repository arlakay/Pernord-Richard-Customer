package com.ilm.mydrinks.ui.main;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.ilm.mydrinks.BaseActivity;
import com.ilm.mydrinks.R;
import com.ilm.mydrinks.ui.bottomnavigation.clublist.ClubListFragment;
import com.ilm.mydrinks.ui.bottomnavigation.home.HomeFragment;
import com.ilm.mydrinks.ui.bottomnavigation.mybottles.MyBottleFragment;
import com.ilm.mydrinks.ui.bottomnavigation.mypoints.MyPointFragment;
import com.ilm.mydrinks.ui.bottomnavigation.notifications.NotificationFragment;
import com.ilm.mydrinks.utility.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.view_pager)AHBottomNavigationViewPager viewPager;
    @BindView(R.id.bottom_navigation)AHBottomNavigation bottomNavigation;
    @BindView(R.id.txt_name)TextView txtName;
    @BindView(R.id.txt_points)TextView txtPoint;

    private MainPagerAdapter2 mPagerAdapter;
    private AHBottomNavigationAdapter navigationAdapter;
    private ArrayList<AHBottomNavigationItem> bottomNavigationItems = new ArrayList<>();
    private boolean useMenuResource = true;
    private int[] tabColors;
    private FloatingActionButton floatingActionButton;
    private SessionManager sessionManager;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        fragmentManager = getFragmentManager();

        sessionManager = new SessionManager(getApplicationContext());
        sessionManager.checkLogin();

        if (!sessionManager.isLoggedIn()) {
            sessionManager.setLogin(false);
            sessionManager.logoutUser();
            finish();
        }

        HashMap<String, String> user = sessionManager.getUserDetails();
        String firstName = user.get(SessionManager.KEY_FIRST_NAME);
        String lastName = user.get(SessionManager.KEY_LAST_NAME);
        String point = user.get(SessionManager.KEY_POINT_BALANCE);

        txtName.setText(firstName + " " + lastName);
        txtPoint.setText(point + " pts");

        initUI();

        viewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);
        mPagerAdapter = new MainPagerAdapter2(getSupportFragmentManager());
        viewPager.setAdapter(mPagerAdapter);

    }

    private void initUI() {

        if (useMenuResource) {
            tabColors = getApplicationContext().getResources().getIntArray(R.array.tab_colors);
            navigationAdapter = new AHBottomNavigationAdapter(this, R.menu.bottom_navigation_menu_5);
            navigationAdapter.setupWithBottomNavigation(bottomNavigation, tabColors);

            bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
            bottomNavigation.setAccentColor(Color.parseColor("#333333"));
            bottomNavigation.setInactiveColor(Color.parseColor("#999999"));
            bottomNavigation.setForceTint(true);
            bottomNavigation.setForceTitlesDisplay(true);
            bottomNavigation.setBehaviorTranslationEnabled(false);

        } else {
            AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.ic_home_grey600_36dp, R.color.color_tab_1);
            AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_2, R.drawable.ic_battery_full_grey600_36dp, R.color.color_tab_2);
            AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_3, R.drawable.ic_star_border_black_36dp, R.color.color_tab_3);
            AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_4, R.drawable.ic_language_grey600_36dp, R.color.color_tab_4);
            AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.tab_5, R.drawable.ic_info_outline_grey600_36dp, R.color.color_tab_5);

            bottomNavigationItems.add(item1);
            bottomNavigationItems.add(item2);
            bottomNavigationItems.add(item3);
            bottomNavigationItems.add(item4);
            bottomNavigationItems.add(item5);

            bottomNavigation.addItems(bottomNavigationItems);

            bottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
            bottomNavigation.setAccentColor(Color.parseColor("#333333"));
            bottomNavigation.setInactiveColor(Color.parseColor("#999999"));
            bottomNavigation.setForceTint(true);
            bottomNavigation.setForceTitlesDisplay(true);
            bottomNavigation.setBehaviorTranslationEnabled(false);

        }

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                if (position == 0){
                    HomeFragment homeFragment = new HomeFragment();
                    Bundle arguments = new Bundle();
    //                    arguments.putString("Id", txtId);
                    homeFragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
                            .replace(R.id.root_frame, homeFragment, "homeFragment")
                            .commit();
                }
                if (position == 1) {

                    MyBottleFragment myBottleFragment = new MyBottleFragment();
                    Bundle arguments = new Bundle();
    //                    arguments.putString("Id", txtId);
                    myBottleFragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
                            .replace(R.id.root_frame, myBottleFragment, "myBottleFragment")
                            .commit();

                }
                if (position == 2){
                    MyPointFragment myPointFragment = new MyPointFragment();
                    Bundle arguments = new Bundle();
    //                    arguments.putString("Id", txtId);
                    myPointFragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
                            .replace(R.id.root_frame, myPointFragment, "myPointFragment")
                            .commit();
                }
                if (position == 3){
                    ClubListFragment clubListFragment = new ClubListFragment();
                    Bundle arguments = new Bundle();
    //                    arguments.putString("Id", txtId);
                    clubListFragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
                            .replace(R.id.root_frame, clubListFragment, "clubListFragment")
                            .commit();
                }
                if (position == 4){
                    NotificationFragment notificationFragment = new NotificationFragment();
                    Bundle arguments = new Bundle();
    //                    arguments.putString("Id", txtId);
                    notificationFragment.setArguments(arguments);
                    getSupportFragmentManager().beginTransaction()
                            .setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
                            .replace(R.id.root_frame, notificationFragment, "notificationFragment")
                            .commit();
                }
                else {

                }
            return true;
            }
        });

//        bottomNavigation.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
//            @Override public void onPositionChange(int y) {
//                Log.d("DemoActivity", "BottomNavigation Position: " + y);
//            }
//        });

//        viewPager.setOffscreenPageLimit(4);
//        adapter = new MainViewPagerAdapter(getSupportFragmentManager());
//        viewPager.setAdapter(adapter);

//        currentFragment = adapter.getCurrentFragment();

//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Setting custom colors for notification
//                AHNotification notification = new AHNotification.Builder()
//                        .setText(":)")
//                        .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.color_notification_back))
//                        .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.color_notification_text))
//                        .build();
//                bottomNavigation.setNotification(notification, 1);
//                Snackbar.make(bottomNavigation, "Snackbar with bottom navigation",
//                        Snackbar.LENGTH_SHORT).show();
//            }
//        }, 3000);

//        bottomNavigation.setDefaultBackgroundResource(R.drawable.bottom_navigation_background);
    }

    public void confirmExit(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit");
        alertDialogBuilder.setMessage("Are you sure you want to exit?");
        alertDialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                doubleBackToExitPressedOnce = false;
                finish();
            }
        });
        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doubleBackToExitPressedOnce = false;
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        //Checking for fragment count on backstack
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else if (!doubleBackToExitPressedOnce) {
            this.doubleBackToExitPressedOnce = true;
            confirmExit();
//            Toast.makeText(this,"Please click BACK again to exit.", Toast.LENGTH_SHORT).show();
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    doubleBackToExitPressedOnce = false;
//                }
//            }, 2000);
        } else {
            super.onBackPressed();
            return;
        }
    }

}
