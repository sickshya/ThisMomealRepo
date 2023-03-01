package co.doeat.community.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class CommentVO {
	private int no;
	private String userId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date writeDate;
	private String subject;
	private int cmmtClass;
	private int cmmtOrder;
	private int cmmtCode;
	private int postNo;
	private String boardType;
}
