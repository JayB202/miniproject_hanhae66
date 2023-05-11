
# miniproject_hanhae66

![항해66](https://github.com/seunghee58/miniproject_hanhae66/assets/129656095/7893b3ae-5d9e-4f26-8f1e-e6802da9597e)

>개발 기간: 2023.05.04 ~ 2023.05.11</p>🎥 시연영상 https://www.youtube.com/watch?v=mGZ9BXAWmiE

### 🖐항해66 블라인드 게시판
  - 항해가 66일 남은 날 프로젝트를 시작하였고, 항해의 뒷 모습 이란 생각에 항해 66으로 이름을 지었다.
  - 항해66은 항해를 하는 사람들의 익명 게시판이다. Blind를 벤치 마킹 하였다. 
  - 유저의 아이디의 첫 글자, 주특기, 기수만 공개된다  ex) u***** (Spring) 14기

## 📃 S.A
https://www.notion.so/S-A-afa6ceba07db4022bebde44ccb35807a

## 📜와이어 프레임
<img width="913" alt="스크린샷 2023-05-11 014246" src="https://github.com/seunghee58/miniproject_hanhae66/assets/129656095/b30b6beb-c5e0-4635-b1ab-7bc7847a0d3a">

## 📰 ERD
![hh66 ERD](https://github.com/seunghee58/miniproject_hanhae66/assets/129656095/a5a8e351-6365-43c1-bede-76a5bc01670f)


## 📖 API 명세서
<details>
  <summary> 펼쳐보기 </summary>
<img width="964" alt="hh66 API 명세서" src="https://github.com/seunghee58/miniproject_hanhae66/assets/129656095/719cd245-0070-4e8d-9870-8fb55d2f5265">
</details>

## 👨‍👩‍👧팀원
|이름|역할|
|------|---|
|이현규(BE팀장)</br>[@OliveLover](https://github.com/OliveLover)|- 댓글 API</br>- 게시글, 댓글 좋아요 API</br>- 서버 배포</br>- 검색 기능</br>- DB 연동|
|정성윤</br>[@kanteluv](https://github.com/kanteluv)|- RefreshToken, AccessToken</br>- 유저 API </br>- entity 연관관계|
|김종범</br>[@JayB202](https://github.com/JayB202)|-게시글 API</br>-  검색 기능|
|한승희</br>[@seunghee58](https://github.com/seunghee58)|- 댓글 API </br>- 게시글, 댓글 좋아요 API </br>-  검색 기능|

FE git hub : https://github.com/HaeJinS2/MiniProject_Hanghae66_FE

## ⚙️ Tech Stack
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> <br>
<img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black"> <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <br>
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white"> <br>
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

## 1️⃣ 회원 관련 기능
  1. 회원 가입 API <br>
    - userId : 크기 4 이상, 10 이하 / 소문자와 숫자만 입력가능, 공백 금지 / **중복 불가** <br>
    - userName : 크기 10이하, 공백 금지 <br>
    - password : 크기 8 이상, 15 이하 / 대소문자, 숫자, 특수문자 가능, 공백 금지 <br>
    
  2. 로그인 / 로그아웃 API<br>
    - 로그인 성공 시, Access Token과 Refresh Token을 발급하고 헤더에 토큰을 추가<br>

  3. 마이페이지 API <br>
    - 나의 정보 확인 <br>
    - 내가 쓴 게시글, 댓글 목록 조회 <br>
  
## 2️⃣ 게시글 관련 기능
  1. 게시글 작성 API <br>
    - 카테코리를 선택 후 글을 작성 <br>
  
  2. 게시글 수정 API <br>
    - 내가 작성한 글에 한하여 수정 가능 <br>
    
  3. 게시글 삭제 API <br>
    - 내가 작성한 글에 한하여 삭제 가능 <br>

  4. 게시글 목록 조회 API <br>
    - 게시글 목록을 내림차순으로 조회 <br>
    - 카테고리 별 게시글 조회 <br>
    
  5. 게시글 상세 조회 API <br>
    - 게시글의 내용과 댓글 리스트 
    
  6. 게시글 좋아요 API <br>
    - 게시글에 좋아요 버튼을 눌러 좋아요 표시 <br>
    - 버튼을 한 번 더 누르면 취소 <br>
  
  7. 게시글 검색 API <br>
    - 키워드를 입력하여 **제목+내용, 제목, 내용**으로 게시글 조회 <br>
  
  8. 댓글 작성 API <br>
    - 모든 게시글에 댓글 작성 가능 <br>

  9. 댓글 좋아요 API <br>
    - 댓글에 좋아요 버튼을 눌러 좋아요 표시 가능 <br>
    - 버튼을 한 번 더 누르면 취소 <br>

## 🚩 기능 구현을 위해 고민한 것

그간 항해를 진행하면서 익혀왔던 테이블간의 연관관계를 구성하고 데이터를 가져오는 방법을 개선하고 싶었는데, <br>
서로 바라보고 있는 양방향관계에서는 순환참조가 일어날 수 밖에 없다고 생각 <br>

추천하는 방법이 @JsonIgnore, @JsonManagedReference, @JsonBackReference 과 같은 어노테이션을 사용하는 것과 <br>
DTO를 이용한 방법이 있었고 <br>
어노테이션을 이용한 방법은 정확히 어떻게 순환참조가 되지 않는지 이해가 잘 안되서 DTO를 이용하는 방법으로 CRUD 를 구현 <br>

DTO를 각 엔티티의 순환참조가 생길만한 연관관계를 지정하는 멤버를 제외하고 본따서 만듦 <br>
게시글과 댓글이 서로 양방향 관계라고 했을 때, 게시글은 댓글 멤버를, 댓글은 게시글 멤버를 뺀 DTO를 구성하고, 각각의  DTO를 더해서 응답 DTO를 만들어내는 로직을 구성 <br>

이렇게 되면 순환참조나 N+1 같은 문제를 피할 수 있게 되지만 구현하다 보니 원하는 출력값이 달라질 때마다 응답 DTO가 계속 추가됨 <br>

프로젝트를 끝내면서 생각해보니 안전하지만 코드가 길어지고 클래스가 늘어나는 DTO를 이용하는 방법도 최선이 아니겠다 생각을 함 <br>
