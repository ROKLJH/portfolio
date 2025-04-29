package com.KoreaItAcdemy.OurNotion.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration; // @Configuration 어노테이션을 사용하기 위해 import
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Spring Security 관련 설정을 위한 Configuration 클래스입니다.
 * 이 클래스는 PasswordEncoder 빈(BCryptPasswordEncoder)을 등록하여
 * UserController에서 비밀번호 암호화 및 비교에 사용합니다.
 */
@Configuration
public class SecurityConfig {

    /**
     * PasswordEncoder 빈을 생성합니다.
     * BCryptPasswordEncoder를 사용하여 비밀번호를 안전하게 암호화합니다.
     *
     * @return PasswordEncoder 빈
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
