package co.doeat.management.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ChallengeValidationVO {
	private int no; // 고유 번호
	private String userId; // 회원 아이디
	private int chalNo; // 챌린지 번호
	@DateTimeFormat(pattern = "yyyy-MM-dd") 
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date vldDate; // 인증 날짜
	private String chalImg; // 인증 사진 이름
	private String fileDir; // 인증 사진 주소
	
	private String nickName; // 사진 올린 회원의 닉네임
	private String profileImgPath; // 사진 올린 회원의 프로필 사진 주소
}
