# 책키라웃 (CheckItOut) - 도서 추천 및 공공도서관 연계 서비스

- Spring Boot 기반으로 도서 추천, 내 서재, 공공도서관 정보를 제공하는 백엔드 REST API 프로젝트
- Flask 기반 AI 추천 서버와 연동해 사용자 맞춤형 도서 추천 기능을 구현
- JWT 인증/인가, 관심 도서관 즐겨찾기, 주소 관리 기능을 제공하며 AWS EC2와 RDS 환경에서 운영

## 1. 주요 기능

- **회원 관리**: JWT 기반 인증/인가, 이메일 회원가입/로그인, 사용자 권한 관리 (USER/ADMIN)
- **도서 서비스**: Flask AI 연동 맞춤 추천, 내 서재 관리(등록/삭제/조회), 카테고리별 필터링
- **편의 기능**: 관심 공공도서관 즐겨찾기, 사용자 주소 관리

## 2. 기술 스택

- **Backend**: Java 17, Spring Boot 3.2.5, Spring Data JPA, QueryDSL, Spring Security, JWT, Gradle
- **Database**: MySQL, Hibernate
- **Infra**: AWS EC2, AWS RDS (MySQL), Amazon Linux

## 3. 아키텍처

![System Architecture](https://res.cloudinary.com/dtrxriyea/image/upload/v1786427892/%EC%A1%B8%EC%97%85_%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8_%EB%8F%84%EC%8B%9D%EB%8F%84_qevom2.png)

## 4. 학술 성과 (프로젝트 연계 논문)

**KSC(Korean Software Congress) 2024 Poster Section 논문 Accept**

- **주제**: Spring Framework 캐싱 전략 성능 분석
- **관련 링크**: [Spring Framework 캐싱 최적화 전략 (RISS)](https://www.riss.kr/search/detail/DetailView.do?p_mat_type=1a0202e37d52c72d&control_no=cd9dc3cecb211246b7998d826d417196&keyword=)

### 1) 논문 작성 배경 및 동기

- **Persistence Context(1차 캐시)** 를 주요 성능 향상 수단으로 설명하는 기존 여러 레퍼런스에 대해 의문을 가짐
- 1차 캐시의 본질적 설계 목적은 엔티티의 **동일성**(Identity) 보장과 생명주기 관리임
- 성능 이점은 동일 트랜잭션 내 PK 조회와 같은 제한적인 조건에서 발생하는 부수적 효과라는 가설을 세움
- **연구 목표**: 실제 부하 테스트를 통해 1차 캐시의 성능 기여도를 파악하고 분석함

### 2) 핵심 요약

- **Insert 쿼리 성능**: Data JPA가 JPQL 대비 약 **20%** 우수함
    - 실험 데이터(238.0 vs 197.8) 기준 약 1.2배 향상됨
- **Select 쿼리 성능**: Spring 내장 Cache Manager 사용 시, 기존 Data JPA 대비 약 **5.75배**의 Throughput 향상을 기록함

### 3) 상세 성능 분석 및 원인

#### 3-1) Insert 로직 성능 비교 (Data JPA vs. JPQL)

| 구분 | JPQL Logic | Spring Data JPA Logic | 비고 |
| :--- | :--- | :--- | :--- |
| **Throughput/sec** | 197.8 /sec | **238.0 /sec** | Data JPA 약 20% 빠름 |
| **Received KB/sec** | 93.48 | 112.48 | |
| **Sent KB/sec** | 97.35 | 116.90 | |

> **성능 차이 원인 분석:**
> Data JPA의 영속성 컨텍스트 기반 **쓰기 지연(Write-behind)** 메커니즘이 성능 차이를 결정함

- **쓰기 지연 (Transactional Write-behind)**: INSERT 쿼리를 즉시 실행하지 않고 영속성 컨텍스트의 쓰기 지연 SQL 저장소에 적재한 뒤 트랜잭션 종료 시점에 모아서 전송하여 네트워크 왕복 횟수를 최소화함
- **JDBC 배치 최적화 (Batch Insert)**: 1차 캐시에 보관된 엔티티들을 JDBC 수준의 배치 처리를 통해 한 번의 네트워크 요청으로 병합 전송하여 오버헤드를 물리적으로 감소시킴
- **영속성 컨텍스트의 효율적 관리**
  - 엔티티 상태 추적을 통해 불필요한 DB 통신을 억제하고 전체 처리량을 향상시킴

#### 3-2) Select 로직 성능 비교 (Cache Manager vs. Data JPA)

* **테스트 조건:** 200 Threads, 5 Loop count

| 구분 | Data JPA Logic | Use Cache Manager Logic | 성능 향상 배수 |
| :--- | :--- | :--- | :--- |
| **Throughput/sec** | 172.2 /sec | **991.1 /sec** | **약 5.75배** |
| **Received KB/sec** | 126.46 | **727.82** | |
| **Sent KB/sec** | 79.21 | **456.83** | |

> **성능 차이 원인 분석:**
> Spring 내장 Cache Manager 활용으로 다음과 같은 이점을 확보함

1. **In-Memory Caching**: 초기 조회 데이터를 메모리에 캐싱하여 후속 요청 시 DB 접근을 차단함
2. **리소스 절약**: 디스크 I/O 연산 및 DB 쿼리 실행에 필요한 컴퓨팅 리소스 소비를 줄임
3. **네트워크 효율**: DB 통신 횟수 감소로 네트워크 레이턴시 등 시스템 부하 요소를 제거함

### 4) 실험 환경

#### 4-1) 데이터베이스 (Database)

- **DBMS**: Amazon RDS for MySQL
- **인스턴스 타입**: AWS T4g 계열 (Arm 기반 Graviton 2 프로세서)
    - 클라우드 환경의 관리형 DB 활용

#### 4-2) 애플리케이션 서버

- **Runtime**: Java 17
- **Framework**: Spring Boot 3.2.5
- **Hardware (Host)**: Intel i7-1195G7 CPU / 16GB RAM

#### 4-3) 테스트 및 분석 도구

- **부하 테스트 도구**: Apache JMeter (200 Threads, 5 Loop 설정)
- **모니터링**: AOP를 활용한 메소드 레벨 로그 분석 및 실행 시간 측정
- **측정 지표**: Throughput, 데이터 송수신량(KB/sec), Latency

## 5. 디렉토리 구조

```text
src/main/java/hongik/demo_book/
├── address/    # 사용자 주소 관리
├── book/       # 도서 관리 및 Flask AI 연동
├── cateogory/  # 도서 카테고리 관리
├── global/     # 전역 공통 설정 (Config, Exception, JWT 등)
├── library/    # 공공도서관 데이터 및 즐겨찾기 관리
└── member/     # 회원 관리 및 인증 (Security, Auth)

src/main/resources/
├── application.yml      # 기본 설정
└── application-dev.yml  # 개발 환경 설정

docs/
└── readme/     # README 관련 리소스 (이미지 등)
```

## 6. API 문서 (API Documentation)

- [Postman API Documentation](https://documenter.getpostman.com/view/32521050/2sAXjQ1AKk)
