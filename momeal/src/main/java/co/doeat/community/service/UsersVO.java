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
	private int reportCnt;
	private String pubcRange;
	private String email;
	private String accessToken;
	private String userRole;
	private int attendanceCnt;
	private String profileImgPath;
	private String enabled;
	
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
