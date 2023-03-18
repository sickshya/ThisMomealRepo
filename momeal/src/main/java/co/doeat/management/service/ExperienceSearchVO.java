package co.doeat.management.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ExperienceSearchVO extends ExperienceVO {
	int first;
	int last;
	String keyword;
	String typeArr;
	String type;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date datekeyword1; // 검색 내용
	@DateTimeFormat(pattern="yyyy-MM-dd")
	Date datekeyword2; // 검색 내용
}
