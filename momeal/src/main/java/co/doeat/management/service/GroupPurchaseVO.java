package co.doeat.management.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class GroupPurchaseVO {
	
	private Integer no;  //상품번호
	private String name; //상품이름
	private String coName; //업체명
	private Integer price; //상품가격
	private String subject; //상세설명
	private Integer minGd; //최소주문수량
	private Integer splyGd; //공급한계수량
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date grpStartDate; //공동구매시작일자
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date grpEndDate; //공동구매완료일자
	private String prdtCtgryCode; //상품카테고리코드
}
