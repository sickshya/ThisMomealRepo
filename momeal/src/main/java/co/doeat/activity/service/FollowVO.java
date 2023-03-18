package co.doeat.activity.service;

import lombok.Data;

@Data
public class FollowVO {
	private String followerId; // 팔로우 신청한 회원의 아이디(로그인한 회원 본인)
	private String followeeId; // 팔로우 신청을 받은 회원의 아이디(팔로우 신청당한 회원)
	private String accept; // 수락여부
	
	private int followerCnt; // 팔로워 수 카운트
	private int followeeCnt; // 팔로잉 수 카운트
	private int followChk; // 팔로잉 유무 체크 (팔로우 중 = 1, 팔로우 안 함 = 0)
	
	private String nickName; // 회원 닉네임
	private String profileImgPath; // 회원 프로필 사진 주소
}
