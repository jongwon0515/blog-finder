# blog-finder

| 엔드포인트              | 메서드 | 파라미터                                  | 설명                                                |
|--------------------|-----|---------------------------------------|---------------------------------------------------|
| /v1/search/blog    | GET | ?query=개발&sort=recency&page=1&size=10 | kakao, naver open api로 블로그 검색                     |
| /v1/search-keyword | GET |                                       | 인기 검색어 목록 - 사용자들이 많이 검색한 순서대로, 최대 10개의 검색 키워드를 제공 |

## jar파일 다운로드 링크
[다운로드](https://drive.google.com/file/d/1-iCD4Usj3rY81swb0UOvSyYXKrjUqchC/view?usp=share_link)


## 앱 정보
- JAVA 11
- Spring Boot 2.7.9
- Gradle 멀티 모듈 프로젝트

## 구현 기능
- 블로그 검색
- 인기 검색어 목록
- 카카오 블로그 검색 API에 장애가 발생한 경우, 네이버 블로그 검색 API를 통해 데이터 제공

## 테스트 케이스
- controller / service