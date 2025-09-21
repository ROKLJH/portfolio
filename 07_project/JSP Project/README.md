\# JSP Project - 산오르미



\## 1. 개요

JSP/Servlet 기반 MVC 구조로 제작한 팀 프로젝트입니다.  

회원 관리, 게시판, 신고·관리자 기능을 갖춘 커뮤니티 성격의 웹서비스입니다.



\## 2. 기술 스택

\- Language : Java, JSP  

\- Framework : Servlet, JSTL  

\- DB : MySQL (JDBC)  

\- Tools : Tomcat, Eclipse  

\- 기타 : web.xml 기반 라우팅  



\## 3. 주요 기능

\- 회원 관리 : 회원가입, 로그인/로그아웃, 비밀번호 찾기, 회원탈퇴  

\- 게시판 : 자유게시판, QnA 게시판 (CRUD + 댓글)  

\- 신고/관리자 : 게시글 신고, 관리자 승인/거절, 회원 관리  

\- 공통 뷰 : header/footer 분리 후 include로 모듈화  

\- 기타 : 차트 시각화, 커뮤니티 랭킹  



\## 4. 프로젝트 구조

\- `controller/` : 액션 클래스 (예: `LoginAction`, `AcceptReportAction`)  

\- `model/` : DAO, VO 클래스  

\- `webapp/` : JSP 뷰 (`login.jsp`, `adminPage.jsp` 등)  

\- `WEB-INF/web.xml` : 라우팅 설정  



\## 5. 담당 역할

\- JSP 페이지 제작 (로그인, 회원가입, 메인, 관리자, 게시판 등)  

\- JSTL 활용 조건/반복 처리  

\- \*\*header/footer 분리 → include 모듈화 적용\*\* (코드 중복 약 15% 감소)  

\- UI/UX 개선 (공통 레이아웃, 메뉴 구조, 에러 페이지 추가)  



