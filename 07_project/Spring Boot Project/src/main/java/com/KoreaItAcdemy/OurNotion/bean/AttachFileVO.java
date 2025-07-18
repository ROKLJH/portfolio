package com.KoreaItAcdemy.OurNotion.bean;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AttachFileVO {
    private String fileName;
    private String uploadPath;
    private String uuid;
    private boolean image;
    private Long bno;
}