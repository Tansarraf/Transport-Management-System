package com.tanishk.tms.dto;

import lombok.Data;

@Data
public class AvailTruckRequest {
	
	private String truckType;
	private int count;
	
	public String getTruckType() {
		return truckType;
	}
	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
