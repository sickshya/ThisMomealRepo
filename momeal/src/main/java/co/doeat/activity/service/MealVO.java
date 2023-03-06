package co.doeat.activity.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class MealVO {
	private int no;
	private String userId;
	private String subject;
	private String img;
	private String fileDir;
	private int mealScore;
	private String typeCode;
	private String mealTime;
	private String mealAmount;
	@DateTimeFormat
	@JsonFormat(pattern = "yyyy/MM/dd")
	private Date postDate;

	private String nickName; // 회원 닉네임
	private String profileImgPath; // 회원 프로필 사진 주소
	private String followeeId; // 팔로우 유무 판단용 아이디(팔로우 중이 아니라면 null 반환)

}