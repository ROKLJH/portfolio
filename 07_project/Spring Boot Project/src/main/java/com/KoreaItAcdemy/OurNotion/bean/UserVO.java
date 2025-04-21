package com.KoreaItAcdemy.OurNotion.bean;

import lombok.Data;
import java.util.Date;

@Data
public class UserVO {
    private Integer userSeq;
    private String id;
    private String pwd;
    private Integer gender;
    private String name;
    private String birth;
    private String telNum;
    private String deptSeq;
    private String email;
    private Integer isSocial;
    private String profile;
    private String lastDateTime;
}
