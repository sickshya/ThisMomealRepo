package co.doeat.common.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class BoardVO {
	private int no;
	private String boardTitle;
	private String boardSubject;
	@DateTimeFormat(pattern = "yyyyMMdd")
	@JsonFormat(pattern = "yyyyMMdd")
	private Date boardDate;
	private int boardHit;
	private String boardCode;
	private String userId;

	int first;
	int last;
	String keyword;
	String typeArr;
	String type;
	
	int startPage;			
	int endPage;

}