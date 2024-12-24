package com.koreait.board.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.koreait.board.bean.BoardVO;
import com.koreait.board.util.MyUtil;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BoardDAOTest {
	@Autowired
	private BoardDAO dao;
	
	@Test
	public void testRegister() {
		BoardVO board = new BoardVO();
		board.setTitle("DAO Test");
		board.setContent("for DAO Test");
		board.setWriter("DAO Tester");
		dao.register(board);
		log.info("" + board);
	}
	
	@Test
	public void testGetList() {
		dao.getList()
			.forEach(board -> log.info(MyUtil.BLUE + board + MyUtil.END));
	}
	
	@Test
	public void testRead() {
		log.info("" + dao.read(11L));
	}
	
	@Test
	public void testModify() {
		BoardVO vo = new BoardVO();
		vo.setBno(12L);
		vo.setTitle("DAO에서 수정하였습니다.");
		dao.modify(vo);
	}
}