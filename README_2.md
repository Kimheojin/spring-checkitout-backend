# 책키라웃 (CheckItOut) - 도서 추천 및 공공도서관 연계 서비스

- Spring Boot 기반으로 도서 추천, 내 서재, 공공도서관 정보를 제공하는 백엔드 REST API 프로젝트
- Flask 기반 AI 추천 서버와 연동해 사용자 맞춤형 도서 추천 기능을 구현
- JWT 인증/인가, 관심 도서관 즐겨찾기, 주소 관리 기능을 제공하며 AWS EC2와 RDS 환경에서 운영

### ✅ 담당 역할

- Spring REST API 서버 설계
- 데이터 스크래핑 및 챗봇 데이터셋 구축
- 인프라 설계 및 운영

### ✅ 기술 스택

**Backend / DB**

- Java 17, Spring Boot 3.2.5, Spring Data JPA, Hibernate, QueryDSL, H2, MySQL

**Security / Auth**

- Spring Security, JWT(JJWT)

**Infra / External**

- AWS EC2, AWS RDS, Flask AI 추천 서버 연동