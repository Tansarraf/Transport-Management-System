package com.tanishk.tms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tanishk.tms.dto.AvailTruckRequest;
import com.tanishk.tms.dto.TransportRequest;
import com.tanishk.tms.dto.TransportResponse;
import com.tanishk.tms.entity.AvailableTruck;
import com.tanishk.tms.entity.Transporter;
import com.tanishk.tms.exception.ResourceNotFoundException;
import com.tanishk.tms.repository.TransporterRepo;

@Service
public class TransporterServiceImpl implements TransporterService{
	
	private final TransporterRepo transporterRepo;

	public TransporterServiceImpl(TransporterRepo transporterRepo) {
		this.transporterRepo = transporterRepo;
	}

	@Override
    @Transactional
    public TransportResponse createTransporter(TransportRequest transporterReq) {

        Transporter transporter = new Transporter();
        transporter.setCompanyName(transporterReq.getCompanyName());
        transporter.setRating(transporterReq.getRating());

        Transporter saved = transporterRepo.save(transporter);
        return map(saved);
    }

    @Override
    public TransportResponse getTransporter(UUID transporterId) {

        Transporter transporter = transporterRepo.findById(transporterId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter not found"));

        return map(transporter);
    }

    @Override
    @Transactional
    public TransportResponse updateTrucks(UUID transporterId, List<AvailTruckRequest> trucks) {

        Transporter transporter = transporterRepo.findById(transporterId)
                .orElseThrow(() -> new ResourceNotFoundException("Transporter not found"));

        List<AvailableTruck> list = new ArrayList<>();

        for (AvailTruckRequest req : trucks) {
            AvailableTruck at = new AvailableTruck();
            at.setTruckType(req.getTruckType());
            at.setCount(req.getCount());
            list.add(at);
        }

        transporter.setAvailableTrucks(list);
        transporterRepo.save(transporter);

        return map(transporter);
    }

    private TransportResponse map(Transporter t) {

        TransportResponse dto = new TransportResponse();
        dto.setTransporterId(t.getTransporterId());
        dto.setCompanyName(t.getCompanyName());
        dto.setRating(t.getRating());
        dto.setAvailableTrucks(t.getAvailableTrucks() == null ? null :
                t.getAvailableTrucks().stream().map(at -> {
                    AvailTruckRequest req = new AvailTruckRequest();
                    req.setTruckType(at.getTruckType());
                    req.setCount(at.getCount());
                    return req;
                }).collect(Collectors.toList())
        );

        return dto;
    }

}
