package com.tanishk.tms.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.tanishk.tms.constants.BidStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bids")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID bidId;
	
	@ManyToOne
	@JoinColumn(name = "load_id", nullable = false)
	private Load load;
	
	@ManyToOne
	@JoinColumn(name = "transporterId" ,nullable = false)
	private Transporter transporter;
	
	private double proposedRate;
	
	private int trucksOffered;
	
	@Enumerated(EnumType.STRING)
	private BidStatus status;
	
	@CreationTimestamp
	private LocalDateTime submittedAt;

	public UUID getBidId() {
		return bidId;
	}

	public void setBidId(UUID bidId) {
		this.bidId = bidId;
	}

	public Load getLoad() {
		return load;
	}

	public void setLoad(Load load) {
		this.load = load;
	}

	public Transporter getTransporter() {
		return transporter;
	}

	public void setTransporter(Transporter transporter) {
		this.transporter = transporter;
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

	public Bid(UUID bidId, Load load, Transporter transporter, double proposedRate, int trucksOffered, BidStatus status,
			LocalDateTime submittedAt) {
		super();
		this.bidId = bidId;
		this.load = load;
		this.transporter = transporter;
		this.proposedRate = proposedRate;
		this.trucksOffered = trucksOffered;
		this.status = status;
		this.submittedAt = submittedAt;
	}

	public Bid() {
		
	}
}
