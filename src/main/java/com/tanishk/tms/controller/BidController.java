package com.tanishk.tms.controller;

import com.tanishk.tms.constants.BidStatus;
import com.tanishk.tms.dto.BidRequest;
import com.tanishk.tms.dto.BidResponse;
import com.tanishk.tms.service.BidService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bid")
public class BidController {

    private final BidService bidService;

    public BidController(BidService bidService) {
        this.bidService = bidService;
    }

    @Operation(summary = "Submit a bid")
    @ApiResponse(responseCode = "200")
    @PostMapping
    public ResponseEntity<BidResponse> submitBid(@RequestBody BidRequest request) {
        return ResponseEntity.ok(bidService.submitBid(request));
    }

    @Operation(summary = "Filter bids")
    @ApiResponse(responseCode = "200")
    @GetMapping
    public ResponseEntity<List<BidResponse>> findBids(
            @RequestParam(required = false) UUID loadId,
            @RequestParam(required = false) UUID transporterId,
            @RequestParam(required = false) BidStatus status) {

        return ResponseEntity.ok(bidService.findBids(loadId, transporterId, status));
    }

    @Operation(summary = "Get bid details")
    @ApiResponse(responseCode = "200")
    @GetMapping("/{bidId}")
    public ResponseEntity<BidResponse> getBid(@PathVariable UUID bidId) {
        return ResponseEntity.ok(bidService.getBid(bidId));
    }

    @Operation(summary = "Reject the bid")
    @ApiResponse(responseCode = "200")
    @PatchMapping("/{bidId}/reject")
    public ResponseEntity<String> rejectBid(@PathVariable UUID bidId) {
        bidService.rejectBid(bidId);
        return ResponseEntity.ok("Bid was rejected successfully");
    }
}
