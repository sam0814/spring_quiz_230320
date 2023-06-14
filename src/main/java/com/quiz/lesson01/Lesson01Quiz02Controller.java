package com.quiz.lesson01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lesson01/quiz02")
public class Lesson01Quiz02Controller {
	
	
	// http://localhost:8080/lesson01/quiz02/1
	@RequestMapping("/1")
	public List<Map<String, Object>> quiz02_1() {
		List<Map<String, Object>> resultList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("rate", 15);
		map.put("director", "봉준호");
		map.put("time", 131);
		map.put("title", "기생충");
		resultList.add(map);
		
		map.put("rate", 0);
		map.put("director", "로베르토 베니니");
		map.put("time", 116);
		map.put("title", "인생은 아름다워");
		resultList.add(map);
		
		map.put("rate", 12);
		map.put("director", "크리스토퍼 놀란");
		map.put("time", 147);
		map.put("title", "인셉션");
		resultList.add(map);
		
		map.put("rate", 19);
		map.put("director", "윤종빈");
		map.put("time", 133);
		map.put("title", "범죄와의 전쟁: 나쁜놈들의 전성시대");
		resultList.add(map);
		
		map.put("rate", 15);
		map.put("director", "프란시스 로렌스");
		map.put("time", 137);
		map.put("title", "헝거게임");
		resultList.add(map);
		
		return resultList;
		
	}
	
	// http://localhost:8080/lesson01/quiz02/1
	@RequestMapping("/2")
	public List<Board> quiz02_2(){
		//@ResponseBody + return String => HttpMessageConverter		HTML
		//@@ResponseBody + return 객체 => HttpMessageConverter		jackson 라이브러리 => JSON String
		
		List<Board> resultList = new ArrayList<>();
		Board board = new Board(); //일반 java bean
		board.setUser("안녕하세요 가입인사 드립니다");
		board.setTitle("hagulu");
		board.setContent("안녕하세요. 가입했어요. 앞으로 잘 부탁드립니다.");
		resultList.add(board);
		
		board.setUser("안녕하세요 가입인사 드립니다");
		board.setTitle("hagulu");
		board.setContent("안녕하세요. 가입했어요. 앞으로 잘 부탁드립니다.");
		resultList.add(board);
		
		board.setUser("안녕하세요 가입인사 드립니다");
		board.setTitle("hagulu");
		board.setContent("안녕하세요. 가입했어요. 앞으로 잘 부탁드립니다.");
		resultList.add(board);
		
		return resultList;
	}
	
	@RequestMapping("/3")
	public ResponseEntity<Board> quiz02_3() {
		Board board = new Board();
		board.setUser("안녕하세요 가입인사 드립니다");
		board.setTitle("hagulu");
		board.setContent("안녕하세요. 가입했어요. 앞으로 잘 부탁드립니다.");
		
		return new ResponseEntity<>(board, HttpStatus.INTERNAL_SERVER_ERROR);
	
	}

}
