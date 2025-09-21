\# Java Project - Shoe\_CON



\## 1. 개요

Java 프로젝트에 웹 크롤링 기능을 추가한 프로그램입니다.  

회원 관리와 상품 관리 기능을 구현하고, WebCrawling 클래스를 통해 데이터를 수집·활용했습니다.



\## 2. 기술 스택

\- Language : Java  

\- Tools : Eclipse  

\- 라이브러리 : Jsoup (웹 크롤링)  



\## 3. 주요 기능

\- 회원 관리 : 회원 등록/조회 (`MemberDAO`, `MemberVO`)  

\- 상품 관리 : 상품 등록/조회 (`ProductDAO`, `ProductVO`)  

\- 웹 크롤링 : 외부 웹페이지에서 데이터 수집 (`WebCrawling.java`)  

\- 프로그램 실행 흐름:  

&nbsp; - `Client.java` : 메인 실행  

&nbsp; - `Ctrl.java` : 흐름 제어  

&nbsp; - `View.java` : 콘솔 출력 담당  



\## 4. 프로젝트 구조

\- `src/client/Client.java` : 프로그램 시작점  

\- `src/ctrl/Ctrl.java` : 흐름 제어  

\- `src/model/` : DAO, VO, WebCrawling  

\- `src/view/View.java` : 콘솔 출력  



\## 5. 담당 역할

\- 프로젝트 전체 구현  

\- DAO/VO 설계 및 적용  

\- 웹 크롤링 기능 구현 (`WebCrawling.java`)  

\- MVC 구조 설계 및 흐름 제어  



