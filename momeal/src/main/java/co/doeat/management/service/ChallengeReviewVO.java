package co.doeat.management.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ChallengeReviewVO {
	private String userId; // 회원 아이디
	private int no; // 챌린지 번호
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date writeDate; // 후기 작성일
	private String subject; // 후기 내용
	private int star; // 별점
	
	private String nickName; // 회원 닉네임
	private String profileImgPath; // 회원 프로필 사진 주소
}
