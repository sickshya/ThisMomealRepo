package co.doeat.management.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class GroupPurchaseListVO {

	private Integer no; // 상품번호
	private String name; // 상품이름
	private String coName; // 업체명
	private Integer price; // 상품가격
	private String subject; // 상세설명
	private Integer minGd; // 최소주문수량
	private Integer splyGd; // 공급한계수량
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date grpStartDate; // 공동구매시작일자
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date grpEndDate; // 공동구매완료일자
	private String thumbnailImg; // 이미지
	private String thumbnailImgPath; // 첨부이미지 경로

	// 이미지
	private String atchPath; // 첨부이미지 경로

	// 결제자 수 카운트
	private int ordCnt; // 해당 물품의 구매자 수

	// 날짜에 따른 공동구매 진행상태
	private String gpstatus;

}
