package com.tanishk.tms.dto;

import lombok.Data;

@Data
public class TransportRequest {
	
	private String companyName;
	private double rating;
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
}
