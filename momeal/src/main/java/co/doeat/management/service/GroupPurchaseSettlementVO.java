package co.doeat.management.service;

import lombok.Data;

@Data
public class GroupPurchaseSettlementVO {
	private Integer no; //상품번호
	private String userId; //구매자아이디
	private String deliveryAddr; //배송지
	private String paymentMthd; //결제수단
	private Integer zipcode; //우편번호
	private String tel; //전화번호
	private int totalGd; // 구매수량
  
	private int price; //제품가격
	private int totalPrice; //제품가격
}
