package com.cst.aaron.ismartedmonton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.R.integer;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.View;

public class SpeedMapView extends View{
	Paint paint=new Paint();
	Point point_5=new Point(80, 900);
	Point point_13=new Point(330, 900);
	Point point_14=new Point(330, 1100);
	Point point_16=new Point(580, 1100);
	int[] location_values_right={80,900,180,900,230,900,280,900,330,900,330,950,330,1000,330,1050,330,1100,380,1100,480,1100};
	int[] location_values_left={80,915,180,915,230,915,280,915,315,915,315,965,315,1015,315,1065,315,1115,365,1115,480,1115};
	int[] location_VSD_ID_right={1009,1007,1016,1036,1034,1032,1030,1028,1026,1018};
	int[] location_VSD_ID_left={1010,1008,1017,1037,1035,1033,1031,1029,1027,1019};
	int[] location_values_others_right={870,150,820,100,330,100,80,500,80,1400,330,1620,920,1620,920,550};
	int[] location_values_others_left={870,135,820,85,315,85,65,500,65,1415,330,1635,935,1635,935,550};
	int[] location_values_17_11_right={650,850,920,850};
	int[] location_values_17_11_left={650,865,920,865};
	int[] location_vlaues_15_8_right={500,1500,450,1680};
	int[] location_vlaues_15_8_left={515,1500,465,1680};
	int[] location_values_16_10_right={480,1100,920,1100};
	int[] location_values_16_10_left={480,1115,920,1115};
	Point originalPoint=new Point();
	Point destinationPoint=new Point();
	Random random=new Random();
	ArrayList<HashMap<String, String>> arrayList=new ArrayList<HashMap<String,String>>();
	public SpeedMapView(Context context, ArrayList<HashMap<String, String>> arrayList) {
		super(context);
		// TODO Auto-generated constructor stub
		this.arrayList=arrayList;
		paint.setColor(Color.GREEN);
		paint.setStrokeWidth(6);
		paint.setStyle(Paint.Style.STROKE);
		
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		drawlink(canvas, location_values_right,location_VSD_ID_right);
		drawlink(canvas, location_values_left,location_VSD_ID_left);
		drawlink_Not_Real(canvas, location_values_others_right);
		drawlink_Not_Real(canvas, location_values_others_left);
		drawlink_Not_Real(canvas, location_values_17_11_right);
		drawlink_Not_Real(canvas, location_values_17_11_left);
		drawlink_Not_Real(canvas, location_vlaues_15_8_right);
		drawlink_Not_Real(canvas, location_vlaues_15_8_left);
		drawlink_Not_Real(canvas, location_values_16_10_right);
		drawlink_Not_Real(canvas, location_values_16_10_left);
	}
	
	private void drawlink(Canvas canvas,int[] data,int[] location_id){
		LinkEntity entity=new LinkEntity();
		ArrayList<Integer> arrayList_ID=new ArrayList<Integer>();
		Boolean real_time=false;
		if (!arrayList.isEmpty()) {
			real_time=true;
			for (int i = 0; i < arrayList.size(); i++) {
				int id =(int) Double.parseDouble(arrayList.get(i).get("VDS_ID"));
				arrayList_ID.add(i, id);
			}
		}
		for (int i = 0; i < (data.length/2)-1; i++) {	
			
			originalPoint.set(data[(i+1)*2-2], data[(i+1)*2-1]);
			destinationPoint.set(data[(i+1)*2], data[(i+1)*2+1]);
			entity.setOrignal_point(originalPoint);
			entity.setDestination_point(destinationPoint);
			
			int travelspeed=random.nextInt(100)+20;
			if (real_time) {
				int index=arrayList_ID.indexOf(location_id[i]);
				if (index!=-1) {
					travelspeed=(int) Double.parseDouble(arrayList.get(index).get("VDS_Speed"));
					
				}else {
					travelspeed=-1;
				}		
				Log.v("test", "index:"+index+"speed:"+travelspeed+"ID:"+location_id[i]);
			}
			entity.setTravel_speed(travelspeed);		
			paint.setColor(entity.getLinkColor());
			canvas.drawLine(entity.getOrignal_point().x, entity.getOrignal_point().y, entity.getDestination_point().x, entity.getDestination_point().y, paint);
		}
		
			
	}
	private void drawlink_Not_Real(Canvas canvas,int[] data){
		LinkEntity entity=new LinkEntity();
		
		for (int i = 0; i < (data.length/2)-1; i++) {	
			
			originalPoint.set(data[(i+1)*2-2], data[(i+1)*2-1]);
			destinationPoint.set(data[(i+1)*2], data[(i+1)*2+1]);
			entity.setOrignal_point(originalPoint);
			entity.setDestination_point(destinationPoint);
			int travelspeed=100;
			entity.setTravel_speed(travelspeed);		
			paint.setColor(entity.getLinkColor());
			canvas.drawLine(entity.getOrignal_point().x, entity.getOrignal_point().y, entity.getDestination_point().x, entity.getDestination_point().y, paint);
		}
			

	}

}
