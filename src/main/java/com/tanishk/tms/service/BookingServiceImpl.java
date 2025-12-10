package com.tanishk.tms.service;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tanishk.tms.constants.BidStatus;
import com.tanishk.tms.constants.BookingStatus;
import com.tanishk.tms.constants.LoadStatus;
import com.tanishk.tms.dto.BookingRequest;
import com.tanishk.tms.dto.BookingResponse;
import com.tanishk.tms.entity.AvailableTruck;
import com.tanishk.tms.entity.Bid;
import com.tanishk.tms.entity.Booking;
import com.tanishk.tms.entity.Load;
import com.tanishk.tms.entity.Transporter;
import com.tanishk.tms.exception.InsufficientCapacityException;
import com.tanishk.tms.exception.InvalidStatusException;
import com.tanishk.tms.exception.LoadBookedException;
import com.tanishk.tms.exception.ResourceNotFoundException;
import com.tanishk.tms.repository.BidRepo;
import com.tanishk.tms.repository.BookingRepo;
import com.tanishk.tms.repository.LoadRepo;
import com.tanishk.tms.repository.TransporterRepo;

import jakarta.persistence.OptimisticLockException;

@Service
public class BookingServiceImpl implements BookingService{

	private BookingRepo bookingRepo;
    private BidRepo bidRepo;
    private LoadRepo loadRepo;
    private TransporterRepo transporterRepo;
    
	public BookingServiceImpl(BookingRepo bookingRepo, BidRepo bidRepo, LoadRepo loadRepo, TransporterRepo transporterRepo) {
		this.bookingRepo = bookingRepo;
		this.bidRepo = bidRepo;
		this.loadRepo = loadRepo;
		this.transporterRepo = transporterRepo;
	}
	
	@Override
    @Transactional
    public BookingResponse acceptBid(BookingRequest bookingReq) {

        Bid bid = bidRepo.findById(bookingReq.getBidId())
                .orElseThrow(() -> new ResourceNotFoundException("Bid not found"));

        Load load = loadRepo.findById(bookingReq.getLoadId())
                .orElseThrow(() -> new ResourceNotFoundException("Load not found"));

        Transporter transporter = transporterRepo.findById(bookingReq.getTransporterId())
                .orElseThrow(() -> new ResourceNotFoundException("Transporter not found"));

        if (bidRepo.existsByLoad_LoadIdAndStatus(load.getLoadId(), BidStatus.ACCEPTED)) {
            throw new InvalidStatusException("Load already has an accepted bid");
        }

        if (load.getStatus() == LoadStatus.CANCELLED) {
            throw new InvalidStatusException("Cannot accept bid on a cancelled load");
        }

        int allocated = bookingRepo.findByLoad_LoadId(load.getLoadId())
                .stream()
                .filter(bk -> bk.getStatus() != BookingStatus.CANCELLED)
                .mapToInt(Booking::getAllocatedTrucks)
                .sum();

        int remaining = load.getNoOfTrucks() - allocated;
        if (bookingReq.getAllocatedTrucks() > remaining) {
            throw new InsufficientCapacityException("Not enough trucks remaining for this load.");
        }

        int available = transporter.getAvailableTrucks().stream()
                .filter(t -> t.getTruckType().equalsIgnoreCase(load.getTruckType()))
                .map(AvailableTruck::getCount)
                .findFirst()
                .orElse(0);

        if (bookingReq.getAllocatedTrucks() > available) {
            throw new InsufficientCapacityException("Transporter doesn't have enough truck capacity.");
        }

        bid.setStatus(BidStatus.ACCEPTED);
        bidRepo.save(bid);

        Booking booking = new Booking();
        booking.setBid(bid);
        booking.setLoad(load);
        booking.setTransporter(transporter);
        booking.setAllocatedTrucks(bookingReq.getAllocatedTrucks());
        booking.setFinalRate(bookingReq.getFinalRate());
        booking.setStatus(BookingStatus.CONFIRMED);

        for (AvailableTruck at : transporter.getAvailableTrucks()) {
            if (at.getTruckType().equalsIgnoreCase(load.getTruckType())) {
                at.setCount(at.getCount() - bookingReq.getAllocatedTrucks());
            }
        }
        transporterRepo.save(transporter);

        Booking savedBooking;

        try {
            savedBooking = bookingRepo.save(booking);

            int remainingAfter = remaining - bookingReq.getAllocatedTrucks();
            if (remainingAfter == 0) {
                load.setStatus(LoadStatus.BOOKED); 
            } else {
                load.setStatus(LoadStatus.OPEN_FOR_BIDS);
            }

            loadRepo.save(load);

        } catch (OptimisticLockException e) {
            restoreTransporterCapacity(transporter, load.getTruckType(), bookingReq.getAllocatedTrucks());
            throw new LoadBookedException("Concurrent booking conflict", e);
        }

        return map(savedBooking);
    }

    @Override
    public BookingResponse getBooking(UUID bookingId) {
        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find the booking!"));

        return map(booking);
    }

    @Override
    @Transactional
    public void cancelBooking(UUID bookingId) {

        Booking booking = bookingRepo.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Cannot find the booking!"));

        if (booking.getStatus() == BookingStatus.CANCELLED) return;

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepo.save(booking);

        Load load = booking.getLoad();
        Transporter transporter = booking.getTransporter();

        for (AvailableTruck at : transporter.getAvailableTrucks()) {
            if (at.getTruckType().equalsIgnoreCase(load.getTruckType())) {
                at.setCount(at.getCount() + booking.getAllocatedTrucks());
            }
        }
        transporterRepo.save(transporter);

        int activeAllocated = bookingRepo.findByLoad_LoadId(load.getLoadId())
                .stream()
                .filter(b -> b.getStatus() != BookingStatus.CANCELLED)
                .mapToInt(Booking::getAllocatedTrucks)
                .sum();

        if (activeAllocated == 0) {
            load.setStatus(LoadStatus.OPEN_FOR_BIDS);
        } else if (activeAllocated < load.getNoOfTrucks()) {
            load.setStatus(LoadStatus.OPEN_FOR_BIDS);
        }

        loadRepo.save(load);
    }

    private void restoreTransporterCapacity(Transporter transporter, String truckType, int count) {
        for (AvailableTruck at : transporter.getAvailableTrucks()) {
            if (at.getTruckType().equalsIgnoreCase(truckType)) {
                at.setCount(at.getCount() + count);
            }
        }
        transporterRepo.save(transporter);
    }

    private BookingResponse map(Booking b) {
        BookingResponse dto = new BookingResponse();
        dto.setBookingId(b.getBookingId());
        dto.setLoadId(b.getLoad().getLoadId());
        dto.setBidId(b.getBid().getBidId());
        dto.setTransporterId(b.getTransporter().getTransporterId());
        dto.setAllocatedTrucks(b.getAllocatedTrucks());
        dto.setFinalRate(b.getFinalRate());
        dto.setStatus(b.getStatus());
        dto.setBookedAt(b.getBookedAt());
        return dto;
    }

    
}
