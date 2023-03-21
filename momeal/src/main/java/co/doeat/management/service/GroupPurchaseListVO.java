package co.doeat.management.service;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import co.doeat.common.service.ImageVO;
import lombok.Data;

@Data
public class GroupPurchaseListVO extends ImageVO {

	private int no; // 상품번호
	private String name; // 상품이름
	private String coName; // 업체명
	private int price; // 상품가격
	private String subject; // 상세설명
	private int minGd; // 최소주문수량
	private int splyGd; // 공급한계수량
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date grpStartDate; // 공동구매시작일자
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date grpEndDate; // 공동구매완료일자
	private String prdtCtgryCode; // 상품카테고리코드
	
  // JOIN
	private int ordCnt; // 해당 물품의 구매자 수, 결제자 수 카운트
	private List<ImageVO> imgPath; // 관리자 공동구매관리 상세조회 시, 이미지 경로값
	private String gpstatus; // 날짜에 따른 공동구매 진행상태
	private String deliveryAddr; // 공동구매 물품배송지
	private int zipcode;
	private String tel;
	private int listNo;
	private int pchOrdCnt; // 공동구매 잔여 구매가능 수 
	private int totalGd; // 구매수량

	// 이미지
	private String atchPath; // 첨부이미지 경로
	private String thumbnailImg; // 썸네일이미지 이름
	private String thumbnailImgPath; // 썸네일이미지 경로


}
