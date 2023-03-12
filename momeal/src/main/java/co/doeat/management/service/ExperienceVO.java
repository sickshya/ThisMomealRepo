package co.doeat.management.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import co.doeat.common.service.ImageVO;
import lombok.Data;

@Data
public class ExperienceVO extends ImageVO {
	
	private int no; //체험단등록순번
	private String title; //글제목
	private String subject; //글내용
	private int nowAplyt; //신청인원
	private int lmtAplyt; //마감인원
	private int Aplyt; // 남은인원 카운트
	private String nessGrd; //필요등급
	private int savePoint; //적립포인트
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expeStartDate; //체험신청시작기간
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expeEndDate; //체험신청완료기간
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reviewStartDate; //리뷰작성시작기간
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date reviewEndDate; //리뷰작성완료기간
	private int ordCnt; // 해당 물품의 구매자 수, 결제자 수 카운트
	private String thumbnailImg; // 썸네일이미지 이름
	private String thumbnailImgPath; // 썸네일이미지 경로
	private int startDate; //체험단시작날짜 
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	
	
	// 이미지
	private String atchPath; // 첨부이미지 경로
	
	//관리자
	private List<ImageVO> imgPath; // 관리자 상세조회 시, 이미지 경로값
	private String exstatus; // 날짜에 따른 공동구매 진행상태
	
}
