package com.tanishk.tms.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tanishk.tms.constants.BidStatus;
import com.tanishk.tms.constants.LoadStatus;
import com.tanishk.tms.dto.BidRequest;
import com.tanishk.tms.dto.BidResponse;
import com.tanishk.tms.entity.AvailableTruck;
import com.tanishk.tms.entity.Bid;
import com.tanishk.tms.entity.Load;
import com.tanishk.tms.entity.Transporter;
import com.tanishk.tms.exception.InsufficientCapacityException;
import com.tanishk.tms.exception.InvalidStatusException;
import com.tanishk.tms.exception.ResourceNotFoundException;
import com.tanishk.tms.repository.BidRepo;
import com.tanishk.tms.repository.LoadRepo;
import com.tanishk.tms.repository.TransporterRepo;


@Service
public class BidServiceImpl implements BidService{
	private BidRepo bidRepo;
	private LoadRepo loadRepo;
	private TransporterRepo transporterRepo;
	
	public BidServiceImpl(BidRepo bidRepo, LoadRepo loadRepo, TransporterRepo transporterRepo) {
		super();
		this.bidRepo = bidRepo;
		this.loadRepo = loadRepo;
		this.transporterRepo = transporterRepo;
	}
	
	public BidResponse submitBid(BidRequest bidReq) {
		Load load = loadRepo.findById(bidReq.getLoadId())
                .orElseThrow(() -> new ResourceNotFoundException("Load not found with ID : " + bidReq.getLoadId()));

        Transporter transporter = transporterRepo.findById(bidReq.getTransporterId())
                .orElseThrow(() -> new ResourceNotFoundException("Transporter not found with ID : " + bidReq.getTransporterId()));

        if (load.getStatus() == LoadStatus.CANCELLED || load.getStatus() == LoadStatus.BOOKED) {
            throw new InvalidStatusException("Cannot make bids on cancelled/booked loads");
        }

        int available = findAvailableCount(transporter, load.getTruckType());
        if (bidReq.getTrucksOffered() > available) {
            throw new InsufficientCapacityException("Transporter does not have enough trucks for this truck type");
        }

        Bid bid = new Bid();
        bid.setLoad(load);
        bid.setTransporter(transporter);
        bid.setProposedRate(bidReq.getProposedRate());
        bid.setTrucksOffered(bidReq.getTrucksOffered());
        bid.setStatus(BidStatus.PENDING);

        boolean noPriorBids = bidRepo.findByLoad_LoadId(load.getLoadId()).isEmpty();
        if (noPriorBids && load.getStatus() == LoadStatus.POSTED) {
            load.setStatus(LoadStatus.OPEN_FOR_BIDS);
            loadRepo.save(load);
        }

        Bid saved = bidRepo.save(bid);
        return mapToResponse(saved);
    }

    @Override
    public List<BidResponse> findBids(UUID loadId, UUID transporterId, BidStatus status) {
        List<Bid> list;

        if (loadId != null) {
            list = bidRepo.findByLoad_LoadId(loadId);
        } else if (transporterId != null) {
            list = bidRepo.findByTransporter_TransporterId(transporterId);
        } else if (status != null) {
            list = bidRepo.findByStatus(status);
        } else {
            list = bidRepo.findAll();
        }

        // apply status filter if provided
        return list.stream()
                .filter(b -> status == null || b.getStatus() == status)
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BidResponse getBid(UUID bidId) {
        Bid b = bidRepo.findById(bidId)
                .orElseThrow(() -> new ResourceNotFoundException("Bid not found with ID : " + bidId));
        return mapToResponse(b);
    }

    @Override
    @Transactional
    public void rejectBid(UUID bidId) {
        Bid b = bidRepo.findById(bidId)
                .orElseThrow(() -> new ResourceNotFoundException("Bid not found with ID : " + bidId));

        b.setStatus(BidStatus.REJECTED);
        bidRepo.save(b);
    }

    private int findAvailableCount(Transporter t, String truckType) {
        if (t.getAvailableTrucks() == null) return 0;
        return t.getAvailableTrucks().stream()
                .filter(at -> at.getTruckType().equalsIgnoreCase(truckType))
                .findFirst()
                .map(AvailableTruck::getCount)
                .orElse(0);
    }

    private BidResponse mapToResponse(Bid b) {
        BidResponse dto = new BidResponse();
        dto.setBidId(b.getBidId());
        dto.setLoadId(b.getLoad().getLoadId());
        dto.setTransporterId(b.getTransporter().getTransporterId());
        dto.setProposedRate(b.getProposedRate());
        dto.setTrucksOffered(b.getTrucksOffered());
        dto.setStatus(b.getStatus());
        dto.setSubmittedAt(b.getSubmittedAt());
        return dto;
    }
}
