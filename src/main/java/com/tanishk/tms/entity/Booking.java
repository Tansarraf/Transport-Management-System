package com.tanishk.tms.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.tanishk.tms.constants.BookingStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bookings")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID bookingId;
	
	@ManyToOne
	@JoinColumn(name = "loadId", nullable = false)
	private Load load;
	
	@ManyToOne
	@JoinColumn(name = "bidId", nullable = false)
	private Bid bid;
	
	@ManyToOne 
	@JoinColumn(name = "transporterId", nullable = false)
	private Transporter transporter;
	
	private int allocatedTrucks;
	
	private double finalRate;
	
	@Enumerated(EnumType.STRING)
	private BookingStatus status;
	
	@Column(updatable = false)
	private LocalDateTime bookedAt;
	
	@PrePersist
	public void onCreate() {
	    this.bookedAt = LocalDateTime.now();
	}

	public UUID getBookingId() {
		return bookingId;
	}

	public void setBookingId(UUID bookingId) {
		this.bookingId = bookingId;
	}

	public Load getLoad() {
		return load;
	}

	public void setLoad(Load load) {
		this.load = load;
	}

	public Bid getBid() {
		return bid;
	}

	public void setBid(Bid bid) {
		this.bid = bid;
	}

	public Transporter getTransporter() {
		return transporter;
	}

	public void setTransporter(Transporter transporter) {
		this.transporter = transporter;
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

	public Booking(UUID bookingId, Load load, Bid bid, Transporter transporter, int allocatedTrucks, double finalRate,
			BookingStatus status, LocalDateTime bookedAt) {
		super();
		this.bookingId = bookingId;
		this.load = load;
		this.bid = bid;
		this.transporter = transporter;
		this.allocatedTrucks = allocatedTrucks;
		this.finalRate = finalRate;
		this.status = status;
		this.bookedAt = bookedAt;
	}

	public Booking() {

	}
	
	
}
