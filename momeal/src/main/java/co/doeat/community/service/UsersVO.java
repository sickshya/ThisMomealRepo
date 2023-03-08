package co.doeat.community.service;

import lombok.Data;

@Data
public class UsersVO {
	private String userId;
	private String userName;
	private String nickName;
	private String pwd;
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
	
	private int totalGd;
	private int totalPrice;
	private String deliveryAddr;
}
