# bbmarketb
📁 프로젝트 구성
Spring Boot 기반 REST API 서버

JWT 인증 및 비밀번호 재설정 기능 구현

이메일 전송 및 토큰 만료 처리

MySQL 데이터베이스 연동

주요 기능: 회원가입, 로그인, 회원정보 조회/수정, 비밀번호 재설정

💻 설치 방법
Java 17 이상 설치

MySQL 설치 및 DB 생성

application.yml 또는 application.properties 설정:

properties
복사
편집
spring.datasource.url=jdbc:mysql://localhost:3306/bbmarket
spring.datasource.username=root
spring.datasource.password=비밀번호
spring.mail.username=이메일주소
spring.mail.password=앱비밀번호
프로젝트 루트에서 Gradle 또는 Maven으로 빌드:

bash
복사
편집
./gradlew build
java -jar build/libs/bbmarketb.jar
🛠️ 사용법
기본 주소: http://localhost:8080

주요 API:

POST /auth/register 회원가입

POST /auth/login 로그인 (JWT 발급)

GET /users/me 사용자 정보 조회

POST /password/forgot 비밀번호 재설정 요청

POST /password/reset 새로운 비밀번호 설정

📜 저작권 및 사용권
개인 포트폴리오용 프로젝트로 상업적 사용 금지

코드 및 구조 일부 참조는 허용하되 출처 표기 필요

👨‍💻 프로그래머 정보
이름: 백승준 (Sj Baek)

역할: 전체 백엔드 설계 및 구현

사용 기술: Spring Boot, JPA, JWT, MySQL, JavaMail, Scheduler, Gradle

🐞 버그 및 디버깅
이메일 발송 실패 시 상태 및 오류 코드 기록

만료된 토큰은 Scheduled Job으로 주기적으로 정리

테스트 계정으로 다양한 시나리오 테스트 완료

🔗 참고 및 출처
JWT 구조 및 구현: jwt.io

이메일 발송: JavaMail 공식 문서

스케줄러: Spring Scheduled Annotations

📌 버전 및 업데이트 정보
2025.07.22 - 비밀번호 재설정 기능 완성

2025.07.10 - JWT 기반 인증 기능 추가

2025.06.30 - 기본 회원가입 및 로그인 기능 완성
