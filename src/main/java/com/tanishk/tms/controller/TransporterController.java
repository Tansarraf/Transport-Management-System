package com.tanishk.tms.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanishk.tms.dto.AvailTruckRequest;
import com.tanishk.tms.dto.TransportRequest;
import com.tanishk.tms.dto.TransportResponse;
import com.tanishk.tms.service.TransporterService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/transporter")
public class TransporterController {
	
	private TransporterService transporterService;

    public TransporterController(TransporterService transporterService) {
        this.transporterService = transporterService;
    }
    
    @Operation(summary = "Register transporter")
    @ApiResponse(responseCode = "200")
    @PostMapping
    public ResponseEntity<TransportResponse> createTransporter(
            @RequestBody TransportRequest request) {

        return ResponseEntity.ok(transporterService.createTransporter(request));
    }

    @Operation(summary = "Get transporter details")
    @ApiResponse(responseCode = "200")
    @GetMapping("/{transporterId}")
    public ResponseEntity<TransportResponse> getTransporter(
            @PathVariable UUID transporterId) {

        return ResponseEntity.ok(transporterService.getTransporter(transporterId));
    }

    @Operation(summary = "Update available trucks")
    @ApiResponse(responseCode = "200")
    @PutMapping("/{transporterId}/trucks")
    public ResponseEntity<TransportResponse> updateTrucks(
            @PathVariable UUID transporterId,
            @RequestBody List<AvailTruckRequest> trucks) {
        return ResponseEntity.ok(transporterService.updateTrucks(transporterId, trucks));
    }
}
