package com.tanishk.tms.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import com.tanishk.tms.constants.LoadStatus;
import com.tanishk.tms.constants.WeightUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "load")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Load {
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID loadId;
	
	private String shipperId;
	
	private String loadingCity;
	
	private String unloadingCity;
	
	private LocalDate loadingDate = LocalDate.now();
	
	private String productType;
	
	private double weight;
	
	@Enumerated(EnumType.STRING)
	private WeightUnit weightUnit;
	
	private String truckType;
	
	private int noOfTrucks;
	
	@Enumerated(EnumType.STRING)
	private LoadStatus status;
	
	@Column(updatable = false)
	private LocalDateTime datePosted;
	
	@Version
	private Long version;
	
	@PrePersist
	public void onCreate() {
	    this.datePosted = LocalDateTime.now();
	}

	public UUID getLoadId() {
		return loadId;
	}

	public void setLoadId(UUID loadId) {
		this.loadId = loadId;
	}

	public String getShipperId() {
		return shipperId;
	}

	public void setShipperId(String shipperId) {
		this.shipperId = shipperId;
	}

	public String getLoadingCity() {
		return loadingCity;
	}

	public void setLoadingCity(String loadingCity) {
		this.loadingCity = loadingCity;
	}

	public String getUnloadingCity() {
		return unloadingCity;
	}

	public void setUnloadingCity(String unloadingCity) {
		this.unloadingCity = unloadingCity;
	}

	public LocalDate getLoadingDate() {
		return loadingDate;
	}

	public void setLoadingDate(LocalDate loadingDate) {
		this.loadingDate = loadingDate;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public WeightUnit getWeightUnit() {
		return weightUnit;
	}

	public void setWeightUnit(WeightUnit weightUnit) {
		this.weightUnit = weightUnit;
	}

	public String getTruckType() {
		return truckType;
	}

	public void setTruckType(String truckType) {
		this.truckType = truckType;
	}

	public int getNoOfTrucks() {
		return noOfTrucks;
	}

	public void setNoOfTrucks(int noOfTrucks) {
		this.noOfTrucks = noOfTrucks;
	}

	public LoadStatus getStatus() {
		return status;
	}

	public void setStatus(LoadStatus status) {
		this.status = status;
	}

	public LocalDateTime getDatePosted() {
		return datePosted;
	}

	public void setDatePosted(LocalDateTime datePosted) {
		this.datePosted = datePosted;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Load(UUID loadId, String shipperId, String loadingCity, String unloadingCity, LocalDate loadingDate,
			String productType, double weight, WeightUnit weightUnit, String truckType, int noOfTrucks,
			LoadStatus status, LocalDateTime datePosted, Long version) {
		super();
		this.loadId = loadId;
		this.shipperId = shipperId;
		this.loadingCity = loadingCity;
		this.unloadingCity = unloadingCity;
		this.loadingDate = loadingDate;
		this.productType = productType;
		this.weight = weight;
		this.weightUnit = weightUnit;
		this.truckType = truckType;
		this.noOfTrucks = noOfTrucks;
		this.status = status;
		this.datePosted = datePosted;
		this.version = version;
	}

	public Load() {

	}
	
	
}
