package com.paypal.bfs.test.bookingserv.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paypal.bfs.test.bookingserv.entity.BookingData;

@Repository
public interface BookingDataRepository extends JpaRepository<BookingData, Long> {

	@Query(value = "SELECT * FROM booking_data u WHERE u.first_name = ?1 and u.last_name = ?2 and date_of_birth = ?3 and request_time > DATEADD('MINUTE',-5, NOW())", nativeQuery = true)
	List<BookingData> findRecentBookingForUser(String firstName, String lastName, String DOB);

}
