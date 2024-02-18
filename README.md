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
<img src="https://img.shields.io/badge/IntelliJ-000000?style=for-the-badge&logo=intellij-idea&logoColor=white"><img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"><img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">

### Frontend
<img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white"><img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white"><img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white"><img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">

### Backend
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/SpringDataJPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 

### DB
<img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 


<br>

## :computer: 화면 구성

### 회원가입/로그인
| 로그인                     | 회원가입                     |
|---------------------------|---------------------------|
| ![로그인](https://github.com/compasstar/skku-library/assets/55419868/efaef9cb-ed04-4447-ac0e-215a53a12662) | ![회원가입](https://github.com/compasstar/skku-library/assets/55419868/574c9910-0ad4-4ba5-ad38-631fad5902d3) |


### 관리자
| 사용자 관리                     | 도서 검색                     | 도서 추가                    |
|---------------------------|---------------------------|---------------------------|
| ![사용자 관리](https://github.com/compasstar/skku-library/assets/55419868/5d451b8b-36c8-49cb-9441-636884b25077) | ![도서 검색](https://github.com/compasstar/skku-library/assets/55419868/0419f882-312b-49e0-8147-13793f0105b8) | ![도서 추가](https://github.com/compasstar/skku-library/assets/55419868/6d292582-31cc-4146-a370-eea92c34323b) |


### 일반 사용자
| 도서 추천                     | 도서 대출/연장/반남                    | 빌린 도서정보 조회                    |
|---------------------------|---------------------------|---------------------------|
| ![도서 추천](https://github.com/compasstar/skku-library/assets/55419868/0159757f-ec14-46fb-807f-be75e6ef1887) | ![도서 대출/연장/반남](https://github.com/compasstar/skku-library/assets/55419868/ed6ff6e5-3b6f-4243-9589-c1c5339720cb) | ![빌린 도서정보 조회](https://github.com/compasstar/skku-library/assets/55419868/2d59eca9-8451-4e43-8cf2-659ef0a1b653) |



<br>

## :dart: 주요 기능

### 화면 기능
- 화면 상단에는 항상 로고(홈화면이동)와 메뉴가 존재한다
- 도서 이미지에 마우스를 올리면 약간 상승하며 그림자를 주어 선택된 느낌을 준다
- 어느 도서 이미지에서든 클릭하면 도서 정보로 이동한다
- 링크를 통해 이동가능한 텍스트에 마우스를 올리면 밑줄을 그어 선택된 느낌을 준다
- 화면 하단에는 항상 로그아웃 버튼을 두어 언제든 로그아웃이 가능하도록 한다

### 관리자: 사용자 관리 기능
- 사용자 목록을 관리자에게 제공한다
- 사용자를 선택하면 해당 사용자의 id를 통해 이름, 아이디, 패스워드, 빌린 도서 정보를 제공한다
- 관리자는 사용자 정보를 삭제할 수 있다

### 관리자: 도서 관리 기능
- 관리자는 도서를 등록할 수 있다
  - 등록에는 제목, 저자, 출판사, 출판년도, 장르, 이미지 정보가 필요하다
- 도서를 조회할 수 있다
  - 도서 제목 혹은 저자로 검색할 수 있다
  - 도서의 장르 별로 검색할 수 있다
- 도서의 정보를 수정 및 삭제할 수 있다

### 사용자: 도서 대출 기능
- 도서의 제목 혹은 저자로 검색할 수 있다
- 도서의 장르 별로 검색할 수 있다
- 도서를 대출, 연장, 반납할 수 있다
- 도서를 대출하면 해당 책은 Available 에서 Checked Out 으로 변경된다 

### 사용자: 빌린 도서 관리 기능
- 사용자는 자신이 빌린 책의 목록을 확인할 수 있다
- 도서 정보에서 반납까지 남은 일 수를 알려준다
- 사용자는 자신의 계정을 삭제할 수 있다


