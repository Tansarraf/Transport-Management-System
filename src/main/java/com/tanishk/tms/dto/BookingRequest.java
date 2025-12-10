package com.tanishk.tms.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class BookingRequest {

	private UUID bidId;
    private UUID loadId;
    private UUID transporterId;
    private int allocatedTrucks;
    private double finalRate;
    
	public UUID getBidId() {
		return bidId;
	}
	public void setBidId(UUID bidId) {
		this.bidId = bidId;
	}
	public UUID getLoadId() {
		return loadId;
	}
	public void setLoadId(UUID loadId) {
		this.loadId = loadId;
	}
	public UUID getTransporterId() {
		return transporterId;
	}
	public void setTransporterId(UUID transporterId) {
		this.transporterId = transporterId;
	}
	public int getAllocatedTrucks() {
		return allocatedTrucks;
	}
	public void setAllocatedTrucks(int allocatedTrucks) {
		this.allocatedTrucks = allocatedTrucks;
	}
	public double getFinalRate() {
		return finalRate;
	}
	public void setFinalRate(double finalRate) {
		this.finalRate = finalRate;
	}
    
    
}
