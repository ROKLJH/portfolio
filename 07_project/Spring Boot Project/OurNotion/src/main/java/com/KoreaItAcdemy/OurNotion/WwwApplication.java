package com.KoreaItAcdemy.OurNotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude= {SecurityAutoConfiguration.class})
public class WwwApplication {
	// 실행 파일
	public static void main(String[] args) {
		SpringApplication.run(WwwApplication.class, args);
	}

}