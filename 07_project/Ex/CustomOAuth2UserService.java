package com.KoreaItAcdemy.OurNotion.service;

import com.KoreaItAcdemy.OurNotion.domain.User;
import com.KoreaItAcdemy.OurNotion.repository.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oauth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oauth2User.getAttributes();

        // "id" 속성이 있는지 확인 후 가져오기
        if (!attributes.containsKey("id")) {
            throw new IllegalArgumentException("OAuth2 응답에 'id' 필드가 없습니다.");
        }
        String kakaoId = attributes.get("id").toString();

        User user = userRepository.findByKakaoId(kakaoId)
                .orElseGet(() -> userRepository.save(new User(kakaoId)));

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                attributes,
                "id"
        );
    }
}
