package com.tanishk.tms.dto;

import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class TransportResponse {
	private UUID transporterId;
    private String companyName;
    private double rating;
    private List<AvailTruckRequest> availableTrucks;
    
	public UUID getTransporterId() {
		return transporterId;
	}
	public void setTransporterId(UUID transporterId) {
		this.transporterId = transporterId;
	}
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
	public List<AvailTruckRequest> getAvailableTrucks() {
		return availableTrucks;
	}
	public void setAvailableTrucks(List<AvailTruckRequest> availableTrucks) {
		this.availableTrucks = availableTrucks;
	}
    
    
}
