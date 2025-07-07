# 책키라웃 - 도서 추천 및 공공도서관 연계 서비스
Spring Boot 백엔드 REST API 서버 코드 
백엔드, AI 도서정보 데이터셋 생성/정제 및 DevOps 담당
## 링크
- **API 문서**: [Postman 문서](https://documenter.getpostman.com/view/32521050/2sAXjQ1AKk)
## 배포 환경
- **배포 방식**: AWS EC2 + AWS RDS (MySQL)
- **서버 환경**: Amazon Linux, t2.micro

## 프로젝트 도식도

![졸프 도식도](https://res.cloudinary.com/dtrxriyea/image/upload/v1751883671/markdonw/ev5amliqzc2jeg0ztkoe.png)

## 주요 기능
- Flask AI 추천 서버 통신 기능 구현
- JWT Token 기반 인증/인가 구현
- RESTful API 설계 및 구현

## 스택
### Backend
- Java 17, Spring Boot 3.2.5
- Spring Data JPA, QueryDSL, Spring Security, JWT

### Database
- MySQL

### API 문서화
- Postman

## 학술 논문
### - KSC(Korean Software Congress) 2024 Poster Section 논문 Accept
- 한국정보과학회 주관
- Apache JMeter 기반 Spring Framework 캐싱 전략 성능 분석: Data JPA, JPQL, Cache Manager의 캐시 레벨별 성능 비교 (1차, 2차 캐시)
- 관련 링크: [Spring Framework 캐싱 최적화 전략](https://www.riss.kr/search/detail/DetailView.do?p_mat_type=1a0202e37d52c72d&control_no=cd9dc3cecb211246b7998d826d417196&keyword=)

