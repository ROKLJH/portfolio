package com.KoreaItAcdemy.OurNotion.controller;

import com.KoreaItAcdemy.OurNotion.bean.AttachFileVO;
import com.KoreaItAcdemy.OurNotion.bean.BoardVO;
import com.KoreaItAcdemy.OurNotion.bean.UserVO;
import com.KoreaItAcdemy.OurNotion.dao.BoardDAO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
@RequestMapping("/board/*")
@Slf4j
public class BoardController {

    @Autowired
    private BoardDAO dao;

    /*
     * 게시글 목록 조회
     * 로그인한 사용자의 부서번호(deptSeq)가 동일한 사용자 글만 보이도록 하고,
     * 검색 조건(type, keyword)이 있을 경우 부서 조건과 함께 검색한다.
     */
    @GetMapping("list")
    public String list(@RequestParam(value = "type", required = false) String type,
                       @RequestParam(value = "keyword", required = false) String keyword,
                       HttpSession session, Model model) {
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            // 로그인하지 않은 경우 빈 목록 및 메시지 전달
            model.addAttribute("list", List.of());
            model.addAttribute("msg", "로그인 후 게시글을 확인할 수 있습니다.");
        } else {
            String deptSeq = loginUser.getDeptSeq();
            if (keyword != null && !keyword.isEmpty()) {
                // 검색 조건이 있으면 부서 조건과 함께 검색
                model.addAttribute("list", dao.getListWithKey(deptSeq, type, keyword));
            } else {
                // 검색 조건이 없으면 부서 조건
                model.addAttribute("list", dao.getListByDept(deptSeq));
            }
        }
        return "board/list";
    }

    // 글쓰기 화면 (GET)
    @GetMapping("register")
    public RedirectView registerForm(HttpSession session, RedirectAttributes rttr) {
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            rttr.addFlashAttribute("msg", "로그인 후 글쓰기가 가능합니다.");
            return new RedirectView("/user/login");
        }
        // 로그인 상태면 등록 페이지로 이동 (board/register.html)
        return new RedirectView("/board/registerForm");
    }

    // 글쓰기 화면 호출 (실제 등록 폼 페이지: registerForm.html)
    @GetMapping("registerForm")
    public String registerForm(Model model) {
        // 새 BoardVO 객체를 모델에 추가 (폼 바인딩 대상)
        model.addAttribute("board", new BoardVO());
        return "board/registerForm"; // view 이름 반환
    }

    // 글쓰기 처리 (POST)
    @PostMapping("register")
    public RedirectView register(BoardVO board, HttpSession session, RedirectAttributes rttr) {
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            rttr.addFlashAttribute("msg", "로그인 후 글쓰기가 가능합니다.");
            return new RedirectView("/user/login");
        }
        // 작성자는 로그인한 사용자 id로 설정
        board.setWriter(loginUser.getId());
        log.info("글 등록: " + board);
        int result = dao.register(board);
        rttr.addFlashAttribute("msg", board.getBno() + "번 글이 등록되었습니다.");
        return new RedirectView("list");
    }

    // 글 읽기
    @GetMapping("read")
    public void read(Long bno, Model model, HttpSession session, RedirectAttributes rttr) {
        BoardVO board = dao.read(bno);
        if (board == null) {
            rttr.addFlashAttribute("msg", "글을 찾을 수 없습니다.");
            return;
        }
        model.addAttribute("vo", board);
    }

    // 글 수정 화면 (GET)
    @GetMapping("modify")
    public RedirectView modifyForm(Long bno, HttpSession session, RedirectAttributes rttr, Model model) {
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        BoardVO board = dao.read(bno);
        if (loginUser == null) {
            rttr.addFlashAttribute("msg", "로그인 후 수정이 가능합니다.");
            return new RedirectView("/user/login");
        }
        // 본인이 작성한 글이 아니면 수정 불가
        if (!loginUser.getId().equals(board.getWriter())) {
            rttr.addFlashAttribute("msg", "본인이 작성한 글만 수정 가능합니다.");
            return new RedirectView("read?bno=" + bno);
        }
        model.addAttribute("board", board);
        return new RedirectView("/board/modifyForm?bno=" + bno);
    }

    // 글 수정 호출
    @GetMapping("modifyForm")
    public void modifyForm(Long bno, Model model) {
        BoardVO board = dao.read(bno);
        model.addAttribute("board", board);
    }

    // 글 수정 처리
    @PostMapping("modify")
    public RedirectView modify(BoardVO board, HttpSession session, RedirectAttributes rttr) {
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        // 수정 시 작성자는 세션의 아이디로 고정
        board.setWriter(loginUser.getId());
        // 본인이 작성한 글인지 확인
        BoardVO original = dao.read(board.getBno());
        if (!loginUser.getId().equals(original.getWriter())) {
            rttr.addFlashAttribute("msg", "본인이 작성한 글만 수정 가능합니다.");
            return new RedirectView("read?bno=" + board.getBno());
        }
        int result = dao.modify(board);
        rttr.addFlashAttribute("msg", board.getBno() + "번 글이 수정되었습니다.");
        return new RedirectView("list");
    }

    // 글 삭제
    @GetMapping("remove")
    public RedirectView remove(Long bno, HttpSession session, RedirectAttributes rttr) {
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        BoardVO board = dao.read(bno);
        if (loginUser == null) {
            rttr.addFlashAttribute("msg", "로그인 후 삭제가 가능합니다.");
            return new RedirectView("/user/login");
        }
        if (!loginUser.getId().equals(board.getWriter())) {
            rttr.addFlashAttribute("msg", "본인이 작성한 글만 삭제 가능합니다.");
            return new RedirectView("read?bno=" + bno);
        }
        int result = dao.remove(bno);
        rttr.addFlashAttribute("msg", board.getBno() + "번 글이 삭제되었습니다.");
        return new RedirectView("list");
    }
//
//    // 게시글 첨부파일
//    @GetMapping(value="/getAttachList", produces= MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public List<AttachFileVO> getAttachList(Long bno) {
//        return dao.getAttachList(bno);
//    }

    /**
     * 첨부파일 목록을 JSON 형태로 반환하는 엔드포인트.
     * 클라이언트의 Ajax 요청에 의해 호출됩니다.
     */
    @GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AttachFileVO> getAttachList(@RequestParam("bno") Long bno) {
        return dao.getAttachList(bno);
    }

    /**
     * 첨부파일 다운로드 엔드포인트.
     * URL 예: /board/download?uuid=...&uploadPath=...&fileName=...
     *
     * @param uuid        파일의 UUID (저장 시 부여한 값)
     * @param uploadPath  파일이 저장된 하위 경로 (예: "2023/02/14")
     * @param fileName    원본 파일명 (다운로드 시 사용)
     * @return            파일 다운로드를 위한 ResponseEntity<Resource>
     */
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("uuid") String uuid,
                                                 @RequestParam("uploadPath") String uploadPath,
                                                 @RequestParam("fileName") String fileName) {
        // 저장된 파일명은 UUID와 원본 파일명을 조합하여 저장되므로, 이를 재구성합니다.
        String storedFileName = uuid + "_" + fileName;
        // 파일 업로드 시 사용한 기본 디렉토리와 동일하게 "C:/upload/temp" 사용
        String baseDir = "C:/upload/temp";
        // 전체 경로: baseDir + "/" + uploadPath + "/" + storedFileName
        File file = new File(baseDir + File.separator + uploadPath, storedFileName);

        if (!file.exists()) {
            // 파일이 존재하지 않으면 404 응답
            return ResponseEntity.notFound().build();
        }

        // 파일을 읽어 Resource로 변환
        Resource resource = new FileSystemResource(file);

        // 파일의 콘텐츠 타입 결정 (기본값은 "application/octet-stream")
        String contentType = "application/octet-stream";
        try {
            contentType = Files.probeContentType(file.toPath());
        } catch (IOException ex) {
            // 로깅 후 기본값 유지
            ex.printStackTrace();
        }

        // HTTP 응답 헤더 설정:
        // Content-Disposition 헤더를 통해 파일 다운로드(attachment)로 처리
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }
}
