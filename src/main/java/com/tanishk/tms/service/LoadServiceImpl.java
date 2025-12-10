package com.tanishk.tms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tanishk.tms.constants.LoadStatus;
import com.tanishk.tms.dto.BidResponse;
import com.tanishk.tms.dto.LoadRequest;
import com.tanishk.tms.dto.LoadResponse;
import com.tanishk.tms.entity.Bid;
import com.tanishk.tms.entity.Load;
import com.tanishk.tms.entity.Transporter;
import com.tanishk.tms.exception.InvalidStatusException;
import com.tanishk.tms.exception.ResourceNotFoundException;
import com.tanishk.tms.repository.BidRepo;
import com.tanishk.tms.repository.LoadRepo;

@Service
public class LoadServiceImpl implements LoadService {
	
    private BidRepo bidRepo;
    private LoadRepo loadRepo;
    
	public LoadServiceImpl(BidRepo bidRepo, LoadRepo loadRepo) {
		this.bidRepo = bidRepo;
		this.loadRepo = loadRepo;
	}
	
	@Override
    @Transactional
    public LoadResponse createLoad(LoadRequest loadReq) {

        Load load = new Load();
        load.setShipperId(loadReq.getShipperId());
        load.setLoadingCity(loadReq.getLoadingCity());
        load.setUnloadingCity(loadReq.getUnloadingCity());
        load.setProductType(loadReq.getProductType());
        load.setWeight(loadReq.getWeight());
        load.setWeightUnit(loadReq.getWeightUnit());
        load.setTruckType(loadReq.getTruckType());
        load.setNoOfTrucks(loadReq.getNoOfTrucks());
        load.setStatus(LoadStatus.POSTED);

        Load saved = loadRepo.save(load);
        return mapToResponse(saved);
    }

    @Override
    public List<LoadResponse> listLoads(String shipperId, LoadStatus status, int page, int size) {
        PageRequest pr = PageRequest.of(page, size);

        if (shipperId == null && status == null) {
            return loadRepo.findAll(pr).stream()
                    .map(this::mapToResponse)
                    .collect(Collectors.toList());
        }

        return loadRepo.findAll().stream()
                .filter(l -> shipperId == null || l.getShipperId().equals(shipperId))
                .filter(l -> status == null || l.getStatus() == status)
                .skip((long) page * size)
                .limit(size)
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LoadResponse getLoadById(UUID loadId) {
        Load load = loadRepo.findById(loadId)
                .orElseThrow(() -> new ResourceNotFoundException("Load not found"));

        return mapToResponse(load);
    }

    @Override
    @Transactional
    public void cancelLoad(UUID loadId) {
        Load load = loadRepo.findById(loadId)
                .orElseThrow(() -> new ResourceNotFoundException("Load not found"));

        if (load.getStatus() == LoadStatus.BOOKED) {
            throw new InvalidStatusException("Booked load cannot be cancelled");
        }

        load.setStatus(LoadStatus.CANCELLED);
        loadRepo.save(load);
    }

    @Override
    public List<BidResponse> getBestBids(UUID loadId) {

        List<Bid> bids = bidRepo.findByLoad_LoadId(loadId);

        List<BidWithScore> scoredList = new ArrayList<>();

        for (Bid bid : bids) {
            Transporter transporter = bid.getTransporter();
            double rating = transporter.getRating();
            double score = (1.0 / bid.getProposedRate()) * 0.7 + (rating / 5.0) * 0.3;

            scoredList.add(new BidWithScore(bid, score));
        }

        scoredList.sort((a, b) -> Double.compare(b.score, a.score));

        return scoredList.stream()
                .map(bs -> mapBid(bs.bid))
                .collect(Collectors.toList());
    }

    private static class BidWithScore {
        Bid bid;
        double score;
        BidWithScore(Bid b, double s) { bid = b; score = s; }
    }

    private LoadResponse mapToResponse(Load l) {
        LoadResponse dto = new LoadResponse();

        dto.setLoadId(l.getLoadId());
        dto.setShipperId(l.getShipperId());
        dto.setLoadingCity(l.getLoadingCity());
        dto.setUnloadingCity(l.getUnloadingCity());
        dto.setLoadingDate(l.getLoadingDate());
        dto.setProductType(l.getProductType());
        dto.setWeight(l.getWeight());
        dto.setWeightUnit(l.getWeightUnit());
        dto.setTruckType(l.getTruckType());
        dto.setNoOfTrucks(l.getNoOfTrucks());
        dto.setStatus(l.getStatus());
        dto.setDatePosted(l.getDatePosted());

        return dto;
    }

    private BidResponse mapBid(Bid b) {
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
