package com.paypal.bfs.test.bookingserv.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.entity.BookingData;
import com.paypal.bfs.test.bookingserv.repository.BookingDataRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BookingServiceTest {

	@MockBean
	private BookingDataRepository bookingDataRepository;

	@InjectMocks
	private BookingService bookingService;

	@Test
	public void getDataTest() {
		Booking booking = createValidBookingData();
		List<Booking> lst1 = new ArrayList<Booking>();
		lst1.add(booking);
		List<BookingData> lst2 = new ArrayList<BookingData>();
		BookingData bookingData = convertToEntity(booking);
		lst2.add(bookingData);
		Mockito.when(bookingDataRepository.findAll()).thenReturn(lst2);
		List<Booking> result = bookingService.getData();
		assertTrue(result.size() == 1);
	}

	private Booking createValidBookingData() {
		Booking data = new Booking();
		data.setId(1);
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

	private BookingData convertToEntity(Booking booking) {
		BookingData data = new BookingData();
		data.setId(Long.valueOf(1));
		data.setFirstName(booking.getFirstName());
		data.setLastName(booking.getLastName());
		data.setDateOfBirth(booking.getDateOfBirth());
		data.setCheckinDatetime(booking.getCheckinDatetime());
		data.setCheckoutDatetime(booking.getCheckoutDatetime());
		data.setTotalPrice(booking.getTotalPrice());
		data.setDeposit(booking.getDeposit());

		Address address = booking.getAddress();
		data.setLine1(address.getLine1());
		data.setLine2(address.getLine2());
		data.setCity(address.getCity());
		data.setState(address.getState());
		data.setZipcode(address.getZipcode());

		return data;
	}

}
