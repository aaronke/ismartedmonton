package com.cst.aaron.ismartedmonton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart;
import org.achartengine.chart.LineChart;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class Collision_chart extends Fragment{

	LinearLayout parentLayout;
	private String urlString="http://www.its.ualberta.ca/app/wcpa/";
	String[] divisionStrings={"citywide daily collisions.csv",
		    "CT daily collisions worksheet.csv",
		    "NE daily collisions worksheet.csv",
		    "SE daily collisions worksheet.csv",
		    "SW daily collisions worksheet.csv",
		   "NW daily collisions worksheet.csv"};
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		 View rootView=inflater.inflate(R.layout.collision_chart, container, false);	
		 parentLayout=(LinearLayout)rootView.findViewById(R.id.parent_collision_chart);
		 		 
		 new DownloadTask().execute(urlString+divisionStrings[0]);
		Log.v("test", "chart create view");
		return rootView;
	}	
	
	
	private class DownloadTask extends AsyncTask<String, Void, JSONArray> {
		@Override
		protected JSONArray doInBackground(String... urls) {
			// TODO Auto-generated method stub
			try {
				return loadFromNetwork(urls[0]);
			} catch (Exception e) {
				// TODO: handle exception
			}
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		protected void onPostExecute(JSONArray result) {
			// TODO Auto-generated method stub
			setupChart(result);
		//	super.onPostExecute(result);
		}
			
	}
	
	private JSONArray loadFromNetwork(String urlString) throws IOException{
		InputStream inputStream=null;
		JSONArray jsonArray=new JSONArray();
		try {
			inputStream=downloadUrl(urlString);			
		} catch (Exception e) {
			// TODO: handle exception
			inputStream=getActivity().getAssets().open("data.csv");
		}
			jsonArray=readCSV(inputStream);
		return jsonArray;
	}
	private InputStream downloadUrl(String urString) throws IOException{
		URL url=new URL(urString);
		HttpURLConnection connection=(HttpURLConnection)url.openConnection();
		connection.setReadTimeout(1000);
		connection.setConnectTimeout(15000);
		connection.setRequestMethod("GET");
		connection.setDoInput(true);
		connection.connect();
		InputStream inputStream=connection.getInputStream();
		
		return inputStream;
	}
	
	/*@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.v("test", "chart oncreate");
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.v("test", "chart onDestory");
		super.onDestroy();
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		Log.v("test", "chart onPause");
		super.onPause();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		Log.v("test", "chart onResume");
		super.onResume();
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		Log.v("test", "chart onStart");
		super.onStart();
	}

	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		Log.v("test", "chart onStop");
		super.onStop();
	}*/

	public XYSeriesRenderer generateXYseriesRenderer( int color){
		
		XYSeriesRenderer renderer=new XYSeriesRenderer();
		renderer.setColor(color);
		renderer.setDisplayChartValues(true);
        renderer.setChartValuesTextSize(20);
        renderer.setLineWidth((float)5.5d);
		return renderer;
		
	}
	public XYSeries generateSeries(String chartName,ArrayList<Double> arrayList){
		XYSeries series=new XYSeries(chartName);
		for (int i = 0; i < arrayList.size(); i++) {
			series.add(i+1, arrayList.get(i));
		}
		return series;
		
	}
	public void setupChart(JSONArray jsonArray) {
			SharedPreferences sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
			int position=sharedPreferences.getInt("division", 0);
			Log.v("test", "spinner position:"+position);
			ArrayList<Double> mvciArrayList=new ArrayList<Double>();
			ArrayList<Double> cadArrayList=new ArrayList<Double>();
		 	XYMultipleSeriesRenderer multirenderer=new XYMultipleSeriesRenderer();
	        multirenderer.setApplyBackgroundColor(true);
	        multirenderer.setBackgroundColor(Color.BLACK);
	        multirenderer.setBarSpacing(0.5);
	        multirenderer.setXLabels(7);
	        multirenderer.setShowGrid(true);
	        multirenderer.setXLabelsAlign(Align.RIGHT);
	        multirenderer.setYLabelsAlign(Align.RIGHT);
	        multirenderer.setXAxisMax(8);
	        multirenderer.setXAxisMin(0);
	      //  multirenderer.setYAxisMax(35);
	        multirenderer.setYAxisMin(0);
	        multirenderer.setChartTitle("Collision Prediction");
	        multirenderer.setXTitle("Date");
	        multirenderer.setYTitle("Number of Collision");
	        multirenderer.setZoomButtonsVisible(true);
	        multirenderer.setPanEnabled(true, true);
	        multirenderer.setPanLimits(new double[]{-5,10,-10,40});
	        multirenderer.setZoomLimits(new double[]{-5,10,-10,40});
	        multirenderer.setAxisTitleTextSize(25);
	        multirenderer.setChartTitleTextSize(34);
	        multirenderer.setLabelsTextSize(18);
	     
	       
	       // CategorySeries series=new CategorySeries("bar1");
	        for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject object;
				try {
					object=jsonArray.getJSONObject(i);					
					String dateString=object.getString("date");
					String mvciString=object.getString("mvci");
					String cadString=object.getString("fm");
					mvciArrayList.add(i, Double.parseDouble(mvciString));
					cadArrayList.add(i, Double.parseDouble(cadString));
					multirenderer.addTextLabel(i+1, dateString);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}      	       	       	        
	        multirenderer.addSeriesRenderer(0, generateXYseriesRenderer(Color.argb(250, 0, 210, 250)));
	        multirenderer.addSeriesRenderer(1, generateXYseriesRenderer(Color.argb(250, 120, 0, 250)));
	        XYMultipleSeriesDataset dataset=new XYMultipleSeriesDataset();
	        dataset.addSeries(0, generateSeries("MVCI", mvciArrayList));
	        dataset.addSeries(1, generateSeries("FM", cadArrayList));
	        String[] types = new String[] { BarChart.TYPE,LineChart.TYPE };
	        GraphicalView chartView=ChartFactory.getCombinedXYChartView(getActivity(), dataset, multirenderer, types );
	        
	        parentLayout.addView(chartView);
	       
	}
	
	
	public JSONArray readCSV(InputStream in){
		JSONArray jsonArray=new JSONArray();
		try {
			InputStreamReader iStreamReader=new InputStreamReader(in);
			BufferedReader reader=new BufferedReader(iStreamReader);			
			String lineString=null;
			int i=0;
			
			
			while ((lineString=reader.readLine())!=null) {
				
				String[] rowData =lineString.split(",");
				if (i>=2824) {
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
				
				Calendar calendar=Calendar.getInstance();
			//	Date todayDate=new Date(System.currentTimeMillis());
				
				
				int day_of_week=calendar.get(Calendar.DAY_OF_WEEK);
				int update_day=0;
				if (day_of_week>=4) {
					update_day=day_of_week-3;
				}else {
					update_day=day_of_week+7-3;
				}
				Date thatDoday=new Date(System.currentTimeMillis()-update_day*1000*3600*24);
				Date sevenDaysafterDoday=new Date(System.currentTimeMillis()+(7-update_day)*1000*3600*24);
				SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
				Date date = null;
				try {
					date = format.parse(dates);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//Log.v("test", i+"---------"+dates+"------1:"+date.after(sevenDaysBeforeDoday)+"2:"+date.before(thatDoday));
				if (date.after(thatDoday) && date.before(sevenDaysafterDoday)) {
					try {
						JSONObject jsonObject=new JSONObject();
						jsonObject.put("date", ""+dates);
						jsonObject.put("mvci", Long.parseLong(mvci));
						jsonObject.put("cad", Double.parseDouble(cad));
						jsonObject.put("ftc", Double.parseDouble(ftc));
						jsonObject.put("fatalInjury", Double.parseDouble(fatalInjury));
						jsonObject.put("stpk", Double.parseDouble(stpk));
						jsonObject.put("chln", Double.parseDouble(chln));
						jsonObject.put("raof", Double.parseDouble(raof));
						jsonObject.put("fm", Double.parseDouble(fm));
						jsonObject.put("odds", Double.parseDouble(odds));
						
						jsonArray.put(jsonObject);
						Log.v("test", jsonObject.toString());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
				}
				i++;
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return jsonArray;
	}
}
