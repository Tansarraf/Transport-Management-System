package com.tanishk.tms.entity;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "transporter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transporter {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID transporterId;

	private String companyName;

	private double rating;

	@ElementCollection
	@CollectionTable(name = "transporter_trucks", joinColumns = @JoinColumn(name = "transporterId"))
	private List<AvailableTruck> availableTrucks;

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

	public List<AvailableTruck> getAvailableTrucks() {
		return availableTrucks;
	}

	public void setAvailableTrucks(List<AvailableTruck> availableTrucks) {
		this.availableTrucks = availableTrucks;
	}

	public Transporter() {
		
	}

	public Transporter(UUID transporterId, String companyName, double rating, List<AvailableTruck> availableTrucks) {
		super();
		this.transporterId = transporterId;
		this.companyName = companyName;
		this.rating = rating;
		this.availableTrucks = availableTrucks;
	}	
}
