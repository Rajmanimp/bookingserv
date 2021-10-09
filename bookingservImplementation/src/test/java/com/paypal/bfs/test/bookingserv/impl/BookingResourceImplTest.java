/**
 * 
 */
package com.paypal.bfs.test.bookingserv.impl;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.service.BookingService;

/**
 * @author rajmanip
 *
 */
@WebMvcTest(BookingResource.class)
public class BookingResourceImplTest {

	@MockBean
	private BookingService bookingService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("get all Bookings")
	public void shouldGetAllBookings() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/v1/bfs/booking"))
				.andExpect(MockMvcResultMatchers.status().is(200));
	}

	@Test
	@DisplayName("create valid Booking")
	public void shouldCreateBooking() throws Exception {
		Booking booking = createValidBookingData();
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/booking").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(booking))).andExpect(MockMvcResultMatchers.status().is(201));
	}

	@Test
	@DisplayName("invalid booking Booking")
	public void invalidBookingBooking() throws Exception {
		Booking booking = createValidBookingData();
		booking.setFirstName(null);
		mockMvc.perform(MockMvcRequestBuilders.post("/v1/bfs/booking").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(booking))).andExpect(MockMvcResultMatchers.status().is(400));
	}

	private Booking createValidBookingData() {
		Booking data = new Booking();
		data.setId(Integer.valueOf(1));
		data.setFirstName("Rajmani");
		data.setLastName("Patel");
		data.setDateOfBirth("1990-07-07");
		data.setCheckinDatetime(new Date());
		data.setCheckoutDatetime(new Date());
		data.setTotalPrice(1000);
		data.setDeposit(100);

		Address address = new Address();
		address.setLine1("Nai Basti");
		address.setLine2("Line 2");
		address.setCity("Satna");
		address.setState("MP");
		address.setZipcode(560068);
		data.setAddress(address);
		return data;
	}

}
