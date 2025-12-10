package com.tanishk.tms.dto;

import com.tanishk.tms.constants.WeightUnit;

import lombok.Data;

@Data
public class LoadRequest {
	
	private String shipperId;
    private String loadingCity;
    private String unloadingCity;
    private String productType;
    private double weight;
    private WeightUnit weightUnit;
    private String truckType;
    private int noOfTrucks;
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
}
