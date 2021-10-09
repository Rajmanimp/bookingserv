package com.paypal.bfs.test.bookingserv.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.entity.BookingData;
import com.paypal.bfs.test.bookingserv.exception.ValidationException;
import com.paypal.bfs.test.bookingserv.repository.BookingDataRepository;

@Service
public class BookingService {

	@Autowired
	private BookingDataRepository bookingRepository;

	public Booking createBooking(Booking booking) {
		validateBookingData(booking);
		BookingData oldBooking = checkDuplicateRequest(booking);
		if (oldBooking != null) {
			return convertToDTO(oldBooking);
		}
		BookingData data = convertToEntity(booking);
		data = bookingRepository.save(data);
		return convertToDTO(data);
	}

	public List<Booking> getData() {
		List<BookingData> result = bookingRepository.findAll();
		List<Booking> response = new ArrayList<Booking>();
		for (BookingData d : result) {
			response.add(convertToDTO(d));
		}
		return response;
	}

	private void validateBookingData(Booking booking) {
		if (booking.getDeposit() > booking.getTotalPrice()) {
			throw new ValidationException("Deposit should be less then Total Price");
		}
		if (booking.getCheckoutDatetime().before(booking.getCheckinDatetime())) {
			throw new ValidationException("Check-out date should be after Check-in Date ");
		}
	}

	private BookingData checkDuplicateRequest(Booking booking) {
		List<BookingData> bookingDatas = bookingRepository.findRecentBookingForUser(booking.getFirstName(),
				booking.getLastName(), booking.getDateOfBirth());
		if (bookingDatas.size() > 0) {
			return bookingDatas.get(0);
		}
		return null;
	}

	private BookingData convertToEntity(Booking booking) {
		BookingData data = new BookingData();
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

	private Booking convertToDTO(BookingData booking) {
		Booking data = new Booking();
		data.setId(Integer.valueOf(booking.getId() + ""));
		data.setFirstName(booking.getFirstName());
		data.setLastName(booking.getLastName());
		data.setDateOfBirth(booking.getDateOfBirth());
		data.setCheckinDatetime(booking.getCheckinDatetime());
		data.setCheckoutDatetime(booking.getCheckoutDatetime());
		data.setTotalPrice(booking.getTotalPrice());
		data.setDeposit(booking.getDeposit());

		Address address = new Address();
		address.setLine1(booking.getLine1());
		address.setLine2(booking.getLine2());
		address.setCity(booking.getCity());
		address.setState(booking.getState());
		address.setZipcode(booking.getZipcode());
		data.setAddress(address);
		return data;
	}

}
