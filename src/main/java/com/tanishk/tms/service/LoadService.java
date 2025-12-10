package com.tanishk.tms.service;

import java.util.List;
import java.util.UUID;

import com.tanishk.tms.constants.LoadStatus;
import com.tanishk.tms.dto.BidResponse;
import com.tanishk.tms.dto.LoadRequest;
import com.tanishk.tms.dto.LoadResponse;

public interface LoadService {
	
	LoadResponse createLoad(LoadRequest loadReq);
	
	List<LoadResponse> listLoads(String shipperId, LoadStatus status, int page, int size);
	
	LoadResponse getLoadById(UUID loadId);
	
	void cancelLoad(UUID loadId);
	
	List<BidResponse> getBestBids(UUID loadId);
}
