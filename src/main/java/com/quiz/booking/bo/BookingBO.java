package com.quiz.booking.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.quiz.booking.dao.BookingMapper;
import com.quiz.booking.domain.Booking;

@Service
public class BookingBO {
	
	@Autowired
	private BookingMapper bookingMapper;

	public List<Booking> getBookingList() {
		return bookingMapper.selectBookingList();
	}
	
	public int deleteBookingById(int id) {
		return bookingMapper.deleteBookingById(id);
	} 
	
	public int addBooking(String name, String date, int day, int headcount, String phoneNumber) {
		return bookingMapper.insertBooking(name, date, day, headcount, phoneNumber);
	}
	
	public Booking getBookingByNameAndPhoneNumber(String name, String phoneNumber) {
		// 가능한 값: [], [booking,...]
		List<Booking> bookingList = bookingMapper.selectBookingByNameAndPhoneNumber(name, phoneNumber);
		if (bookingList.isEmpty()) {
			return null;
		}
		
		return bookingList.get(bookingList.size() - 1);	// 마지막 값을 준다. (최신 예약)
	}
	
}


