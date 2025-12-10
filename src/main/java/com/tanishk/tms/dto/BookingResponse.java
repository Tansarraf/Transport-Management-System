package com.tanishk.tms.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tanishk.tms.constants.BookingStatus;

import lombok.Data;

@Data
public class BookingResponse {
	private UUID bookingId;
    private UUID loadId;
    private UUID bidId;
    private UUID transporterId;
    private int allocatedTrucks;
    private double finalRate;
    private BookingStatus status;
    private LocalDateTime bookedAt;
	public UUID getBookingId() {
		return bookingId;
	}
	public void setBookingId(UUID bookingId) {
		this.bookingId = bookingId;
	}
	public UUID getLoadId() {
		return loadId;
	}
	public void setLoadId(UUID loadId) {
		this.loadId = loadId;
	}
	public UUID getBidId() {
		return bidId;
	}
	public void setBidId(UUID bidId) {
		this.bidId = bidId;
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
	public BookingStatus getStatus() {
		return status;
	}
	public void setStatus(BookingStatus status) {
		this.status = status;
	}
	public LocalDateTime getBookedAt() {
		return bookedAt;
	}
	public void setBookedAt(LocalDateTime bookedAt) {
		this.bookedAt = bookedAt;
	}
}

