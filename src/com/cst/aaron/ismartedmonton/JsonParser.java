package com.cst.aaron.ismartedmonton;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;

import android.util.Log;

public class JsonParser {

	static InputStream iStream=null;
	static JSONArray jsonArray=null;
	static String jsonString="";
	public JsonParser(){
		
	}
	
	public JSONArray getJsonFromUrl(String urlString){
		
		StringBuilder builder=new StringBuilder();
		HttpClient client=new DefaultHttpClient();
		HttpGet httpGet=new HttpGet(urlString);
		try {
			HttpResponse response=client.execute(httpGet);
			StatusLine statusLine=response.getStatusLine();
			int statusCode=statusLine.getStatusCode();
			if (statusCode==200) {
				HttpEntity entity=response.getEntity();
				InputStream content=entity.getContent();
				BufferedReader reader=new BufferedReader(new InputStreamReader(content), 16000);
				String lineString;
				while ((lineString=reader.readLine())!=null) {
				
					builder.append(lineString);
				}	
				
			//	String contentString=EntityUtils.toString(entity);
				
			//	builder.append(contentString);
			
			}
			else {
				Log.e("hello", "Failed to download file");
			}
		} catch (ClientProtocolException e) {
			// TODO: handle exception
			e.printStackTrace();
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		try {
			jsonArray=new JSONArray(builder.toString());
		//	Log.v("hello", jsonArray.get(27).toString()+"");
		} catch (JSONException e) {
			// TODO: handle exception
		//	Log.v("hello", ""+e);
		}
		return jsonArray;
	}
}
