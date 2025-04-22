# 캠페인
- 상세 페이지
- 체험단 신청 페이지

***

## 참고사항
- img 경로를 application.properties에서 설정하고 있습니다.
  경로 변경 시 application.properties의 설정을 변경해 주세요.
- img 프록시를 /images/sample.jpg 형식으로 설정해 두었습니다.
  필요시 config > WebConfig.java 파일에서 변경할 수 있습니다.

***

## 상세 페이지
- 화면은 나옵니다. 아직 추가사항 작업 중입니다.

## 체험단 신청
- join 할 테이블이 많아서 연관 있는 테이블 entity를 전부 구현해 두었습니다.
- Application, Campaign, Users 엔티티 구현
- 신청 화면에서 Campaign, Users 엔티티의 데이터를 불러오도록 만들었습니다.
  추후 다른 서비스와 연결할 때 참고 부탁드립니다.

