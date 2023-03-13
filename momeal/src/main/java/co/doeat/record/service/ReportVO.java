package co.doeat.record.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ReportVO {
	private int boardRprtNo;
	private int usrRprtNo;
	private String reporter;
	private String reasonCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rprtDate;
	private int boardNo; // 신고받은 게시판 번호
	private String boardCode; // 게시판 구분 코드
	private String reportee;
	private String processCode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rprtSolvDate;
}
