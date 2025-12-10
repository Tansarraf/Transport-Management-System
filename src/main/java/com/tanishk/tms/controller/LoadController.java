package com.tanishk.tms.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tanishk.tms.constants.LoadStatus;
import com.tanishk.tms.dto.BidResponse;
import com.tanishk.tms.dto.LoadRequest;
import com.tanishk.tms.dto.LoadResponse;
import com.tanishk.tms.service.LoadService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/load")
public class LoadController {
	
	private LoadService loadService;

	public LoadController(LoadService loadService) {
		this.loadService = loadService;
	}
	
	@Operation(summary = "Create a new load")
    @ApiResponse(responseCode = "200")
	@PostMapping
	public ResponseEntity<LoadResponse> createLoad(@RequestBody LoadRequest loadRequest){
		return ResponseEntity.ok(loadService.createLoad(loadRequest));
	}
	
	@Operation(summary = "Paginated list of loads")
    @ApiResponse(responseCode = "200")
	@GetMapping
	public ResponseEntity<List<LoadResponse>> listLoads(@RequestParam(required = false) String shipperID, @RequestParam(required = false) LoadStatus status,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
		return ResponseEntity.ok(loadService.listLoads(shipperID, status, page, size));
	}
	
	@Operation(summary = "Get load with active bids")
    @ApiResponse(responseCode = "200")
	@GetMapping("/{loadId}")
    public ResponseEntity<LoadResponse> getLoad(@PathVariable UUID loadId) {
        return ResponseEntity.ok(loadService.getLoadById(loadId));
    }

	@Operation(summary = "Cancel a load")
    @ApiResponse(responseCode = "200")
    @PatchMapping("/{loadId}/cancel")
    public ResponseEntity<String> cancelLoad(@PathVariable UUID loadId) {
        loadService.cancelLoad(loadId);
        return ResponseEntity.ok("Load was cancelled successfully");
    }

	@Operation(summary = "Get sorted bid suggestions")
    @ApiResponse(responseCode = "200")
    @GetMapping("/{loadId}/best-Bid")
    public ResponseEntity<List<BidResponse>> getBestBids(@PathVariable UUID loadId) {
        return ResponseEntity.ok(loadService.getBestBids(loadId));
    }
}
