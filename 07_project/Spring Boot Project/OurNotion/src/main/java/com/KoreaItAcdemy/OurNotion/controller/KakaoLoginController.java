package com.KoreaItAcdemy.OurNotion.controller;

import com.KoreaItAcdemy.OurNotion.bean.UserVO;
import com.KoreaItAcdemy.OurNotion.dto.KakaoUserInfoResponseDTO;
import com.KoreaItAcdemy.OurNotion.service.KakaoService;
import com.KoreaItAcdemy.OurNotion.dao.UserDAO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("") // 기본 경로, 필요에 따라 조정
public class KakaoLoginController {

    private final KakaoService kakaoService;
    private final UserDAO userDAO; // UserDAO를 통해 DB 작업 수행
    private final PasswordEncoder passwordEncoder; // 임의의 비밀번호 생성을 위해

    /**
     * 카카오 로그인 callback 메서드
     * - 카카오 로그인 후, authorization code를 받아 access token을 요청하고,
     *   사용자 정보를 조회한 후, DB에 회원가입(없으면) 및 로그인 처리를 진행합니다.
     *
     * @param code 카카오에서 발급한 authorization code
     * @param session HttpSession, 로그인 후 사용자 정보를 저장하기 위해 사용
     * @return HTTP 상태 200(OK) 또는 적절한 상태 코드
     */
    @GetMapping("/callback")
    public RedirectView callback(@RequestParam("code") String code, HttpSession session) {
        // 1. 카카오에서 access token을 받아옴
        String accessToken = kakaoService.getAccessTokenFromKakao(code);
        log.info("카카오 Access Token: " + accessToken);

        // 2. access token을 사용하여 카카오 사용자 정보 조회
        KakaoUserInfoResponseDTO kakaoUserInfo = kakaoService.getUserInfo(accessToken);
        log.info("카카오 사용자 정보: " + kakaoUserInfo);

        // 3. 카카오 응답에서 필요한 값 추출
        // 카카오의 id는 숫자형이므로 문자열로 변환하여 사용 (T_USER의 ID 컬럼은 varchar(30))
        String kakaoId = String.valueOf(kakaoUserInfo.getId());
        // properties Map 안에 "nickname"과 "profile_image" 키가 존재한다고 가정
        String nickname = (String) kakaoUserInfo.getProperties().get("nickname");
        String profileImage = (String) kakaoUserInfo.getProperties().get("profile_image");

        // 4. DB에 해당 카카오 id의 회원이 이미 존재하는지 확인
        // (여기서는 카카오 회원은 일반 회원과 구분하기 위해 id를 그대로 사용하거나 "kakao_" 접두사를 붙여 저장할 수 있습니다.)
        UserVO user = userDAO.select(kakaoId);
        if (user == null) {
            // 회원이 존재하지 않으면 새로 회원가입
            user = new UserVO();
            user.setId(kakaoId);
            user.setName(nickname);
            user.setProfile(profileImage);
            user.setIsSocial(1); // 소셜 로그인 회원임을 표시 (0: 일반, 1: 소셜)
            // 카카오 로그인의 경우 비밀번호는 사용하지 않으므로 임의의 랜덤값을 설정합니다.
            String randomPwd = UUID.randomUUID().toString();
            user.setPwd(passwordEncoder.encode(randomPwd));
            // 나머지 필드는 기본값 또는 빈 문자열로 처리
            user.setGender(0);      // 성별 정보가 없는 경우 0 또는 null 처리
            user.setBirth("");
            user.setTelNum("");
            user.setDeptSeq("");
            user.setEmail("");
            // DB에 회원가입 처리 (UserDAO.register() 메서드를 사용)
            int result = userDAO.register(user);
            log.info("카카오 회원 신규 등록 결과: " + result);
        } else {
            log.info("이미 등록된 카카오 회원: " + user);
        }

        // 5. 로그인 처리: 세션에 로그인한 회원 정보를 저장 (기존 로그인과 동일한 방식)
        session.setAttribute("loginUser", user);
        log.info("로그인 성공: " + user);

        // 원하는 경우, 클라이언트를 특정 페이지(예: index.html)로 리다이렉트할 수 있도록 URL을 반환합니다.
        // 여기서는 간단하게 HTTP 200 OK 상태를 반환합니다.
        return new RedirectView("/index");
    }

}
