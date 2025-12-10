package com.tanishk.tms.service;

import java.util.UUID;

import com.tanishk.tms.dto.BookingRequest;
import com.tanishk.tms.dto.BookingResponse;

public interface BookingService {
	
	BookingResponse acceptBid(BookingRequest bookingReq);
	
	BookingResponse getBooking(UUID bookingId);
	
	void cancelBooking(UUID bookingId);
}
