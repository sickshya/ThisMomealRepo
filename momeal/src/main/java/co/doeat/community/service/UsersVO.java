package co.doeat.community.service;

import lombok.Data;

@Data
public class UsersVO {
	private String userId;
	private String userName;
	private String nickName;
	private String password;
	private String tel;
	private String addr;
	private String zipcode;
	private String profileImg;
	private String grade;
	private int totalPoint;
	private String quitReq;
	private int reportCnt; // 신고받은 횟수
	private String pubcRange; // 공개여부
	private String email;
	private String userRole; // 유저권한
	private int attendanceCnt;
	private String profileImgPath;
	private String enabled; // 탈퇴관련

	// 메인화면에 뿌려줄
	private int postNum; // 해당 유저가 등록한 식단 수
	private int flwerCnt; // 해당 유저를 팔로우 하고있는 사람들의 수
	private int flweeCnt; // 해당 유저가 팔로우 하는 사람의 수, 해당유저의 팔로우를 받는 사람의 수

	// 공동구매
	private int totalGd;
	private int totalPrice;
	private String deliveryAddr;
	private Integer no; // 상품번호
	private String name; // 상품이름
	private String coName; // 업체명
	private Integer price; // 상품가격
	private String subject; // 상세설명

	// 이미지
	private String atchPath; // 첨부이미지 경로
	private String thumbnailImg; // 썸네일이미지 이름
	private String thumbnailImgPath; // 썸네일이미지 경로

}
