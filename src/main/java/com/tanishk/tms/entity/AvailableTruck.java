package com.tanishk.tms.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableTruck {
	
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

	public AvailableTruck() {
	
	}

	public AvailableTruck(String truckType, int count) {
		super();
		this.truckType = truckType;
		this.count = count;
	}
	
}
