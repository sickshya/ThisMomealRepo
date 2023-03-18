package co.doeat.management.service;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChallengeSearchVO extends ChallengeVO {
	int first;
	int last;
	String keyword; // 검색 내용
	@DateTimeFormat(pattern="yyyy-MM-dd")
	String datekeyword1; // 검색 내용
	String typeArr;
	String type; // 검색 타입
	@DateTimeFormat(pattern="yyyy-MM-dd")
	String datekeyword2; // 검색 내용
}
