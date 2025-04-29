package com.KoreaItAcdemy.OurNotion.dao;

import com.KoreaItAcdemy.OurNotion.bean.AttachFileVO;
import com.KoreaItAcdemy.OurNotion.bean.BoardVO;
import com.KoreaItAcdemy.OurNotion.mapper.AttachFileMapper;
import com.KoreaItAcdemy.OurNotion.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class BoardDAO {

    @Autowired
    private BoardMapper mapper;

    @Autowired
    private AttachFileMapper fmapper;

    // 전체 목록 조회
    public List<BoardVO> getList() {
        return mapper.getList();
    }

    // 부서별 조회
    public List<BoardVO> getListByDept(String deptSeq) {
        return mapper.getListByDept(deptSeq);
    }

    // 부서별 + 검색 조건 조회
    public List<BoardVO> getListWithKey(String deptSeq, String type, String keyword) {
        return mapper.getListWithKey(deptSeq, type, keyword);
    }

    // 글 등록
    public int register(BoardVO board) {
        int result = mapper.insertSelectKey(board);
        if (board.getAttachFile() != null) {
            board.getAttachFile().forEach(attach -> {
                attach.setBno(board.getBno());
                fmapper.insert(attach);
            });
        }
        return result;
    }

    // 글 조회
    public BoardVO read(Long bno) {
        return mapper.get(bno);
    }

    // 글 수정
    public int modify(BoardVO board) {
        return mapper.update(board);
    }

    // 글 삭제 (단일 게시글 삭제 시 첨부파일도 같이 삭제)
    public int remove(Long bno) {
        // 먼저 첨부파일 삭제
        fmapper.deleteByBno(bno);
        // 그 후 게시글 삭제
        return mapper.delete(bno);
    }

    // 회원 탈퇴 시 해당 사용자가 작성한 게시물 및 첨부파일 모두 삭제
    public int deleteByWriter(String writer) {
        // 먼저, 특정 작성자의 게시물 번호 목록 조회
        List<Long> boardSeqList = mapper.getBoardSeqListByWriter(writer);
        if (boardSeqList != null) {
            for (Long bno : boardSeqList) {
                // 각 게시물에 연결된 첨부파일 삭제
                fmapper.deleteByBno(bno);
            }
        }
        // 그 후, 게시물 삭제
        return mapper.deleteByWriter(writer);
    }

    // 첨부파일 가져오기
    public List<AttachFileVO> getAttachList(Long bno) {
        return fmapper.findByBno(bno);
    }
}
