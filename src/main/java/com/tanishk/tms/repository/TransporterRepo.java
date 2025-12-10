package com.tanishk.tms.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tanishk.tms.entity.Transporter;

@Repository
public interface TransporterRepo extends JpaRepository<Transporter, UUID> {

}
