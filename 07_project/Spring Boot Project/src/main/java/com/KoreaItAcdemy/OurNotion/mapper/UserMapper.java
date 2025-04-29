package com.KoreaItAcdemy.OurNotion.mapper;

import com.KoreaItAcdemy.OurNotion.bean.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    // 신규 회원 등록 (일반가입 회원)
    public int insertUser(UserVO user);

    // 회원 리스트
    public List<UserVO> getUserList();

    // 회원 조회
    public UserVO getUser(String id);

    // 회원 업데이트
    public int updateUser(UserVO vo);

    // 회원 탈퇴
    public int deleteUser(int userSeq);
}
