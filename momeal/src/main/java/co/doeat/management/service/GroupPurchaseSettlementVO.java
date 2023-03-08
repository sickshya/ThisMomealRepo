package co.doeat.management.service;

import lombok.Data;

@Data
public class GroupPurchaseSettlementVO {

	private int no; // 상품번호
	private String userId; // 구매자아이디
	private String deliveryAddr; 
	private String paymentMthd;
	private int zipcode; // 우편번호
	private String tel; // 전화번호
	private int totalGd; // 구매수량
	private int totalPrice; //총구매금액

	//join
	private int price; // 제품가격
	private int listNo; // list테이블 no
	private String addr; //고객주소
	private String thumbnailImgPath; // 썸네일이미지 경로
}