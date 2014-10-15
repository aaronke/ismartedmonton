package com.cst.aaron.ismartedmonton;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class CollisionActivity extends FragmentActivity implements ActionBar.TabListener{
	
	ViewPager mViewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	private String[] tabsStrings={"Collicion", "Filter"};
	public void onCreate(Bundle savedInstanceSate) {
		super.onCreate(savedInstanceSate);
		setContentView(R.layout.swipe_view);	
		
		mViewPager=(ViewPager)findViewById(R.id.pager);
		actionBar=getActionBar();
		mAdapter=new TabsPagerAdapter(getSupportFragmentManager());
		
		mViewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		
		for (String tab_nameString : tabsStrings) {
			actionBar.addTab(actionBar.newTab().setText(tab_nameString).setTabListener(this));
		}		
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(arg0);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(tab.getPosition());
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
}
