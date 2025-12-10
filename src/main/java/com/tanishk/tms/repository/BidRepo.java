package com.tanishk.tms.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tanishk.tms.constants.BidStatus;
import com.tanishk.tms.entity.Bid;

@Repository
public interface BidRepo extends JpaRepository<Bid, UUID> {
	
	List<Bid> findByLoad_LoadId(UUID loadId);

    List<Bid> findByTransporter_TransporterId(UUID transporterId);

    List<Bid> findByStatus(BidStatus status);

    boolean existsByLoad_LoadIdAndStatus(UUID loadId, BidStatus status);

    List<Bid> findByLoad_LoadIdAndStatus(UUID loadId, BidStatus status);

}
