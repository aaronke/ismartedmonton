package com.cst.aaron.ismartedmonton;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter{

	public TabsPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index) {
		// TODO Auto-generated method stub
		
		switch (index) {
		case 0:
			return  new Collision_chart();
		case 1:
			return new Collision_filter();
		default:
			break;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
