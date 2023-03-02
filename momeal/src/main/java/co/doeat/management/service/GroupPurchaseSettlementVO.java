package co.doeat.management.service;

import lombok.Data;

@Data
public class GroupPurchaseSettlementVO {

	private Integer prdtNo; //상품번호
	private String userId; //구매자아이디
	private String sttlSt; //결제여부
	private String shpDest; //배송지
	private String sttlMthd; //결제수단
	private Integer zipCode; //우편번호
	private String tel; //전화번호
	private Integer totalGd; // 구매수량
	
	private Integer price; //제품가격
	private String atchPath; // 첨부이미지경로 
}
