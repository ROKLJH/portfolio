package com.KoreaItAcdemy.OurNotion.bean;

import lombok.Data;

import java.util.List;

@Data
public class BoardVO {
    private Long bno;
    private String title;
    private String content;
    private String writer;
    private String regdate;
    private String updatedate;
    private List<AttachFileVO> attachFile;
}