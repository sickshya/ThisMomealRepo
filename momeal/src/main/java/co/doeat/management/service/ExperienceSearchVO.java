package co.doeat.management.service;

import java.util.Date;

import lombok.Data;

@Data
public class ExperienceSearchVO extends ExperienceVO {
	int first;
	int last;
	String keyword;
	String typeArr;
	String type;
	
	Date datekeyword1; // 검색 내용
	Date datekeyword2; // 검색 내용
}
