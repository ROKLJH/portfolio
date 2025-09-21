\# Spring Boot Project - OurNotion



\## 1. 개요

Spring Boot 기반으로 제작한 개인 프로젝트입니다.  

Oracle DB(Docker 환경)를 활용하여 회원 관리, 게시판, 예산 관리, 카카오 로그인, 파일 업로드 기능을 구현했습니다.



\## 2. 기술 스택

\- Language : Java  

\- Framework : Spring Boot  

\- DB : Oracle (Docker 컨테이너)  

\- Tools : Docker, IntelliJ, Maven/Gradle  

\- 외부 연동 : Kakao API (OAuth), 파일 업로드  



\## 3. 주요 기능

\- 회원 관리: 회원가입, 로그인, 카카오 소셜 로그인  

\- 게시판: 글 CRUD, 댓글, 첨부파일 업로드  

\- 예산 관리: 예산 기록, 수정, 조회  

\- 파일 업로드: 첨부파일 등록  

\- 카카오 로그인: 카카오 API 연동 (토큰·사용자 정보 DTO)  

\- 공통 페이지: 메인, 인덱스  



\## 4. 프로젝트 구조

\- `controller/` : `UserController`, `BoardController`, `BudgetController`, `KakaoLoginController` 등  

\- `service/` : `BudgetRecordService`, `KakaoService` 등  

\- `repository/` : `BoardDAO`, `UserDAO`, `BoardMapper` 등  

\- `entity/` : VO/DTO/Entity (예: `UserVO`, `BoardVO`, `KakaoTokenResponseDTO`)  

\- `resources/` : application 설정, 매퍼 XML  



\## 5. 프로젝트 진행

\- 전체 프로젝트 설계/구현 (기획부터 배포까지)  

\- Docker Oracle DB 컨테이너 연동  

\- 회원 관리 + 카카오 로그인 + 예산 관리 + 게시판 CRUD  

\- JPA + MyBatis 병행 적용  

\- 파일 업로드 구현 및 테스트  



