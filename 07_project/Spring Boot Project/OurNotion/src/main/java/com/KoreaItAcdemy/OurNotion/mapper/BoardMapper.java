package com.KoreaItAcdemy.OurNotion.mapper;

import com.KoreaItAcdemy.OurNotion.bean.BoardVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface BoardMapper {

    // 게시글 추가 (일반 INSERT)
    int insert(BoardVO vo);

    // 게시글 추가 (SelectKey를 통한 채번 INSERT)
    int insertSelectKey(BoardVO vo);

    // 전체 게시글 목록 조회
    List<BoardVO> getList();

    // 부서별 + 검색 조건 조회
    // 파라미터로 부서번호, 검색 유형(type), 키워드(keyword)를 받음
    List<BoardVO> getListWithKey(@Param("deptSeq") String deptSeq,
                                 @Param("type") String type,
                                 @Param("keyword") String keyword);

    // 단일 게시글 조회
    BoardVO get(Long bno);

    // 부서별 게시글 조회 (부서번호에 해당하는 사용자 ID를 가진 글)
    List<BoardVO> getListByDept(String deptSeq);

    // 게시글 업데이트
    int update(BoardVO vo);

    // 게시글 삭제
    int delete(Long bno);

    // 회원 탈퇴 시, 해당 사용자가 작성한 게시글 모두 삭제
    int deleteByWriter(String writer);

    // 추가: 특정 작성자의 게시물 번호(BoardSeq) 목록 조회
    List<Long> getBoardSeqListByWriter(String writer);
}
