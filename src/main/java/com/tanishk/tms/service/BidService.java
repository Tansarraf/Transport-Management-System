package com.tanishk.tms.service;

import java.util.List;
import java.util.UUID;

import com.tanishk.tms.constants.BidStatus;
import com.tanishk.tms.dto.BidRequest;
import com.tanishk.tms.dto.BidResponse;

public interface BidService {
	
	BidResponse submitBid(BidRequest bidReq);
	
	List<BidResponse> findBids(UUID loadId, UUID transporterId, BidStatus status);
	
	BidResponse getBid(UUID bidId);
	
	void rejectBid(UUID bidID);
}
