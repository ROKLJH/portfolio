package com.koreait.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.board.bean.ArtVO;
import com.koreait.board.util.MyUtil;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ex/*")  // loaclhost:8088/ex는 제껍니다.
public class ExampleController {
	
	@GetMapping("")
	public void nothing() {
		log.info("Nothing Method is called");
	}
	
	@PostMapping("sports")
	public String postSports() {
	// Controller에서 void는 메서드가 종료되면 자동으로 Mapping keyword로 이동한다.
	// void getSprots()는 template/ex/sports.html을 찾고 있음
		log.info(MyUtil.BLUE + "My favorite sports is the baseball." + MyUtil.END);
		return "index";
	}
	
	@GetMapping("music")
	public String getMusic() {
		log.info(MyUtil.BOLD + "My favorite muisc is the classical music" + MyUtil.END);
		return "/ex/sports";
	}
	
	// classic way
	// http://localhost:10000/ex/classic?title=nocturne&artist=Chopin
	@GetMapping("classic")
	public String classicTest(HttpServletRequest req) {
		String title = req.getParameter("title");
		String artist = req.getParameter("artist");
		log.info("classic : " + title + "(" + artist + ")");
		return "index";
	}
	
	// spring way I
	// http://localhost:10000/ex/modern?title=nocturne&artist=Chopin
	@GetMapping("modern")
	public String modern(String title, String artist) {
		log.info("modern : " + title + "(" + artist + ")");
		return "index";
	}
	
	// spring way II
	// http://localhost:10000/ex/future?title=nocturne&artist=Chopin
	@GetMapping("future")
	public String future(ArtVO artVO, String title) {
		log.info("future : " + artVO);
		log.info("future : " + title);
		return "index";
	}
}
