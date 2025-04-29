package com.KoreaItAcdemy.OurNotion.controller;

import com.KoreaItAcdemy.OurNotion.bean.UserVO;
import com.KoreaItAcdemy.OurNotion.dao.BoardDAO;
import com.KoreaItAcdemy.OurNotion.dao.UserDAO;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;  // 파일 업로드 관련 클래스
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/user/*")
@Slf4j
public class UserController {

    @Autowired
    private UserDAO dao;

    @Autowired
    private BoardDAO boardDao; // 회원 탈퇴 시 게시글 삭제를 위해 사용

    // PasswordEncoder 빈 주입 (BCryptPasswordEncoder 사용)
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("index")
    public void index() { }

    // userRegister(회원가입) 화면 호출용
    @GetMapping("userRegister")
    public void register() { }

    /**
     * 회원가입 처리 메서드.
     * 프로필 사진(MultipartFile)은 별도의 파라미터로 받아 서버에 저장 후,
     * 저장된 파일 경로를 UserVO.profile에 세팅하여 DB에 저장합니다.
     */
    @PostMapping("userRegister")
    public RedirectView register(UserVO user,
                                 @RequestParam(value = "profileFile", required = false) MultipartFile profileFile,
                                 RedirectAttributes rttr) {
        log.info("회원가입 요청: " + user);

        // 프로필 사진이 선택된 경우 파일 업로드 처리
        if (profileFile != null && !profileFile.isEmpty()) {
            // 서버에 저장할 디렉토리 (예: C:/upload/profile)
            String uploadDir = "C:/upload/profile";
            File uploadFolder = new File(uploadDir);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs();
            }
            // 파일 확장자 검사 (서버 측 추가 확인)
            String originalFilename = profileFile.getOriginalFilename();
            if (originalFilename != null && !originalFilename.matches("(?i).*\\.(png|jpe?g|webp|bmp)$")) {
                rttr.addFlashAttribute("msg", "사진파일(.png, .jpg, .jpeg, .webp, .bmp)만 등록 가능합니다.");
                return new RedirectView("/user/userRegister");
            }
            // 파일 크기 검사 (2MB 제한)
            if (profileFile.getSize() > 2097152) {
                rttr.addFlashAttribute("msg", "프로필 사진의 최대 용량은 2mb까지 입니다.");
                return new RedirectView("/user/userRegister");
            }
            try {
                // UUID를 사용하여 새로운 파일명 생성
                String uuid = UUID.randomUUID().toString();
                String newFilename = uuid + "_" + originalFilename;
                File saveFile = new File(uploadFolder, newFilename);
                profileFile.transferTo(saveFile);
                // DB에 저장할 값: 파일 경로 (예: /upload/profile/새파일명)
                user.setProfile("/upload/profile/" + newFilename);
            } catch (Exception e) {
                e.printStackTrace();
                rttr.addFlashAttribute("msg", "프로필 사진 업로드에 실패했습니다.");
                return new RedirectView("/user/userRegister");
            }
        }

        // 회원가입 전에 원시 비밀번호를 암호화 처리
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        int result = dao.register(user);
        log.info(result + "회원 가입 완료");
        rttr.addFlashAttribute("msg", user.getUserSeq() + "회원이 가입 되었습니다.");
        return new RedirectView("userRegister_ok");
    }

    // userRegister(회원가입) 화면 호출용
    @GetMapping("userRegister_ok")
    public void register_ok() { }

    // 이하 로그인, 회원정보 확인, 수정, 탈퇴 등의 엔드포인트는 기존과 동일

    @GetMapping("login")
    public String loginForm(Model model) {
        return "user/login";
    }

    @PostMapping("login")
    public RedirectView loginProcess(String id, String pwd, HttpSession session, RedirectAttributes rttr) {
        log.info("로그인 시도: id = " + id);
        UserVO user = dao.select(id);
        if (user != null && user.getPwd() != null && passwordEncoder.matches(pwd, user.getPwd())) {
            session.setAttribute("loginUser", user);
            log.info("로그인 성공: " + user);
            return new RedirectView("/");
        } else {
            rttr.addFlashAttribute("msg", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return new RedirectView("/user/login");
        }
    }

    @GetMapping("logout")
    public RedirectView logout(HttpSession session) {
        session.invalidate();
        return new RedirectView("/");
    }

    @GetMapping("userCheck")
    public String userCheck(Model model, HttpSession session, RedirectAttributes rttr) {
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            rttr.addFlashAttribute("msg", "로그인 후 접근 가능합니다.");
            return "redirect:/user/login";
        }
        model.addAttribute("user", loginUser);
        return "user/userCheck";
    }

    @GetMapping("userModify")
    public String userModifyForm(Model model, HttpSession session, RedirectAttributes rttr) {
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            rttr.addFlashAttribute("msg", "로그인 후 접근 가능합니다.");
            return "redirect:/user/login";
        }
        model.addAttribute("user", loginUser);
        return "user/userModify";
    }

    // 회원정보 수정 처리: 비밀번호 암호화 적용
    @PostMapping("userModify")
    public RedirectView userModify(UserVO user, HttpSession session, RedirectAttributes rttr) {
        // 만약 비밀번호가 수정되었다면, 새 비밀번호를 암호화 처리
        user.setPwd(passwordEncoder.encode(user.getPwd()));
        int result = dao.modify(user);
        if (result > 0) {
            rttr.addFlashAttribute("msg", "회원정보가 수정되었습니다.");
            // 세션 정보 업데이트: 수정된 회원 정보로 교체
            session.setAttribute("loginUser", user);
        } else {
            rttr.addFlashAttribute("msg", "회원정보 수정에 실패했습니다.");
        }
        return new RedirectView("/user/userCheck");
    }

    @GetMapping("userDelete")
    public String userDeleteConfirm(Model model, HttpSession session, RedirectAttributes rttr) {
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            rttr.addFlashAttribute("msg", "로그인 후 탈퇴가 가능합니다.");
            return "redirect:/user/login";
        }
        model.addAttribute("user", loginUser);
        return "user/userDelete";
    }

    // 회원 탈퇴 처리 및 완료 페이지 (userDeleteOk.html)
    @GetMapping("userDeleteOk")
    public String userDeleteOk(HttpSession session, RedirectAttributes rttr) {
        UserVO loginUser = (UserVO) session.getAttribute("loginUser");
        if (loginUser == null) {
            rttr.addFlashAttribute("msg", "로그인 후 탈퇴가 가능합니다.");
            return "redirect:/user/login";
        }
        // 먼저, 해당 사용자가 작성한 게시글 및 첨부파일을 모두 삭제
        int result = dao.deleteUser(loginUser.getUserSeq());
        // (회원 탈퇴 시 BoardDAO.deleteByWriter() 호출은 UserController에서 boardDao.deleteByWriter(loginUser.getId())로 처리되었음)
        // 예를 들어:
        boardDao.deleteByWriter(loginUser.getId());

        if (result > 0) {
            rttr.addFlashAttribute("msg", "탈퇴가 완료되었습니다.");
            session.invalidate();
        } else {
            rttr.addFlashAttribute("msg", "회원 탈퇴에 실패했습니다.");
            return "redirect:/user/userCheck";
        }
        return "user/userDeleteOk";
    }

    @GetMapping("/user/idCheck")
    @ResponseBody
    public Map<String, Boolean> idCheck(@RequestParam("id") String id) {
        Map<String, Boolean> result = new HashMap<>();
        UserVO user = dao.select(id);
        result.put("available", (user == null));
        log.info("ID Check for {}: available = {}", id, (user == null));
        return result;
    }

    @GetMapping("/profile")
    public ResponseEntity<String> checkProfile(HttpSession session) {
        UserVO loginUser = (UserVO) session.getAttribute("loginUser"); // UserVO로 변경
        if (loginUser != null) {
            return ResponseEntity.ok("프로필 이미지 경로: " + loginUser.getProfile()); // getProfile() 사용
        } else {
            return ResponseEntity.ok("로그인 정보 없음");
        }
    }
}
