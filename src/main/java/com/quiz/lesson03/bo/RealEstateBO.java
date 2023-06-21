package com.quiz.lesson03.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.lesson03.dao.RealEstateMapper;
import com.quiz.lesson03.domain.RealEstate;

@Service
public class RealEstateBO {
	
	@Autowired
	private RealEstateMapper realEstateMapper;

	//input: id
	//output: RealEstate => 컨트롤러한테
	public RealEstate getRealEstateById(int id) {
		return realEstateMapper.selectRealEstateById(id);
	}
	
	// input: rentPrice
	// output: List<RealEstate>
	public List<RealEstate> getRealEstateListByRentPrice(int rentPrice) {
		return realEstateMapper.selectRealEstateListByRentPrice(rentPrice);
	}
	
	//input: int, int
	//output: List<RealEstate>
	public List<RealEstate> getRealEstatesListByAreaAndPrice(int area, int price) {
		return realEstateMapper.selectRealEstatesListByAreaAndPrice(area, price);
	}
	
	public int addRealEstate(RealEstate realEstate) {
		return realEstateMapper.insertRealEstate(realEstate);
	}
	
	// realtorId
//		address : 썅떼빌리버 오피스텔 814호
//		area : 45
//		type : 월세
//		price : 100000
//		rentPrice : 120
		public int addRealEstateAsField(int realtorId, String address, int area,
				String type, int price, Integer rentPrice) {
			return realEstateMapper.insertRealEstateAsField(realtorId, address, area, type, price, rentPrice);
		}
		
		// input: id, type, price
		// output: int
		public int updateRealEstateBy(int id, String type, int price) {
			return realEstateMapper.updateRealEstateById(id, type, price);
		}
		
		public void deleteRealEstateById(int id) {
			realEstateMapper.deleteRealEstateById(id);
		}
}
