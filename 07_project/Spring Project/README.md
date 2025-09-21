\# Spring Project - Startoup



\## 1. 개요

Spring MVC 기반 팀 프로젝트입니다.  

Oracle + MyBatis를 사용해 회원·상품·결제·관리자·메일·SMS·크롤링 기능을 구현했습니다.



\## 2. 기술 스택

\- Language : Java  

\- Framework : Spring MVC, MyBatis  

\- DB : Oracle  

\- Build : Maven  

\- Tools : DBeaver, Tomcat  

\- 외부 연동 : JavaMail, SMS API, Selenium 크롤링  



\## 3. 주요 기능

\- 회원 : 로그인, 중복검증(ID/Email), 마이페이지  

\- 상품/결제 : 상품 목록, 상세조회, 결제, 결제 성공 처리  

\- 관리자 : 상품/펀딩/회원 관리, 메일 일괄 발송  

\- 메일 : 전체 메일 발송, 발송 결과 확인  

\- SMS 인증 : 휴대폰 인증  

\- 웹 크롤링: 외부 데이터 수집  

\- 공용 페이지 : 메인/소개/안내  



\## 4. 프로젝트 구조

\- `controller/` : `MemberController`, `ProductController`, `AdminController` 등  

\- `service/` : 비즈니스 로직  

\- `dao/` : DAO 클래스  

\- `mappings/` : MyBatis 매퍼 XML (예: `member-mapping.xml`)  

\- `vo/` : VO/DTO 클래스  



\## 5. 담당 역할

\- DB 테이블/VO 설계 및 매핑  

\- MyBatis 매퍼 XML 작성 (select, insert, update, delete)  

\- DAO 구현 및 Service 연동  

\- DBeaver를 활용해 SQL 사전 검증 (컬럼 불일치, JOIN 오류 사전 차단)  

\- 데이터 흐름을 코드와 매퍼로 분리하여 리뷰·유지보수 효율 향상  



