package com.paypal.bfs.test.bookingserv.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.service.BookingService;

import io.swagger.annotations.ApiOperation;

@RestController
public class BookingResourceImpl implements BookingResource {

	@Autowired
	private BookingService bookingService;

	@Override
	@ApiOperation(value = "API to create booking. ", response = Booking.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Booking create(@Valid @RequestBody Booking booking) {
		Booking response = bookingService.createBooking(booking);
		return response;
	}

	@Override
	@ApiOperation(value = "API to fetch all the bookings.", response = Booking.class)
	public ResponseEntity<List<Booking>> getBookings() {
		List<Booking> response = bookingService.getData();
		return ResponseEntity.ok(response);
	}
}
