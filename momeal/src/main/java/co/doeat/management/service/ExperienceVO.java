package co.doeat.management.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ExperienceVO {
	Integer no; //체험단등록순번
	String title; //글제목
	String subject; //글내용
	Integer nowAplyt; //현재신청인원
	Integer lmtAplyt; //신청인원제한
	String nessGrd; //필요등급
	Integer savePoint; //적립포인트
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date expeStartDate; //체험신청시작기간
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date expeEndDate; //체험신청완료기간
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date reviewStartDate; //리뷰작성시작기간
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date reviewEndDate; //리뷰작성완료기간
}
