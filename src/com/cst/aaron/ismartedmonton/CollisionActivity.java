package com.cst.aaron.ismartedmonton;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

public class CollisionActivity extends ActionBarActivity {
	
	ViewPager mViewPager;
//	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
//	private String[] tabsStrings={"Collision", "Filter"};
	FragmentManager fragmentManager=getSupportFragmentManager();
	FragmentTransaction fragmentTransaction;
	public void onCreate(Bundle savedInstanceSate) {
		super.onCreate(savedInstanceSate);
		setContentView(R.layout.activity_collision);	
		actionBar=getSupportActionBar();
	
		fragmentTransaction=fragmentManager.beginTransaction();
		Collision_chart chart=new Collision_chart();
		fragmentTransaction.add(R.id.parent_relativelayout, chart,"Collision_Chart");
		fragmentTransaction.commit();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.collision, menu);
		
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		int id=item.getItemId();
		if (id==R.id.action_filter) {
			Collision_filter filter=new Collision_filter();
			fragmentTransaction=fragmentManager.beginTransaction();
			fragmentTransaction.replace(R.id.parent_relativelayout, filter);
			fragmentTransaction.commit();
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	/*//	mViewPager=(ViewPager)findViewById(R.id.pager);
		actionBar=getSupportActionBar();
		mAdapter=new TabsPagerAdapter(getSupportFragmentManager());
		
		mViewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		
		for (String tab_nameString : tabsStrings) {
			actionBar.addTab(actionBar.newTab().setText(tab_nameString).setTabListener((TabListener) this));
		}		
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				actionBar.setSelectedNavigationItem(arg0);
				FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
			if (arg0==0) {
				Collision_chart fragment=new Collision_chart();
				ft.replace(R.id.pager, fragment);
				ft.addToBackStack(null);
				ft.commit();
			}else {
				Collision_filter fragment=new Collision_filter();
				ft.replace(R.id.pager, fragment);
				ft.addToBackStack(null);
				ft.commit();
			}
			
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
	public void onTabSelected(Tab tab, FragmentTransaction fTransaction) {
		// TODO Auto-generated method stub
		
		Log.v("hello", ""+tab.getPosition());
		mViewPager.setCurrentItem(tab.getPosition());
		FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
		if (tab.getPosition()==0) {
			Collision_chart fragment=new Collision_chart();
			ft.replace(R.id.pager, fragment);
			ft.addToBackStack(null);
			ft.commit();
		}else {
			Collision_filter fragment=new Collision_filter();
			ft.replace(R.id.pager, fragment);
			ft.addToBackStack(null);
			ft.commit();
		}
		
	}
	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}*/

	
}
