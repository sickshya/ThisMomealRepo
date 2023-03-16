package co.doeat.management.service;

import lombok.Data;

@Data
public class ChallengeSearchVO extends ChallengeVO {
	int first;
	int last;
	String keyword; // 검색 내용
	String datekeyword1; // 검색 내용
	String typeArr;
	String type; // 검색 타입
	String datekeyword2; // 검색 내용
}
