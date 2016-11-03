package com.com.rahmandev.ui.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 *
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<FragmentProfile> fragments = new ArrayList<>();
	private FragmentProfile currentFragment;

	public MainViewPagerAdapter(FragmentManager fm) {
		super(fm);

		fragments.clear();
		fragments.add(FragmentProfile.newInstance(0));
		fragments.add(FragmentProfile.newInstance(1));
		fragments.add(FragmentProfile.newInstance(2));
		fragments.add(FragmentProfile.newInstance(3));
		fragments.add(FragmentProfile.newInstance(4));
	}

	@Override
	public FragmentProfile getItem(int position) {
		return fragments.get(position);
	}

	@Override
	public int getCount() {
		return fragments.size();
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		if (getCurrentFragment() != object) {
			currentFragment = ((FragmentProfile) object);
		}
		super.setPrimaryItem(container, position, object);
	}

	/**
	 * Get the current fragment
	 */
	public FragmentProfile getCurrentFragment() {
		return currentFragment;
	}
}