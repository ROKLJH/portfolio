package com.KoreaItAcdemy.OurNotion.controller;

import com.KoreaItAcdemy.OurNotion.bean.AttachFileVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@Slf4j
@RequestMapping("/upload/*")
public class UploadController {

    @PostMapping(value="uploadAjaxAction", produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody  // 메서드 종료 시 html(view)로 가지 않고 데이터를 리턴
    public List uploadAjaxPost(MultipartFile[] uploadFile) {
        log.info("[uploadController] uploadAjaxPost() Called OK");

        List<AttachFileVO> fileList = new ArrayList();

        String uploadFolder = "C:/upload/temp";
        String uploadFolderPath = getFolder();
        // yyyy/mm/dd 경로 생성
        File uploadPath = new File(uploadFolder, uploadFolderPath);

        // 디렉토리가 없으면 생성 / 있으면 skip
        if(uploadPath.exists()) {
            log.info("이미 디렉토리가 존재합니다.");
        }
        else {
            uploadPath.mkdirs();
        }

        for(MultipartFile f : uploadFile) {
            log.info("filename : " + f.getOriginalFilename());
            log.info("filesize : " + f.getSize());

            // UUID 적용
            // Network 상에서 각각의 개체를 식별하기 위해 사용
            String uploadFileName = f.getOriginalFilename();
            UUID uuid = UUID.randomUUID();
            log.info("uuid : " + uuid.toString());
            uploadFileName = uuid.toString() + "_" + uploadFileName;

            AttachFileVO attachFileVO = new AttachFileVO();
            attachFileVO.setFileName(uploadFileName);
            attachFileVO.setUuid(uuid.toString());
            attachFileVO.setUploadPath(uploadFolderPath);


            // 1. File Creation(Empty)
            //File saveFile = new File(uploadFolder, f.getOriginalFilename());
            File saveFile = new File(uploadPath, uploadFileName);

            // 2. Contents Copy
            try {
                f.transferTo(saveFile);

                if(checkImageType(saveFile)) {
                    log.info("Image File");
                    attachFileVO.setImage(true);
                }
                else {
                    log.info("Not Image File");
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }

            fileList.add(attachFileVO);
        }
        return fileList;
    }

    private boolean checkImageType(File file) {
        try {
            String contentType = Files.probeContentType(file.toPath());
            log.info("ContentType : " + contentType);
            return contentType.startsWith("image");
        }
        catch(Exception e) { e.printStackTrace(); }

        return false;
    }

    // 오늘 일자를 연/월/일 형태로 리턴한다.
    private String getFolder() {
        String str = null;

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        str = sdf.format(date);

        return str;
    }
}