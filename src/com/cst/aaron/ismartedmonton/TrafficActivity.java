package com.cst.aaron.ismartedmonton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;

public class TrafficActivity extends Activity{

	ActionBar actionBar;
	SpeedMapView mapView;
	private static String url="http://www.its.ualberta.ca/app/api/vdsrecord";
	
	ArrayList<HashMap<String, String>> jsonlist=new ArrayList<HashMap<String,String>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mapView=new SpeedMapView(this,jsonlist);
		mapView.setBackgroundColor(Color.BLACK);
		setContentView(mapView);
		actionBar=getActionBar();
		actionBar.setHomeButtonEnabled(true);	
		//final DownloadTask task=new DownloadTask();
		
		final Handler mHandler=new Handler();
		TimerTask timerTask=new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Log.v("hello", "one time");
						new DownloadTask().execute();
					}
				});
			}
		};
		Timer timer=new Timer();
		timer.schedule(timerTask, 0, 20000);
	}

	private class DownloadTask extends AsyncTask<String, Void, String>{
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			//super.onPreExecute();
			Log.v("hello", "before execute");
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			mapView.invalidate();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			JsonParser jParser=new JsonParser();
			JSONArray jsonArray=jParser.getJsonFromUrl(url);
			for (int i = 0; i < jsonArray.length(); i++) {
				try {
					JSONObject jsonObject=jsonArray.getJSONObject(i);
					String VDS_ID=jsonObject.getString("VDSId");
					String VDS_Speed=jsonObject.getString("Speed");
					HashMap<String, String> map=new HashMap<String, String>();
					map.put("VDS_ID", VDS_ID);
					map.put("VDS_Speed", VDS_Speed);
					Log.v("hello", VDS_ID);
					jsonlist.add(map);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			return null;
		}
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}
	

}
