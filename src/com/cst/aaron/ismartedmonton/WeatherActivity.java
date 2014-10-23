package com.cst.aaron.ismartedmonton;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class WeatherActivity extends ActionBarActivity{
	
	private GoogleMap mMap;
//	private GoogleMapOptions options=new GoogleMapOptions();
	private static final LatLng seventeen_street_position=new LatLng(53.543935, -113.369906);
	private static final LatLng Calgary_Trail_position= new LatLng(53.429798, -113.493692);
	private static final LatLng Coronation_position=new LatLng(53.562149, -113.561155);
	private static final LatLng kennedale_position=new LatLng(53.585886, -113.386153);
	private static final LatLng Rabbit_Hill_position=new LatLng(53.452436, -113.565644);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_activity);
		/*MapFragment mMapFragment=MapFragment.newInstance();
		FragmentTransaction fragmentTransaction=getFragmentManager().beginTransaction();
	//	fragmentTransaction.add(R.id.parent_weather, mMapFragment, "map");
		fragmentTransaction.addToBackStack("map");
		fragmentTransaction.commit();*/
	//	mMap=((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded(){
		if (mMap==null) {
			mMap=((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();
			if (mMap!=null) {
				mMap.setMyLocationEnabled(true);
				//CameraPosition cameraPosition=new CameraPosition(new LatLng(53.552410, -113.489142), (float)13.0, (float)30.0, (float)112.5);				
				//options.camera(cameraPosition);
				LatLngBounds edmontonBounds=new LatLngBounds(new LatLng(53, -114), new LatLng(54, -113));
				mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(edmontonBounds.getCenter(), 10));
				
				Marker seventeen_street=mMap.addMarker(new MarkerOptions().position(seventeen_street_position).title("17 Street Station")
						.icon(BitmapDescriptorFactory.fromResource(R.drawable.weather_maker)).alpha(0.9f));
				Marker Calgary_trail=mMap.addMarker(new MarkerOptions().position(Calgary_Trail_position).title("Calgary Trail Station")
						.icon(BitmapDescriptorFactory.fromResource(R.drawable.weather_maker)).alpha(0.9f));
				Marker Coronation=mMap.addMarker(new MarkerOptions().position(Coronation_position).title("Coronation Station")
						.icon(BitmapDescriptorFactory.fromResource(R.drawable.weather_maker)).alpha(0.9f));
				Marker Kennedale=mMap.addMarker(new MarkerOptions().position(kennedale_position).title("Kennedale Station")
						.icon(BitmapDescriptorFactory.fromResource(R.drawable.weather_maker)).alpha(0.9f));
				Marker Rabbit_Hill_Road=mMap.addMarker(new MarkerOptions().position(Rabbit_Hill_position).title("Rabbit Hill Road Station")
						.icon(BitmapDescriptorFactory.fromResource(R.drawable.weather_maker)).alpha(0.9f));
				
				
				
			}
		}
	}
}
