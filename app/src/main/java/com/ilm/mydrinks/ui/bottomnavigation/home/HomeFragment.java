package com.ilm.mydrinks.ui.bottomnavigation.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ilm.mydrinks.R;
import com.ilm.mydrinks.ui.changepassword.ChangePasswordFragment;
import com.ilm.mydrinks.ui.myqr.MyQRFragment;
import com.ilm.mydrinks.ui.profile.ViewProfileFragment;
import com.ilm.mydrinks.ui.settings.SettingFragment;
import com.ilm.mydrinks.utility.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {
	@BindView(R.id.btn_home_logout)ImageView btnLogout;

	private static final String TAG = "HomeFragment";
	private Unbinder unbinder;
	private SessionManager sessionManager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_profile, container, false);
		ButterKnife.bind(this, view);

		sessionManager = new SessionManager(getActivity().getApplicationContext());

		return view;
	}

	@OnClick(R.id.btn_home_my_qr)
	public void myQR(View view) {
		MyQRFragment myQRFragment = new MyQRFragment();
		Bundle arguments = new Bundle();
//		arguments.putString("phone", phoneStore);
		myQRFragment.setArguments(arguments);
		getActivity().getSupportFragmentManager().beginTransaction()
				.setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
				.add(R.id.root_frame, myQRFragment, "myQRFragment")
				.addToBackStack("myQRFragment")
				.commit();
	}

	@OnClick(R.id.btn_home_change_password)
	public void changePassword(View view) {
		ChangePasswordFragment changePasswordFragment = new ChangePasswordFragment();
		Bundle arguments = new Bundle();
//		arguments.putString("phone", phoneStore);
		changePasswordFragment.setArguments(arguments);
		getActivity().getSupportFragmentManager().beginTransaction()
				.setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
				.add(R.id.root_frame, changePasswordFragment, "changePasswordFragment")
				.addToBackStack("changePasswordFragment")
				.commit();
	}

	@OnClick(R.id.btn_home_profile)
	public void viewProfile(View view) {
		ViewProfileFragment viewProfileFragment = new ViewProfileFragment();
		Bundle arguments = new Bundle();
//		arguments.putString("phone", phoneStore);
		viewProfileFragment.setArguments(arguments);
		getActivity().getSupportFragmentManager().beginTransaction()
				.setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
				.add(R.id.root_frame, viewProfileFragment, "viewProfileFragment")
				.addToBackStack("viewProfileFragment")
				.commit();
	}

	@OnClick(R.id.btn_home_settings)
	public void settings(View view) {
		SettingFragment settingFragment = new SettingFragment();
		Bundle arguments = new Bundle();
//		arguments.putString("phone", phoneStore);
		settingFragment.setArguments(arguments);
		getActivity().getSupportFragmentManager().beginTransaction()
				.setCustomAnimations(R.anim.activityslidein, R.anim.activityslideinout, R.anim.activityslideoutpop, R.anim.activityslideout)
				.add(R.id.root_frame, settingFragment, "settingFragment")
				.addToBackStack("settingFragment")
				.commit();
	}

	@OnClick(R.id.btn_home_logout)
	public void logout(View view) {
		if (sessionManager.isLoggedIn()) {
			sessionManager.setLogin(false);
			sessionManager.logoutUser();
		}
	}

}
