package com.koreait.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.board.bean.BoardVO;
import com.koreait.board.dao.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board/*")
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardDAO dao;
	
	// register(글쓰기) 화면 호출용
	@GetMapping("register")
	public void register() {
		
	}
	
	// register(글쓰기) 처리용
	@PostMapping("register")
	public void write(BoardVO board) {
		log.info("글 쓴댄다 : " + board);
		log.info(dao.register(board) + "건 등록 완료");
	}
}
