package com.tanishk.tms.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tanishk.tms.entity.Booking;

@Repository
public interface BookingRepo extends JpaRepository<Booking, UUID> {
	List<Booking> findByLoad_LoadId(UUID loadId);

    List<Booking> findByTransporter_TransporterId(UUID transporterId);

}
