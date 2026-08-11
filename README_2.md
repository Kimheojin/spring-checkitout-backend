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

### ✅ Architecture

![recipe architecture](https://res.cloudinary.com/dtrxriyea/image/upload/v1786427892/%EC%A1%B8%EC%97%85_%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8_%EB%8F%84%EC%8B%9D%EB%8F%84_qevom2.png)

### ✅ 배포 환경

- [온프레미스 서버 스펙](https://github.com/heojinn/spring-recipe-backend/wiki/2.-OnPremises-Server-Specifications)

### ✅ 주요 기능

▶ 레시피 조회

1. 단일 레시피 상세 및 전체 레시피 조회
2. MongoDB ObjectId 기반 커서형 페이징 적용

▶ [더 보기](https://github.com/heojinn/spring-recipe-backend/wiki/3.-Business-Rule)

### ✅ 주요 기술 구현 정리

#### [1. Apache Nori for Korean Search](https://github.com/heojinn/spring-recipe-backend/wiki/4.-Apache-Nori-for-Korean-Search)

#### [2. EdgeGram Autocomplete](https://github.com/heojinn/spring-recipe-backend/wiki/5.-EdgeGram-Autocomplete)

#### [3. Multi‐Level Caching with Caffeine & Redis](https://github.com/heojinn/spring-recipe-backend/wiki/6.-Multi%E2%80%90Level-Caching-with-Caffeine-&-Redis)

#### [4. Atlas Search Aggregation Pipeline](https://github.com/heojinn/spring-recipe-backend/wiki/7.-Atlas-Search-Aggregation-Pipeline)

#### [5. Guest Recipe Management with Cookie & Interceptor](https://github.com/heojinn/spring-recipe-backend/wiki/8.-Guest-Recipe-Management-with-Cookie-&-Interceptor)

### ✅ etc