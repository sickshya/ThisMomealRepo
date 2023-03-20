package co.doeat.common.service;

import lombok.Data;

@Data
public class BoardSearchVO extends BoardVO{
	private String key;
	private String val;

	int first;
	int last;
	String keyword; // 검색 내용
	String typeArr;
	String type; // 검색 타입
}
