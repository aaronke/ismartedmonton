package com.cst.aaron.ismartedmonton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.ActionBar.TabListener;
import android.content.Intent;
import android.database.CursorJoiner.Result;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
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
		webView.loadUrl("file:///android_asset/collision.html");
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
		readCSV();
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
	public String readCSV(){
		JSONArray jsonArray=new JSONArray();
		try {
			InputStreamReader iStreamReader=new InputStreamReader(getAssets().open("data.csv"));
			BufferedReader reader=new BufferedReader(iStreamReader);			
			String lineString=null;
			int i=0;
			JSONObject jsonObject=new JSONObject();
			
			while ((lineString=reader.readLine())!=null) {
				
				String[] rowData =lineString.split(",");
				
				String dates=rowData[1];
				String cad=rowData[40];
				String mvci=rowData[38];
				String ftc=rowData[48];
				String fatalInjury=rowData[39];
				String stpk=rowData[49];
				String chln=rowData[50];
				String raof=rowData[51];
				String fm=rowData[45];
				String odds=rowData[41];
				i++;
				Log.v("test", dates);
				Calendar calendar=Calendar.getInstance();
				Date todayDate=new Date(System.currentTimeMillis());
				Date sevenDaysBeforeDoday=new Date(calendar.YEAR, calendar.MONTH, calendar.DAY_OF_MONTH-7);
				SimpleDateFormat format=new SimpleDateFormat("mm/dd/yyyy");
				Date date = null;
				try {
					date = format.parse(dates);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (i>610 && date.after(sevenDaysBeforeDoday) && date.before(todayDate)) {
					try {
						jsonObject.put("date", dates);
						jsonObject.put("data", Long.parseLong(mvci));
						
						Log.v("test", jsonObject.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
			}
			jsonArray.put(jsonObject);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Log.v("test", "i am wrong");
		}
		
		return jsonArray.toString();
	}
}
