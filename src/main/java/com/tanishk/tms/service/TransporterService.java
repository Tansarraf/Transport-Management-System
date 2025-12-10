package com.tanishk.tms.service;

import java.util.List;
import java.util.UUID;

import com.tanishk.tms.dto.AvailTruckRequest;
import com.tanishk.tms.dto.TransportRequest;
import com.tanishk.tms.dto.TransportResponse;

public interface TransporterService {
	
	TransportResponse createTransporter(TransportRequest transportReq);
	
	TransportResponse getTransporter(UUID transporterId);
	
	TransportResponse updateTrucks(UUID transporterId, List<AvailTruckRequest> trucks);
}
