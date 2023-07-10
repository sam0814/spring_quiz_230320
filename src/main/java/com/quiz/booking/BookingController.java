package com.quiz.booking;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quiz.booking.bo.BookingBO;
import com.quiz.booking.domain.Booking;

@RequestMapping("/booking")
@Controller
public class BookingController {
	
	@Autowired
	private BookingBO bookingBO;

	// 1) 예약 목록 보기 화면
	@GetMapping("/booking_list_view")
	public String bookingListView(Model model) {
		List<Booking> bookingList = bookingBO.getBookingList();
		model.addAttribute("bookingList", bookingList);
		return "booking/bookingList";
	}
	
	// 예약 내역 한 건 삭제
	// AJAX가 하는 요청
	@ResponseBody
	@DeleteMapping("/delete_booking")
	public Map<String, Object> deleteBooking(
			@RequestParam("id") int id) {
		
		// db delete
		int row = bookingBO.deleteBookingById(id);
		
		Map<String, Object> result = new HashMap<>();
		if (row > 0) {
			// 성공
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			// 삭제할 데이터 없음
			result.put("code", 500);
			result.put("errorMessage", "삭제될 데이터가 없습니다.");
		}
		
		return result;
	}
	
	// 2) 예약 하기 화면
	@GetMapping("/make_booking_view")
	public String makeBookingView() {
		return "booking/makeBooking";
	}
	
	// 예약 항목 추가
	// AJAX가 하는 요청
	@ResponseBody
	@PostMapping("/make_booking")
	public Map<String, Object> makeBooking(
			@RequestParam("name") String name,
			@RequestParam("date") String date,	// @DateTimeFormat을 붙이고 Date 객체로 받아와도 된다.
			@RequestParam("day") int day,
			@RequestParam("headcount") int headcount,
			@RequestParam("phoneNumber") String phoneNumber) {
		
		// insert db
		int row = bookingBO.addBooking(name, date, day, headcount, phoneNumber);
		
		// 응답
		Map<String, Object> result = new HashMap<>();
		if (row > 0) {
			result.put("code", 1);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "예약 데이터가 추가되지 못했습니다.");
		}
		
		return result;
	}
	
	// 3) 예약 조회 화면
	@GetMapping("/search_booking_view")
	public String searchBookingView() {
		return "booking/searchBooking";
	}
	
	// AJAX가 하는 요청
	// 예약 내역 조회
	@ResponseBody
	@PostMapping("/search_booking")
	public Map<String, Object> searchBooking(
			@RequestParam("name") String name,
			@RequestParam("phoneNumber") String phoneNumber) {
		
		// db select
		Booking booking = bookingBO.getBookingByNameAndPhoneNumber(name, phoneNumber);
		
		// 응답 JSON
		Map<String, Object> result = new HashMap<>();
		if (booking == null) {
			result.put("code", 300);
			result.put("errorMessage","예약 내역이 없습니다.");
		} else {
			result.put("code", 1);
			result.put("booking", booking);
		}
		
		return result;
	}
	
	
	
	
}