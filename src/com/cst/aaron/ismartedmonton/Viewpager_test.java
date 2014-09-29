package com.cst.aaron.ismartedmonton;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.app.ActionBar.Tab;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Viewpager_test extends FragmentActivity{
	
	ViewPager mViewPager;
	public void onCreate(Bundle savedInstanceSate) {
		super.onCreate(savedInstanceSate);
		setContentView(R.layout.swipe_view);
		final ActionBar actionBar=getActionBar();
		
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		
		ActionBar.TabListener tabListener=new ActionBar.TabListener() {
			
			@Override
			public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabSelected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
				// TODO Auto-generated method stub
				mViewPager.setCurrentItem(arg0.getPosition());
			}
		};
		
		for (int i = 0; i < 3; i++) {
			actionBar.addTab(actionBar.newTab().setText("tab"+(i+1)).setTabListener(tabListener));
		}
		
		mViewPager=(ViewPager)findViewById(R.id.pager);
		mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){

			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				super.onPageSelected(position);
				getActionBar().setSelectedNavigationItem(position);
			}
			
			
		});
		
	}
	
	public class DemocellectionpagerAdapter extends FragmentStatePagerAdapter {

		public DemocellectionpagerAdapter(FragmentManager fm) {
			super(fm);
			// TODO Auto-generated constructor stub
		}

		@Override
		public Fragment getItem(int arg0) {
			// TODO Auto-generated method stub
			Fragment fragment=new DemoObjectFragment();
			Bundle args=new Bundle();
			args.putInt(DemoObjectFragment.ARG_OBJECT, arg0+1);
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			// TODO Auto-generated method stub
			return "OBJECT"+(position+1);
		}
		
	}
	
	public static class DemoObjectFragment extends Fragment{
		public static final String ARG_OBJECT="object";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			
			View rootView=inflater.inflate(R.layout.swipe_view, container,false);
			Bundle args=getArguments();
			((TextView)rootView.findViewById(android.R.id.text1)).setText(Integer.toString(args.getInt(ARG_OBJECT)));
			return rootView;
		}
		
	}
}