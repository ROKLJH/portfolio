package com.KoreaItAcdemy.OurNotion.mapper;

import com.KoreaItAcdemy.OurNotion.bean.AttachFileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttachFileMapper {
    public void insert(AttachFileVO vo);
    public List<AttachFileVO> findByBno(Long bno);

    // 추가: 특정 게시물(BoardSeq)에 속한 첨부파일 모두 삭제하는 메서드
    public int deleteByBno(Long bno);
}
