package com.KoreaItAcdemy.OurNotion.dao;

import com.KoreaItAcdemy.OurNotion.bean.UserVO;
import com.KoreaItAcdemy.OurNotion.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {

    @Autowired
    private UserMapper mapper;

    // 회원가입
    public int register(UserVO user) {
        return mapper.insertUser(user);
    }

    // 회원선택
    public UserVO select(String id) {
        return mapper.getUser(id);
    }

    // 정보 수정
    public int modify(UserVO user) {
        return mapper.updateUser(user);
    }

    // 회원 탈퇴
    public int deleteUser(int userSeq) {
        return mapper.deleteUser(userSeq);
    }
}
