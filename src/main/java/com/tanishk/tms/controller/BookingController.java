package com.tanishk.tms.controller;

import com.tanishk.tms.dto.BookingRequest;
import com.tanishk.tms.dto.BookingResponse;
import com.tanishk.tms.service.BookingService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @Operation(summary = "Create a new booking")
    @ApiResponse(responseCode = "200")
    @PostMapping
    public ResponseEntity<BookingResponse> acceptBid(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.acceptBid(request));
    }

    @Operation(summary = "Get booking details")
    @ApiResponse(responseCode = "200")
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingResponse> getBooking(@PathVariable UUID bookingId) {
        return ResponseEntity.ok(bookingService.getBooking(bookingId));
    }

    @Operation(summary = "Cancel booking")
    @ApiResponse(responseCode = "200")
    @PatchMapping("/{bookingId}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable UUID bookingId) {
        bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok("Booking cancelled successfully");
    }
}
