package com.cst.aaron.ismartedmonton;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class CollisionActivity extends ActionBarActivity{
	
	
	private WebView webView;
	private ActionBar actionBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collision);
		actionBar=getActionBar();
		actionBar.setStackedBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.sign_button_pressed)));
		webView=(WebView)findViewById(R.id.collision_web);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.loadUrl("file:///android_asset/test.html");
		//webView.loadUrl("http://www.its.ualberta.ca/app/wcpa/CollisionPrediction");
		
		// add tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		ActionBar.TabListener tabListener=new ActionBar.TabListener() {

			@Override
			public void onTabSelected(android.app.ActionBar.Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabUnselected(android.app.ActionBar.Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTabReselected(android.app.ActionBar.Tab tab,
					android.app.FragmentTransaction ft) {
				// TODO Auto-generated method stub
				
			}
		};
		
		actionBar.addTab(actionBar.newTab().setText(getResources().getString(R.string.tab_collision)).setTabListener((TabListener) tabListener));	
		actionBar.addTab(actionBar.newTab().setText(getResources().getString(R.string.tab_collision_filter)).setTabListener((TabListener) tabListener));
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.login, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		  switch (item.getItemId()) {
	        case R.id.action_home_login:
	             Intent intent=new Intent(getApplicationContext(), MainActivity.class);   
	             startActivity(intent);
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
