package co.doeat.management.service;

import lombok.Data;

@Data
public class GroupPurchaseSettlementVO {

	private int no; //상품번호
	private String userId; //구매자아이디
	private String deliveryAddr; 
	private String paymentMthd; 
	private int zipCode; //우편번호
	private String tel; //전화번호
	private int totalGd; // 구매수량
	private int totalPrice;
	
	private int price; //제품가격
}
