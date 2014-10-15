package com.cst.aaron.ismartedmonton;

import android.graphics.Color;
import android.graphics.Point;

public class LinkEntity {
	int travel_speed;
	Point orignal_point;
	Point destination_point;
	
	public int getTravel_speed() {
		return travel_speed;
	}
	public void setTravel_speed(int travel_speed) {
		this.travel_speed = travel_speed;
	}
	public Point getOrignal_point() {
		return orignal_point;
	}
	public void setOrignal_point(Point orignal_point) {
		this.orignal_point = orignal_point;
	}
	public Point getDestination_point() {
		return destination_point;
	}
	public void setDestination_point(Point destination_point) {
		this.destination_point = destination_point;
	}

	public int getLinkColor(){
		int color=Color.argb(255, 0, 255, 0);
		
		if (travel_speed>=80) {
			color=Color.argb(255, 0, 255, 0);
		}
		else if (travel_speed>=75) {
			color=Color.argb(255, 138, 252, 20);
		}
		else if (travel_speed>=65) {
			color=Color.argb(255, 173, 255, 47);
		}
		else if (travel_speed>=40) {
			color=Color.argb(255, 255, 255, 0);
		}
		else if (travel_speed>=0) {
			color=Color.argb(255, 255, 0, 0);
		}else {
			color=Color.argb(255, 160, 160, 160);
		}
		return color;
		
	}
	
}
