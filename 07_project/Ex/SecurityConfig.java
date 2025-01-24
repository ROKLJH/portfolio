package com.KoreaItAcdemy.OurNotion.config;

import com.KoreaItAcdemy.OurNotion.oauth.CustomOAuth2TokenResponseClient;
import com.KoreaItAcdemy.OurNotion.service.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.client.endpoint.OAuth2AccessTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequest;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/auth/loginSuccess")
                        .failureUrl("/auth/loginFailure")
                        .userInfoEndpoint(userInfo -> userInfo.userService(customOAuth2UserService))
                        .tokenEndpoint(token -> token.accessTokenResponseClient(customOAuth2AccessTokenResponseClient()))
                );

        return http.build();
    }

    @Bean
    public OAuth2AccessTokenResponseClient<OAuth2AuthorizationCodeGrantRequest> customOAuth2AccessTokenResponseClient() {
        return new CustomOAuth2TokenResponseClient();
    }
}
