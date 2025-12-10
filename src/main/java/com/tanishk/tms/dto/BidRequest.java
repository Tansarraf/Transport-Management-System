package com.tanishk.tms.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class BidRequest {
	
	private UUID loadId;
    private UUID transporterId;
    private double proposedRate;
    private int trucksOffered;
    
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
	public double getProposedRate() {
		return proposedRate;
	}
	public void setProposedRate(double proposedRate) {
		this.proposedRate = proposedRate;
	}
	public int getTrucksOffered() {
		return trucksOffered;
	}
	public void setTrucksOffered(int trucksOffered) {
		this.trucksOffered = trucksOffered;
	}
}
