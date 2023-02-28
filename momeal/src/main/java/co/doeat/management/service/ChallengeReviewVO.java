package co.doeat.management.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class ChallengeReviewVO {
	String userId;
	Integer no;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date writeDate;
	String subject;
	Integer star;
}
