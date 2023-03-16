package co.doeat.management.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data

public class ExprParticipantsVO {

	Integer no; //체험단 등록순번
	String userId; //아이디
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date aplDate; //신청일자
	String addr; //배송받을 주소	
	
	//join

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date reviewStartDate; //리뷰작성시작기간
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date reviewEndDate; //리뷰작성완료기간
	private String title; //글제목
	private String subject; //글내용
	private String thumbnailImgPath; // 썸네일이미지 경로
}
