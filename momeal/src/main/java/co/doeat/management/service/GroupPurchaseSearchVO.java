package co.doeat.management.service;

import lombok.Data;

@Data
public class GroupPurchaseSearchVO extends GroupPurchaseListVO {
	int first;
	int last;
	String keyword;
}
