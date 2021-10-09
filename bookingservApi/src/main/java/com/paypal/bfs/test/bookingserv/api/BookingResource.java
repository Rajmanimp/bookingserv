package com.paypal.bfs.test.bookingserv.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paypal.bfs.test.bookingserv.api.model.Booking;

public interface BookingResource {
	/**
	 * Create {@link Booking} resource
	 *
	 * @param booking the booking object
	 * @return the created booking
	 */
	@RequestMapping(value = "/v1/bfs/booking", method = RequestMethod.POST)
	Booking create(@RequestBody Booking booking);

	/**
	 * Get all the bookings
	 * 
	 * @return the list of bookings made in the system
	 */
	@RequestMapping(value = "/v1/bfs/booking", method = RequestMethod.GET)
	ResponseEntity<List<Booking>> getBookings();

}
