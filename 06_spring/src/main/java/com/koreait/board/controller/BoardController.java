package com.koreait.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.koreait.board.bean.BoardVO;
import com.koreait.board.dao.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board/*")
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardDAO dao;
	
	// List
	@GetMapping("list")
	public void list(Model model) {
		log.info("-------------------------------------------------->");
		log.info("Get List Called");
		model.addAttribute("list", dao.getList());
	}
	
	// register(글쓰기) 화면 호출용
	@GetMapping("register")
	public void register() {
		
	}
	
	// register(글쓰기) 처리용
	@PostMapping("register")
	public RedirectView write(BoardVO board, RedirectAttributes rttr) {
		log.info("글 쓴댄다 : " + board);
		log.info(dao.register(board) + "건 등록 완료");
		
		rttr.addFlashAttribute("msg", board.getBno() + "번 글이 등록되었습니다.");
		
		return new RedirectView("list");
	}
	
	// read(글읽기) 처리용
	// localhost:8088/board/read?bno=N를 호출했을 때 내용을 보여주는 페이지
	@RequestMapping("read")
	public void read(Long bno, Model model) {
		log.info("------------------------------>");
		log.info("read : bno =" + bno);
		model.addAttribute("vo", dao.read(bno));
	}
	
	// remove 처리용
	// localhost:8088/board/remove?bno=N를 호출했을 때 삭제하는 기능
	@RequestMapping("remove")
	public RedirectView remove(Long bno, RedirectAttributes rttr) {
		log.info("------------------------------>");
		// log.info("삭제 건수 : " + dao.remove(bno));
		log.info("------------------------------>");
		
		if(dao.remove(bno) > 0) {
			rttr.addFlashAttribute("msg", "글 삭제에 성공하였습니다.");
		}
		else {
			rttr.addFlashAttribute("msg", "너 지금 뭐한거냐?");
		}
		
		return new RedirectView("list");
	}
}
