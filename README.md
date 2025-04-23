# 캠페인
- 상세 페이지
- 체험단 신청 페이지

***

## 참고사항
- img 경로를 application.properties에서 설정하고 있습니다.
  경로 변경 시 application.properties의 설정을 변경해 주세요.
  - 경로 : C:/CampaignImg/
- img 프록시를 /images/sample.jpg 형식으로 설정해 두었습니다.
  필요시 config > WebConfig.java 파일에서 변경할 수 있습니다.

***

## 상세 페이지
- 상태(ProgressStatus)에 따라 화면이 달라지도록 구성하였습니다.
  - waiting : 신청 버튼 클릭x. 리뷰 기간 제외한 정보 화면에 보임.
  - ongiong : 신청할 수 있음. 리뷰 기간 제외한 정보 화면에 보임.
  - rewiewing : 신청 버튼 클릭 x. 리뷰 작성 기간 화면에 보임.
  - completed : 신청 버튼 클릭 x. title과 description 제외한 정보 제거.
- 캠페인 페이지 데이터는 1~10까지 있습니다.
  각 데이터별 상태는 data.sql를 참고해 주세요.
- 유의사항, 기타 안내를 추가하였습니다.
  추가하거나 수정할 내용 있다면 말씀해 주세요.


## 체험단 신청
- join 할 테이블이 많아서 연관 있는 테이블 entity를 전부 구현해 두었습니다.
- Application, Campaign, Users 엔티티 구현
- 신청 화면에서 Campaign, Users 엔티티의 데이터를 불러오도록 만들었습니다.
  추후 다른 서비스와 연결할 때 참고 부탁드립니다.

