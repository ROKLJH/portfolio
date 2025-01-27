package com.koreait.board.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.koreait.board.bean.BoardVO;
import com.koreait.board.util.MyUtil;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BoardMapperTest {
	@Autowired
	private BoardMapper mapper;
	
	// @Test
	public void insertTest() {
		BoardVO vo = new BoardVO();
		vo.setTitle("자동제목");
		vo.setContent("테스트");
		vo.setWriter("테스터");
		log.info("BoardVO = " + vo);
		
		//if(mapper.insert(vo) == 1) {
		if(mapper.insertSelectKey(vo) == 1) {		
			log.info(MyUtil.BLUE + "Insert 성공하셨습니다." + MyUtil.END);
		}
		else {
			log.info(MyUtil.BLUE + "Insert 대실패하셨습니다." + MyUtil.END);
		}
		
		log.info("BoardVO = " + vo);
		
		for(int i=0; i<15; i++) {
			vo = new BoardVO();
			vo.setTitle("테스트" + i);
			vo.setContent("내용" + i);
			vo.setWriter("나");
			mapper.insert(vo);
		}
	}
	
	@Test
	public void testGetList() {
		
		// Lambda 문법
		mapper.getList()
		      .forEach(board -> log.info(board.toString()));
	}
	
	@Test
	public void testGet() {
		Long bno = 11L;
		BoardVO vo = mapper.get(bno);
		log.info("TestGet() : " + vo);
	}
	
	@Test
	public void testUpdate() {
		log.info("Test Update");
		BoardVO board = new BoardVO();
		board.setBno(11L);
		board.setTitle("수정한 제목");
		board.setContent("절대 읽으시면 안됩니다.");
		board.setWriter("지은이 아닌데 수정되나요");
		mapper.update(board);
	}
}
