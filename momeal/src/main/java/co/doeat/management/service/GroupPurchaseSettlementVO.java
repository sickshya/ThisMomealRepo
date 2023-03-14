package co.doeat.management.service;

import lombok.Data;

@Data
public class GroupPurchaseSettlementVO {

	private int no; // 상품번호
	private String userId; // 구매자아이디
	private String deliveryAddr; // 배송정보
	private String paymentMthd; // 카드결제여부
	private int zipcode; // 우편번호
	private String tel; // 전화번호
	private int totalGd; // 구매수량
	private int totalPrice; //총구매금액

	//join
	private int price; // 제품가격
	private int listNo; // list테이블 no
	private String addr; //고객주소
	private String thumbnailImgPath; // 썸네일이미지 경로
	private String name;
	private String subject;
	private int ordCnt; // 해당 물품의 구매자 수, 결제자 수 카운트
}