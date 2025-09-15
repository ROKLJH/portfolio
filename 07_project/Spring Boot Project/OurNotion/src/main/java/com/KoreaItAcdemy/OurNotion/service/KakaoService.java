package com.KoreaItAcdemy.OurNotion.service;

import com.KoreaItAcdemy.OurNotion.dto.KakaoTokenResponseDTO;
import com.KoreaItAcdemy.OurNotion.dto.KakaoUserInfoResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoService {

    @Value("${kakao.client_id}")
    private String clientId;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    // 카카오 토큰 요청 및 사용자 정보 조회를 위한 호스트 URL들
    private final String KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
    private final String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";

    /**
     * 카카오로부터 access token을 가져오는 메서드
     * POST 요청의 본문에 form data로 파라미터를 전송합니다.
     *
     * @param code 카카오 로그인 후 callback에서 전달받은 authorization code
     * @return access token 문자열
     */
    public String getAccessTokenFromKakao(String code) {

        // 폼 데이터 생성 (MultiValueMap 사용)
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", clientId);
        formData.add("redirect_uri", redirectUri);
        formData.add("code", code);

        KakaoTokenResponseDTO kakaoTokenResponseDto = WebClient.create(KAUTH_TOKEN_URL_HOST)
                .post()
                .uri("/oauth/token")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoTokenResponseDTO.class)
                .block();

        log.info("[Kakao Service] Access Token: {}", kakaoTokenResponseDto.getAccessToken());
        log.info("[Kakao Service] Refresh Token: {}", kakaoTokenResponseDto.getRefreshToken());
        log.info("[Kakao Service] Id Token: {}", kakaoTokenResponseDto.getIdToken());
        log.info("[Kakao Service] Scope: {}", kakaoTokenResponseDto.getScope());

        return kakaoTokenResponseDto.getAccessToken();
    }

    /**
     * access token을 이용해 카카오 사용자 정보를 조회하는 메서드.
     *
     * @param accessToken 카카오에서 발급받은 access token
     * @return KakaoUserInfoResponseDTO 객체에 담긴 사용자 정보
     */
    public KakaoUserInfoResponseDTO getUserInfo(String accessToken) {

        KakaoUserInfoResponseDTO userInfo = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri("/v2/user/me")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse ->
                        Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoUserInfoResponseDTO.class)
                .block();

        log.info("[Kakao Service] Auth ID: {}", userInfo.getId());
        log.info("[Kakao Service] NickName: {}", userInfo.getKakaoAccount().getProfile().getNickName());
        log.info("[Kakao Service] ProfileImageUrl: {}", userInfo.getKakaoAccount().getProfile().getProfileImageUrl());

        return userInfo;
    }
}
