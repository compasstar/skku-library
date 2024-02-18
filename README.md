# SKKU-LIBRARY


## :book: 프로젝트 진행
- 개인프로젝트<br>
- 개발기간: 2023.11.01 ~ 2023.12.15<br>
- 개발자: 이재홍

<br>

## :christmas_tree: 프로젝트 소개
SKKU 도서관 사이트입니다.<br>
일반 사용자, 관리자 별로 이용이 가능하며, .<br>
도서 검색, 대출, 반납 등의 도서관 사이트에서 기본적으로 제공하는 기능들을 제공합니다.

## :green_book: 주요기능

### 관리자
- 사용자들의 정보를 조회, 삭제할 수 있습니다.
- 도서의 등록, 조회, 수정, 삭제가 가능합니다.

### 일반 사용자
- 메인페이지에서 도서 추천을 받습니다.
- 도서를 카테고리 별로 조회할 수 있습니다.
- 도서의 대출, 연장, 반납이 가능합니다.
- 자신이 현재 대출한 도서 목록을 조회할 수 있습니다.
- 자신의 계정을 삭제할 수 있습니다.


<br>

## :rocket: 시작 가이드
### 프로그래밍 요구 사항
- 자바 [JDK-17](https://www.oracle.com/java/technologies/downloads/#java17) 버전에서 실행 가능합니다 

### 설치
```
$ git clone https://github.com/compasstar/skku-library.git
$ cd skku-library
```

### 실행
- IntelliJ 를 실행시킨 뒤, Open 하여 skku-library 폴더의 build.gradle 를 선택해주세요<br>
![1](https://github.com/compasstar/skku-library/assets/55419868/dbd41383-9800-4f63-8bb1-d1dd28daa4c1)


- `resources/application.properties` 에서 DB 설정 및 파일경로를 개인에 맞게 변경해주세요. (username, password)
![2](https://github.com/compasstar/skku-library/assets/55419868/421caa35-0824-4af2-819d-9690ed9d8f40)

- `LibraryApplication`의 `main()`에서 실행`Run`합니다
<br>



## :school: 기술 스택

### Environment
<img src="https://img.shields.io/badge/IntelliJ-000000?style=for-the-badge&logo=intellij-idea&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

### Frontend
<img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white">
<img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">

### Backend
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
<img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
<img src="https://img.shields.io/badge/SpringDataJPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 

### DB
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 


<br>

## :computer: 화면 구성


| 방문 날짜                     | 메뉴 주문                     | 이벤트 혜택                    |
|---------------------------|---------------------------|---------------------------|
| ![game1](./img/game1.png) | ![game2](./img/game2.png) | ![game3](./img/game3.png) |



<br>

## :dart: 주요 기능

### 식당 예상 방문 날짜 및 주문 메뉴 입력 기능
- 12월 중 식당 예상 방문 날짜를 입력받는다
  - [검증] 1 이상 31 이하의 정수여야 한다
- 주문 시 주의 사항을 안내한다
  - 총주문 금액 10,000원 이상부터 이벤트 적용
  - 음료만 주문 불가
  - 메뉴는 한 번에 최대 20개까지만 주문 가능
- 주문할 메뉴와 개수를 입력한다 (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)
  - [검증] 음료만 주문할 수 없다
  - [검증] 메뉴는 한 번에 최대 20개까지만 주문할 수 있다
  - [검증] 메뉴판에 없는 메뉴를 주문할 수 없다
  - [검증] 메뉴의 개수는 1 이상의 정수여야 한다
  - [검증] 메뉴 형식이 예시와 일치해야 한다
  - [검증] 메뉴를 중복해서 주문할 수 없다

### 증정 이벤트 적용 기능
- 할인 전 총주문 금액이 12만 원 이상이라면, 샴페인 1개를 증정한다

### 할인 이벤트 적용 기능
- 총주문 금액과 이벤트 혜택 금액을 계산한다
- 총주문 금액 10,000원 이상부터 이벤트가 적용된다
  - 크리스마스 디데이 할인 (2023.12.1 ~ 2023.12.25)
  - 평일 할인 (일요일~목요일)
  - 주말 할인 (금요일, 토요일)
  - 특별 할인

### 총혜택 금액에 따른 이벤트 배지 부여 기능
- 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
- 5천 원 이상 1만 원 미만: 별
- 1만 원 이상 2만 원 미만: 트리
- 2만 원 이상: 산타

### 전체 이벤트 혜택 출력 기능
- 주문 메뉴 출력
- 할인 전 총주문 금액 출력
- 증정 메뉴 출력
  - 증정 메뉴 없다면 "없음" 출력
- 혜택 내역 출력
  - 고객에게 적용된 이벤트 내역만 출력한다
  - 적용된 이벤트가 없다면 "없음" 출력
- 총혜택 금액 출력
  - 총혜택 금액 = 할인 금액의 합계 + 증정 메뉴의 가격
- 할인 후 예상 결제 금액 출력
- 12월 이벤트 배지 내용 출력
  - 이벤트 배지가 없다면 "없음" 출력

<br>

## :mag: 아키텍쳐

### 디렉토리 구조
![architecture1](./img/architecture1.png)


### 작업 흐름
![architecture2](./img/architecture2.png)
