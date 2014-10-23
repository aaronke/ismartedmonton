package com.cst.aaron.ismartedmonton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
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
import android.widget.Toast;


public class Collision_chart extends Fragment{

	LinearLayout parentLayout;
	private int position=0;
	private Boolean MVCI_Selected=true;
	private Boolean CAD_Selected=false;
	private Boolean Fatal_Major_Collision_Selected=false;
	private Boolean Fatal_Major_Injuries_Selected=false;
	private Boolean odds_Selected=false;
	
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
		 String url=urlString+divisionStrings[position];
		 url=url.replace(" ", "%20");
		 new DownloadTask().execute(url);
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
	
	private JSONArray loadFromNetwork(String url) throws IOException{
		InputStream inputStream=null;
		JSONArray jsonArray=new JSONArray();
		try {
			inputStream=downloadUrl(url);			
		} catch (Exception e) {
			// TODO: handle exception
			inputStream=getActivity().getAssets().open("data.csv");
		}
			jsonArray=readCSV(inputStream);
		
		return jsonArray;
	}
	private InputStream downloadUrl(String url) {
		
		/*URL url=new URL(urString);
		HttpURLConnection connection=(HttpURLConnection)url.openConnection();
		connection.setReadTimeout(1000);
		connection.setConnectTimeout(15000);
		connection.setRequestMethod("GET");
		connection.setDoInput(true);
		connection.connect();
		InputStream inputStream=connection.getInputStream();*/
		HttpClient client=new DefaultHttpClient();
		try {
			HttpGet httpGet=new HttpGet(url);
			HttpResponse response=client.execute(httpGet);
			StatusLine statusLine=response.getStatusLine();
			int statuscode=statusLine.getStatusCode();
			if (statuscode==200) {
				HttpEntity entity=response.getEntity();
				InputStream inputStream=entity.getContent();
				return inputStream;
			}else {
			Toast.makeText(getActivity(), "unalbe to connect internet", Toast.LENGTH_SHORT).show();
			}
		} catch (IOException e) {
			// TODO: handle exception
			
		}
		
		return null;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		SharedPreferences sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
		position=sharedPreferences.getInt("division", 0);
		MVCI_Selected=sharedPreferences.getBoolean("mvci", true);
		CAD_Selected=sharedPreferences.getBoolean("cad", false);
		Fatal_Major_Collision_Selected=sharedPreferences.getBoolean("fatal_injury", false);
		Fatal_Major_Injuries_Selected=sharedPreferences.getBoolean("fatal_major", true);
		odds_Selected=sharedPreferences.getBoolean("odds", false);
		Log.v("test", "chart oncreate"+position);
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
	}

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
			
			ArrayList<Double> mvciArrayList=new ArrayList<Double>();
			ArrayList<Double> cadArrayList=new ArrayList<Double>();
			ArrayList<Double> fatal_injuriesArrayList=new ArrayList<Double>();
			ArrayList<Double> fatal_majorArrayList=new ArrayList<Double>();
			ArrayList<Double> oddsArrayList=new ArrayList<Double>();
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
					String cadString=object.getString("cad");
					String fatal_injuriesString=object.getString("fatalInjury");
					String fatal_majorString=object.getString("fm");
					String oddsString=object.getString("odds");
					mvciArrayList.add(i, Double.parseDouble(mvciString));
					cadArrayList.add(i, Double.parseDouble(cadString));
					fatal_injuriesArrayList.add(i, Double.parseDouble(fatal_injuriesString));
					fatal_majorArrayList.add(i, Double.parseDouble(fatal_majorString));
					oddsArrayList.add(i, Double.parseDouble(oddsString));
					multirenderer.addTextLabel(i+1, dateString);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			} 
	        ArrayList<String> typeStrings=new ArrayList<String>();
	        int render_index=0;
	        XYMultipleSeriesDataset dataset=new XYMultipleSeriesDataset();
	        if (MVCI_Selected) {
	        	 multirenderer.addSeriesRenderer(render_index, generateXYseriesRenderer(Color.argb(250, 0, 210, 250)));
	        	 dataset.addSeries(render_index, generateSeries("MVCI", mvciArrayList));
	        	 typeStrings.add(BarChart.TYPE);
	        	 render_index++;
			}
	        if (CAD_Selected) {
	        	multirenderer.addSeriesRenderer(render_index, generateXYseriesRenderer(Color.argb(250, 120, 0, 250)));
	        	dataset.addSeries(render_index, generateSeries("CAD", cadArrayList));
	        	typeStrings.add(LineChart.TYPE);
	        	render_index++;
			}
	       if (Fatal_Major_Injuries_Selected) {
	    	   multirenderer.addSeriesRenderer(render_index, generateXYseriesRenderer(Color.argb(250, 20, 80, 250)));
	           dataset.addSeries(render_index, generateSeries("Fatal_Collision", fatal_majorArrayList));
	           typeStrings.add(LineChart.TYPE);
	           render_index++;
	       }
	       if (Fatal_Major_Collision_Selected) {
	    	   multirenderer.addSeriesRenderer(render_index, generateXYseriesRenderer(Color.argb(250, 120, 180, 50)));
	           dataset.addSeries(render_index, generateSeries("Fatal_Injuries", fatal_injuriesArrayList));
	           typeStrings.add(LineChart.TYPE);
	           render_index++;
	       }
	       if (odds_Selected) {
	    	   multirenderer.addSeriesRenderer(render_index, generateXYseriesRenderer(Color.argb(250, 0, 180, 150)));
	           dataset.addSeries(render_index, generateSeries("Odds", oddsArrayList));
	           typeStrings.add(LineChart.TYPE);
	           render_index++;
	       }
	       
	        
//	        String[] types = new String[] { BarChart.TYPE,LineChart.TYPE };
	        
	        GraphicalView chartView=ChartFactory.getCombinedXYChartView(getActivity(), dataset, multirenderer,  typeStrings.toArray(new String[typeStrings.size()]) );
	        
	        parentLayout.addView(chartView);
	       
	}
	
	private JSONArray jsonArray=new JSONArray();
	
	public JSONArray readCSV(InputStream in){
		
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
						update_day=day_of_week-4;
					}else {
						update_day=day_of_week+7-4;
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
							
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}					
					}
				}
				i++;
				if (i==2938) {
					Log.v("test", "3++++++"+jsonArray.toString());
					return jsonArray;
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return jsonArray;
	}
	
	
}
