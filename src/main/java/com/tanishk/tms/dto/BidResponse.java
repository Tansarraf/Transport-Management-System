package com.tanishk.tms.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tanishk.tms.constants.BidStatus;

import lombok.Data;

@Data
public class BidResponse {
	private UUID bidId;
    private UUID loadId;
    private UUID transporterId;
    private double proposedRate;
    private int trucksOffered;
    private BidStatus status;
    private LocalDateTime submittedAt;
    
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
	public BidStatus getStatus() {
		return status;
	}
	public void setStatus(BidStatus status) {
		this.status = status;
	}
	public LocalDateTime getSubmittedAt() {
		return submittedAt;
	}
	public void setSubmittedAt(LocalDateTime submittedAt) {
		this.submittedAt = submittedAt;
	}
    
    
}
